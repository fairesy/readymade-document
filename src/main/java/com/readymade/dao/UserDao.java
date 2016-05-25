package com.readymade.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

		String sql = "INSERT INTO user (email, password) VALUES (:email, :password)";
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
		jdbcTemplate.update(sql, namedParameters, holder);

		Integer userId = holder.getKey().intValue();
		user.setId(userId);
		return user;
	}
	
	public User findByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email = :email";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("email", email);
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<User>(User.class)));
	}
}
