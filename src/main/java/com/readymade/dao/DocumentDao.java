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

import com.readymade.model.Document;


@Repository
public class DocumentDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public Document insert(Document document) throws Exception{
		KeyHolder holder = new GeneratedKeyHolder();

		String sql = "INSERT INTO document (type, user_id) VALUES (:type, :user_id)";
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(document);
		jdbcTemplate.update(sql, namedParameters, holder);

		Integer documentId = holder.getKey().intValue();
		document.setId(documentId);
		return document;
	}

	public Document findByUserId(int userId) {
		String sql = "SELECT * FROM document WHERE user_id = :userId";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("userId", userId);
	    return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Document>(Document.class)));
	}
}
