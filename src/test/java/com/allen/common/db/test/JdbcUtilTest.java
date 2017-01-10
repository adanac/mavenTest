package com.allen.common.db.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.allen.common.db.mysql.JdbcUtil;
import com.allen.common.util.RandomToolkit;

public class JdbcUtilTest {

	/** 
	* 批处理执行预处理SQL测试 
	* 预处理批量执行100条Insert操作，共耗时：0.833秒！
	*/
	@Test
	public void test_ParperedInsertBatch() throws Exception {
		Connection conn = null;
		Long start = System.currentTimeMillis();
		int count = 100;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "" + "insert into demo.tab_common\n" + "    (deptCode, userName,sex ,age,passwd)\n"
					+ "values\n" + "    (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < count; i++) {
				pstmt.setString(1, RandomToolkit.generateString(4));
				pstmt.setString(2, RandomToolkit.generateString(5));
				pstmt.setInt(3, 1);
				pstmt.setInt(4, 1);
				pstmt.setString(5, RandomToolkit.generateString(6));
				// 加入批处理
				pstmt.addBatch();
			}
			pstmt.executeBatch(); // 执行批处理
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConnection();
		}
		Long end = System.currentTimeMillis();
		System.out.println("预处理批量执行" + count + "条Insert操作，共耗时：" + (end - start) / 1000f + "秒！");
	}

	/** 
	 * 批处理执行静态SQL测试 
	 * 批量执行Insert操作，共耗时：0.949秒！
	 */
	@Test
	public void test_StaticInsertBatch() throws Exception {
		Connection conn = null;
		Long start = System.currentTimeMillis();
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			for (int i = 0; i < 100; i++) {
				String sql = "\n" + "insert into demo.tab_common \n" + "\t(deptCode, \n" + "\tuserName, \n"
						+ "\tsex, \n" + "\tage, \n" + "\tpasswd)\n" + "\tvalues\n" + "\t('"
						+ RandomToolkit.generateString(3) + "', \n" + "\t'" + RandomToolkit.generateString(4) + "', \n"
						+ "\t0, \n" + "\t1, \n" + "\t'" + RandomToolkit.generateString(5) + "'" + ")";
				// 加入批处理
				stmt.addBatch(sql);
			}
			stmt.executeBatch(); // 执行批处理
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConnection();
		}
		Long end = System.currentTimeMillis();
		System.out.println("批量执行Insert操作，共耗时：" + (end - start) / 1000f + "秒！");
	}

	/** 
	* 预定义SQL模式，获取新增记录的主键 (获取最后插入MySQL记录的自增ID值)
	* @throws SQLException 
	* 
	* -----预定义SQL模式-----id = 6
	*/
	@Test
	public void test_insertWithParperedSQL() throws SQLException {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into demo.tab_common (deptCode,userName,sex,age,passwd) values(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "1006");
			pstmt.setString(2, "adanacLiu");
			pstmt.setInt(3, 1);
			pstmt.setInt(4, 0);
			pstmt.setString(5, "mypwd");
			pstmt.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				// 知其仅有一列，故获取第一列
				Long id = rs.getLong(1);
				System.out.println("-----预定义SQL模式-----id = " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConnection();
		}
	}

	/** 
	 * 静态SQL模式，获取新增记录的主键 (获取最后插入MySQL记录的自增ID值)
	 * @throws SQLException 
	 */
	@Test
	public void test_insertWithStaticSQL() throws SQLException {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into demo.tab_common (id,deptCode,userName,sex,age,passwd) values('4','1004','Frank',0,1,'frpwd')";
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			// 检索由于执行此 Statement 对象而创建的所有自动生成的键
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				// 知其仅有一列，故获取第一列
				Long id = rs.getLong(1);
				System.out.println("-----静态SQL模式-----id = " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConnection();
		}
	}
}
