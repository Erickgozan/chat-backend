package com.erickgozan.springboot.backend.chat.model.service;


import java.util.List;

import com.erickgozan.springboot.backend.chat.model.documents.Mensaje;


public interface IChatService {

	public List<Mensaje> obtenerUltimos10Mensajes();

	public Mensaje guardar(Mensaje mensaje);

}
