package com.readymade.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.readymade.model.Module;

@Transactional
@Repository
public class ModuleDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public Module insert(Module module) throws Exception{
		KeyHolder holder = new GeneratedKeyHolder();

		String sql = "INSERT INTO module (type, data, document_id) VALUES (:type, :data, :document_id)";
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(module);
		jdbcTemplate.update(sql, namedParameters, holder);

		Integer moduleId = holder.getKey().intValue();
		module.setId(moduleId);
		return module;
	}

	@Transactional(readOnly=true)
	public Module findByDocumentId(Integer document_id, String type) {
		String sql = "SELECT * FROM module WHERE type = '"+type+"' AND document_id = :document_id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("document_id", Integer.valueOf(document_id));
				
		RowMapper<Module> rm = new RowMapper<Module>() {
			@Override
			public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Module(rs.getString("type"), rs.getString("data"), rs.getInt("document_id"));
			}
		};
		
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, rm));
				
	}

	public void update(Module module) {
		String sql = "UPDATE module SET data = :data WHERE document_id = :document_id AND type = :type";
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(module);
		jdbcTemplate.update(sql, namedParameters);
	}

	
}
