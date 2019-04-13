package com.demo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.demo.model.Circle;

@Component
public class JdbcDaoImpl 
{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemp;
	private NamedParameterJdbcTemplate namedParamJdbctemp;
	
	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}

	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemp = new JdbcTemplate(dataSource);
		this.namedParamJdbctemp = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class CircleMapper implements RowMapper<Circle>
	{

		@Override
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		{
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("id"));
			circle.setName(resultSet.getString("name"));
			return circle;
		}
		
	}
	
	public int getCircleCount()
	{
		return jdbcTemp.queryForInt("Select Count(*) from circle");
	}
	
	public String getCircleName(int circleId)
	{
		return jdbcTemp.queryForObject("Select name from circle where id = ?", new Object[] {circleId}, String.class);
	}
	
	public Circle getCircleById(int circleId)
	{
		return jdbcTemp.queryForObject("Select * from circle where id = ?", new Object[] {circleId}, new CircleMapper());
	}
	
	public List<Circle> getrAllCircles()
	{
		return jdbcTemp.query("Select * from circle", new CircleMapper());
	}

	public String insertCircle(Circle circle)
	{
		jdbcTemp.update("Insert into circle(id, name) values(?, ?)", new Object[] {circle.getId(), circle.getName()});
		return "Inserted Successfully";
	}
	
	/*
	 * We DDL commands using JDBC Template class by execute method. Refer method below
	 */
	 public String createTriangleTable()
	 {
		 jdbcTemp.execute("Create table triangle(id integer, name varchar(20))");
		 return "Triangle table created";
	 }
	 
	 /*
	  * JdbcTemplate class cannot use other place holders than '?'.
	  * But, we can other class named 'NamedParameterJdbTemplate' which accepts named parameters place holders. 
	  */
	 public String insertCirclebyNamedParameters(Circle circle)
	 {
		 String sql = "Insert into circle(id, name) values(:id, :name)";
		 SqlParameterSource namedParams = new MapSqlParameterSource("id", circle.getId()).addValue("name", circle.getName());
		 namedParamJdbctemp.update(sql, namedParams);
		 return "Inserted Row By Named Params";
	 }
}
