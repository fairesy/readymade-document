package com.readymade.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.readymade.model.User;

@Repository
public class UserDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public User insert(User user) throws Exception{
		KeyHolder holder = new GeneratedKeyHolder();

		String sql = "INSERT INTO user (email, password, name) VALUES (:email, :password, :name)";
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
		jdbcTemplate.update(sql, namedParameters, holder);

		Integer userId = holder.getKey().intValue();
		user.setId(userId);
		return user;
	}
}