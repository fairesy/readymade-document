package com.readymade.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.readymade.model.Module;

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
	
}
