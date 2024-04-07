package usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.UsuarioException;

public class GestorUsuarios {

	private Map<String, Usuario> mapaUsuarios;

public GestorUsuarios() {
		mapaUsuarios = new HashMap<>();
	}	
	
public void crearUsuario(String login, String nombre, String apellidos, String email, 
		String dni, String clave, int numeroMovil, String curso, String ubicacion, String tipoUsuario) 
			throws UsuarioException {
		if (mapaUsuarios.containsKey(login)) 
			throw new UsuarioException("Login ya existe");
		else {
			Usuario u; 
			switch(tipoUsuario) {				
			case "Socio":
				u = new Socio(login, nombre, apellidos, email, dni, clave, numeroMovil, curso);
				break;
			case "Conservador":
				u = new Conservador(login, nombre, apellidos, email, dni, clave, numeroMovil, ubicacion);
				break;
			case "Administrativo":
				u = new Administrativo(login, nombre, apellidos,
						email, dni, clave, numeroMovil, ubicacion);
				break;
			default: 
				throw new UsuarioException("Tipo de usuario \""+tipoUsuario+"\" incorrecto");
			}
			mapaUsuarios.put(login, u);			
		}
	}

public boolean validarUsuario(String login, String clave) {
		Usuario u = mapaUsuarios.get(login);
		if (u == null)
			return false;
		else 
			return clave.equals(u.getClave());
	}

public Usuario getUsuario(String login) {
		return mapaUsuarios.get(login);
	}

public List<Usuario> listarUsuariosTipo(String tipoUsuario) throws UsuarioException {

		List<Usuario> lus = new ArrayList<>();		

		for (Usuario us : mapaUsuarios.values()) {

			switch(tipoUsuario) {				
			case "Socio":
				if (us instanceof Socio)
					lus.add(us);
				break;
			case "Conservador":
				if (us instanceof Conservador)
					lus.add(us);
				break;
			case "Administrativo":
				if (us instanceof Administrativo)
					lus.add(us);
				break;
			default:
				throw new UsuarioException("Tipo de usuario \""+tipoUsuario+"\" incorrecto");
			}				
		}			

		return lus;
	}

}
