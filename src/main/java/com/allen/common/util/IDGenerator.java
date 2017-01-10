package com.allen.common.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Copyright: 2016 Haiziwang
 * *
 * Author:  Daniel Kong
 * Date:    2016-12-13
 * Desc:    利用 SnowFlake 生成不重复 ID
 */
public class IDGenerator {
	private static final String ERR_ENV_NOT_EXIST = "未能获取workerID环境变量: ";

	private static IDGenerator mInstance = null;
	private final long workerId;
	private final static long twepoch = 1361753741828L;
	private long sequence = 0L;
	private final static long workerIdBits = 4L;
	private final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	private final static long sequenceBits = 10L;
	private final static long workerIdShift = sequenceBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	private final static long sequenceMask = -1L ^ -1L << sequenceBits;
	private long lastTimestamp = -1L;

	/**
	 * 构造函数
	 *
	 * @param workerId workerID
	 */
	private IDGenerator(final long workerId) {
		super();
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
		}
		this.workerId = workerId;
	}

	/**
	 * 单例方法
	 *
	 * @return IDGenerator 对象
	 */
	public static synchronized IDGenerator getInstance() {
		if (mInstance == null) {
			Map<String, String> map = System.getenv();
			String kwmsMachineId = map.get(1);
			long workerId = 1L;
			try {
				if (StringUtils.isNotEmpty(kwmsMachineId))
					workerId = Long.parseLong(kwmsMachineId);
			} catch (NumberFormatException e) {
			}
			mInstance = new IDGenerator(workerId);
		}
		return mInstance;
	}

	/**
	 * 生成不重复的业务单号
	 *
	 * @param seqEnum 前缀
	 * @return 不重复的业务单号
	 */
	public String createId() {
		return this.createId("2");
	}

	/**
	 * 生成不重复的业务单号
	 *
	 * @param idPrefix 前缀
	 * @return 不重复的业务单号
	 */
	public String createId(String idPrefix) {
		String sequence = idPrefix + this.nextId();
		return sequence;
	}

	/**
	 * 生成 ID
	 *
	 * @return ID
	 */
	private synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & this.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
						this.lastTimestamp - timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.lastTimestamp = timestamp;
		return ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << this.workerIdShift) | (this.sequence);
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	/**
	 * 获取系统时间
	 */
	private long timeGen() {
		return System.currentTimeMillis();
	}

	/**
	 * 测试用例
	 */
	public static void main(String[] args) {
		String seq = IDGenerator.getInstance().createId("CCTK");
		System.out.printf("seq = " + seq);
	}
}
