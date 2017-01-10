package com.allen.common.db.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.allen.common.db.mysql.DbHelper;

public class DbHelpTest {

	/**
	 * 1	1002	adanac	0	0	123
	 * 2	1203	cd	0	0	wsx
	 * 3	1003	jean	1	1	aqe
	 */
	@Test
	public void test_query() {
		String sql = null;
		DbHelper db1 = null;
		ResultSet ret = null;

		sql = "select * from demo.tab_common";// SQL语句
		db1 = new DbHelper(sql);// 创建DBHelper对象

		try {
			ret = db1.pst.executeQuery();// 执行语句，得到结果集
			while (ret.next()) {
				String id = ret.getString(1);
				String deptCode = ret.getString(2);
				String userName = ret.getString(3);
				String sex = ret.getString(4);
				String age = ret.getString(5);
				String passwd = ret.getString(6);
				System.out.println(id + "\t" + deptCode + "\t" + userName + "\t" + sex + "\t" + age + "\t" + passwd);
			} // 显示数据
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
