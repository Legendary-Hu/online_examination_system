package com.bjqf.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjqf.mapper.RowMapper;

public class JDBCUtil {
	//JDBC常量
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/exampro";
	private static final String USER="root";//用户名
	private static final String PWD = "root";//密码
	/**
	 * 公共的增删改方法
	 * @param sql
	 * @param params
	 */
	public static int executeUpdate(String sql,Object...params){
		int num = 0;
		try {
			//加载驱动
			Class.forName(DRIVER);
			//创建连接
			Connection conn = DriverManager.getConnection(URL,USER,PWD);
			//预编译sql语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			//ִ执行sql语句
			num = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * 封装的查询方法
	 * @param sql
	 * @param rowMapper
	 * @param params
	 * @return
	 */
	
	public static List executeQuery(String sql,RowMapper rowMapper,Object...params){
		//创建集合
		List list = new ArrayList();
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//获取结果集中的数据
				//匿名对象只能调用一次方法
				Object obj = rowMapper.rowMapper(rs);
				//将数据添加到结果集中
				list.add(obj);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 公共的给占位符赋值的方法
	 * @param pstmt
	 * @param params
	 * @throws SQLException
	 */
	public static void setParams(PreparedStatement pstmt,Object...params) throws SQLException{
		//给占位符赋值，由于查询操作可能不存在占位符，如查询所有数据
		if(params != null){
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject((i+1), params[i]);
			}
		}
	}
}
