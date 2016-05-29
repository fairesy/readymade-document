package com.readymade.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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

	public Document findByUserId(int user_id) {
		String sql = "SELECT * FROM document WHERE user_id = :user_id";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", Integer.valueOf(user_id));
	    
	    RowMapper<Document> rm = new RowMapper<Document>() {
			@Override
			public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Document(rs.getString("type"), rs.getInt("user_id"));
			}
        };
	    
        
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, rm));
	}

	public Document findResumeByUserId(Integer user_id) {
		String sql = "SELECT * FROM document WHERE user_id = :user_id AND type = 'resume_default'";
		SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", user_id);
		
		RowMapper<Document> rm = new RowMapper<Document>() {
			@Override
			public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Document(rs.getString("type"), rs.getInt("user_id"));
			}
		};
		
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, rm));
	}

}
