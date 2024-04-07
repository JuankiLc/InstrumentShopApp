package controladores;

import java.util.Map;

import eventos.Revision;
import excepciones.InstrumentoException;
import excepciones.UsuarioException;
import instrumentos.Estado;
import instrumentos.GestorInstrumentos;
import instrumentos.Instrumento;
import instrumentos.TipoInstrumento;
import usuarios.Conservador;
import usuarios.GestorUsuarios;

import java.util.HashMap;
import java.util.List;

public class ControladorSesionConservador implements ControladorSesion {
	private Conservador conservador = null;
	private GestorUsuarios gu;
	private GestorInstrumentos gi;

	public ControladorSesionConservador(GestorUsuarios gu, GestorInstrumentos gi) {
		this.gu = gu;
		this.gi = gi;
	}

	public void identificarUsuario(String login, String clave) throws UsuarioException {
		boolean conservadorValido;
		conservadorValido = gu.validarUsuario(login, clave);
		if (conservadorValido == false)
			throw new UsuarioException("Conservador no reconocido");
		else {
			conservador = (Conservador) gu.getUsuario(login);
		}
	}

	public void verMisRevisiones() throws UsuarioException { // saca las revisiones de un conservador por pantalla
		Map<Integer, Revision> mapaRevisiones = new HashMap<>();
		Revision revision;
		Conservador cons;
		Instrumento ins;
		int i = 0;
		if (conservador == null)
			throw new UsuarioException("Inicia sesion primero");
		else {
			mapaRevisiones = conservador.getMisRevisiones();
			if (mapaRevisiones.size() == 0) {
				System.out.println("No hay revisiones pendientes");
			} else {
				while (mapaRevisiones.get(i) != null) {
					revision = mapaRevisiones.get(i);
					cons = revision.getConservador();
					ins = revision.getInstrumento();
					System.out.println(
							"Revision nº: " + revision.getIdRevision() + "\nPendiente: " + revision.isPendiente()
									+ "\nEstado instrumento: " + revision.getEstado() + "\nFecha de inicio: "
									+ revision.getFechaInicio() + "\nFecha de fin: " + revision.getFechaFin()
									+ "\nConservador: " + cons.getLogin() + "\nInstrumento: " + ins.getTipo());
					i++;
				}
			}

		}
	}

	public void registrarRevisionInstrumento(int identificadorRev, Estado estado, String comentario)
			throws UsuarioException {
		if (conservador == null)
			throw new UsuarioException("Inicia sesion primero");
		else {
			try {
				int identificador = conservador.cerrarRevision(identificadorRev, estado, comentario);
				gi.cerrarRevisionInstrumento(identificador, estado, comentario);
				
				System.out.println("La revisión "+identificadorRev+" ha sido un éxito!");
			} catch (InstrumentoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void addCapacidadRevision(TipoInstrumento tipo) throws UsuarioException {
		int i = 0;
	
		Instrumento instrumento;
		if (conservador == null)
			throw new UsuarioException("Inicia sesion primero");
		else {
			conservador.addCapacidadRevision(tipo);
			List<Instrumento> linsRev = gi.asignarRevision(conservador, tipo);
			if (linsRev.size() == 0) {
				System.out.println("No hay instrumentos para revisar del tipo "+tipo);
			} else {
				while (linsRev.get(i) != null) {
					instrumento = linsRev.get(i);
					conservador.crearRevision(instrumento);
					i++;
					if(i == linsRev.size()) {
						break;
					}
				}
			}
		}
	}

	public void cerrarSesion() {
		conservador = null;
	}
}
