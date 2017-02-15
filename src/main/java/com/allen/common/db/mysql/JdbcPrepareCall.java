package com.allen.common.db.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcPrepareCall {
	public static void main(String[] args) {
		Connection cn = null;
		CallableStatement cstmt = null;
		try {
			// 这里最好不要这么干，因为驱动名写死在程序中了
			Class.forName("com.mysql.jdbc.Driver");
			Context ctx = new InitialContext();
			// 实际项目中，这里应用DataSource数据，如果用框架，
			// 这个数据源不需要我们编码创建，我们只需
			DataSource ds = (DataSource) ctx.lookup("java:comp/env");
			// cn = ds.getConnection();
			cn = DriverManager.getConnection("jdbc:mysql:///test", "root", "root");
			cstmt = cn.prepareCall("{call insert_Student(?,?,?)}");
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.setString(1, "wangwu");
			cstmt.setInt(2, 25);
			cstmt.execute();
			// get第几个，不同的数据库不一样，建议不写
			System.out.println(cstmt.getString(3));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
