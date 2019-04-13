package com.demo.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcDaoSupportImpl extends JdbcDaoSupport {

	
	public int getCircleCount()
	{
		return this.getJdbcTemplate().queryForInt("Select Count(*) from circle");
	}
}
