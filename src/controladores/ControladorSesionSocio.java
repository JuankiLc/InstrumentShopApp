package controladores;

import eventos.GestorDonaciones;
import excepciones.UsuarioException;
import usuarios.GestorUsuarios;
import usuarios.Socio;

public class ControladorSesionSocio  implements ControladorSesion{
	private Socio socio = null;
	private GestorUsuarios gu;
	private GestorDonaciones gd;
	
	public ControladorSesionSocio(GestorUsuarios gu, GestorDonaciones gd) {
		super();
		this.gu = gu;
		this.gd = gd;
	}
	public void identificarUsuario(String login, String clave) throws UsuarioException{
		boolean socioValido;
		socioValido = gu.validarUsuario(login, clave);
		if (socioValido==false) 
			throw new UsuarioException("Socio no reconocido");
		else {
			socio = (Socio) gu.getUsuario(login); 
		}
	}
	public void verMisDonaciones() throws UsuarioException{
		if (socio==null) 
			throw new UsuarioException("Inicia sesion primero");
		else {
			gd.verMisDonaciones(socio.getLogin());
		}	
	}
	
	public void cerrarSesion() {
		socio = null;
	}
}

