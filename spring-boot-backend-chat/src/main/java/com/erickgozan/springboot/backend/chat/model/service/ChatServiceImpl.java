package com.erickgozan.springboot.backend.chat.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickgozan.springboot.backend.chat.model.dao.IChatDao;
import com.erickgozan.springboot.backend.chat.model.documents.Mensaje;

@Service
public class ChatServiceImpl implements IChatService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ChatServiceImpl.class);
	
	@Autowired
	private IChatDao iChatDao;
	
	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		LOG.debug("Consulata " + iChatDao.findFirst10ByOrderByFechaDesc());
		return iChatDao.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		return iChatDao.save(mensaje);
	}

}
