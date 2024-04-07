package controladores;

import excepciones.UsuarioException;

public interface ControladorSesion {
	void identificarUsuario(String login, String clave) throws UsuarioException;
	void cerrarSesion();
}
