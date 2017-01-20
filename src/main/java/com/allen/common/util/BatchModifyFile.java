package com.allen.common.util;

import java.io.File;

import com.allen.common.exception.ModifyFileException;

public class BatchModifyFile {

	public static void main(String[] args) {
		String filePath = "E://adanac_book//我的笔记//";
		String suffixName = "md";
		int count = batchModifySuffixName(filePath, suffixName);
		System.out.println("修改了记录数：" + count);
	}

	/**
	 * <p>
	 * 递归处理
	 * </p>
	 * 批量修改文件夹的所有文件的后缀名
	 * @param filePath 要修改的文件夹路径
	 * @param targetName 修改后的文件后缀名
	 * @return 修改的记录数
	 */
	public static int batchModifySuffixName(String filePath, String targetName) {
		int count = 0;
		try {
			File file = new File(filePath);
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						int subCount = batchModifySuffixName(files[i].getPath(), targetName);
						count += subCount;
					} else {
						boolean flag = modifySuffixName(files[i], targetName);
						if (flag) {
							count++;
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ModifyFileException("修改文件后缀名出错");
		}
		return count;
	}

	/**
	 * 修改文件后缀名
	 * @param file
	 * @param targetName
	 * @return
	 */
	private static boolean modifySuffixName(File file, String targetName) {
		System.out.println(file.getAbsolutePath());// 包括了文件名

		// 输出为：E:\adanac_book\我的笔记\工作\工作总结.md.txt
		// 而我们需要只要路径，然后将文件名进行适当的截取。因此需要用fiiles[i].getParent()
		String sourceFilePath = file.getParent();
		String sourceFileName = file.getName();
		if (sourceFileName.indexOf(".") > 0) {
			sourceFileName = sourceFileName.substring(0, sourceFileName.indexOf("."));
		}

		String fileNameWithPath = sourceFilePath + "//" + sourceFileName + "." + targetName;
		boolean flag = file.renameTo(new File(fileNameWithPath));
		return flag;
	}

}