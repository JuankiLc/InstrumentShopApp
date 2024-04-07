package controladores;

import java.util.List;

import eventos.GestorDonaciones;
import excepciones.DonacionException;
import excepciones.InstrumentoException;
import excepciones.UsuarioException;
import instrumentos.Familia;
import instrumentos.GestorInstrumentos;
import instrumentos.Instrumento;
import instrumentos.Tamano;
import instrumentos.TipoInstrumento;
import usuarios.Administrativo;
import usuarios.Conservador;
import usuarios.GestorUsuarios;
import usuarios.Socio;
import usuarios.Usuario;

public class ControladorSesionAdministrativo implements ControladorSesion {
	private Administrativo administrativo = null;
	private GestorUsuarios gu;
	private GestorInstrumentos gi;
	private GestorDonaciones gd;

	public ControladorSesionAdministrativo(GestorUsuarios gu, GestorInstrumentos gi, GestorDonaciones gd) {
		super();
		this.gu = gu;
		this.gi = gi;
		this.gd = gd;
	}

	public void crearAdministrattivo(String login, String nombre, String apellidos, String email, String dni,
			String clave, int numeroMovil, String ubicacion) throws UsuarioException {
		if (administrativo == null)
			throw new UsuarioException("Autenticación requerida");
		// creo usuario
		gu.crearUsuario(login, nombre, apellidos, email, dni, clave, numeroMovil, null, ubicacion, "Administrativo");
	}

	public void crearConservador(String login, String nombre, String apellidos, String email, String dni, String clave,
			int numeroMovil, String ubicacion) throws UsuarioException {
		if (administrativo == null)
			throw new UsuarioException("Autenticación requerida");
		// creo usuario
		gu.crearUsuario(login, nombre, apellidos, email, dni, clave, numeroMovil, null, ubicacion, "Conservador");
	}

	public void crearSocio(String login, String nombre, String apellidos, String email, String dni, String clave,
			int numeroMovil, String curso) throws UsuarioException {
		if (administrativo == null)
			throw new UsuarioException("Autenticación requerida");
		// creo usuario
		gu.crearUsuario(login, nombre, apellidos, email, dni, clave, numeroMovil, curso, null, "Socio");
	}

	public void identificarUsuario(String login, String clave) throws UsuarioException {
		boolean adminValido;
		adminValido = gu.validarUsuario(login, clave);
		if (adminValido == false)
			throw new UsuarioException("Administrativo no reconocido");
		else {
			administrativo = (Administrativo) gu.getUsuario(login);
		}
	}

	public List<Usuario> listarUsuariosTipo(String tipoUsuario) throws UsuarioException {
		return gu.listarUsuariosTipo(tipoUsuario);

	}

	public void donarInstrumento(TipoInstrumento tipo, Familia familia, Tamano tamaño, String marca, String modelo,
			String comentario, String login) throws UsuarioException, InstrumentoException, DonacionException {
		int i = 0;
		if (administrativo == null)
			throw new UsuarioException("Inicia sesion primero");
		else {

			Instrumento instrumento = gi.crearInstrumento(familia, tipo, marca, modelo, tamaño, comentario);
			Socio socio = (Socio) gu.getUsuario(login);
			if (socio == null) {
				throw new UsuarioException("Usuario no existe");
			} else {
				gd.crearDonacion(socio, instrumento);
				List<Usuario> lus = gu.listarUsuariosTipo("Conservador");
				Conservador conservador;
				boolean puedeRevisar;
				while (lus.get(i) != null) {
					conservador = (Conservador) lus.get(i);
					puedeRevisar = conservador.puedeRevisar(tipo);
					if (puedeRevisar) {
						System.out.println(conservador.getLogin() + " puede revisar " + tipo);
						conservador.crearRevision(instrumento);
						instrumento.crearRevision(conservador);
						break;
					}
					i++;
					if (i == lus.size()) {
						break;
					}
				}
				if (i == lus.size()) {
					System.out.println("Nadie puede revisar el "+tipo);
				}
			}

		}
	}

	public void cerrarSesion() {
		administrativo = null;
	}
}
