package com.bjqf.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public interface RowMapper {
	/**
	 * 获取查询结果集的抽象方法
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public abstract Object rowMapper(ResultSet rs) throws SQLException;
}
