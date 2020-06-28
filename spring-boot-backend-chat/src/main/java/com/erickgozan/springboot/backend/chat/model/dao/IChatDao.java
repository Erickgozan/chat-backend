package com.erickgozan.springboot.backend.chat.model.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.erickgozan.springboot.backend.chat.model.documents.Mensaje;

public interface IChatDao extends MongoRepository<Mensaje, String>{
	
	public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
