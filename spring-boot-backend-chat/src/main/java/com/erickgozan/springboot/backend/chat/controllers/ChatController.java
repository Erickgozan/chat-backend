package com.erickgozan.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.erickgozan.springboot.backend.chat.model.documents.Mensaje;
import com.erickgozan.springboot.backend.chat.model.service.IChatService;

@Controller
public class ChatController {

	private String colores[] = { "red", "green", "blue", "orange", "magenta", "purple" };
	
	@Autowired
	private IChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSoket;

	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje") // Enviar el mensaje a los usuarios que estan suscritos
	public Mensaje recibeMensaje(Mensaje mensaje) {
		
		mensaje.setFecha(new Date().getTime());
		
		if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColores(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo usuario");
		}else {
			chatService.guardar(mensaje);
		}
		return mensaje;
	}

	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String escribiendo(String username) {
		return username.concat(" esta escribiendo..");
	}
	
	@MessageMapping("/historial")
	public void historial(String clienteId) {
		webSoket.convertAndSend("/chat/historial/"+clienteId,chatService.obtenerUltimos10Mensajes());
	}

}
