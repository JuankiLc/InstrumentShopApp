package usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import eventos.Revision;
import excepciones.InstrumentoException;
import instrumentos.Estado;
import instrumentos.Instrumento;
import instrumentos.TipoInstrumento;

public class Conservador extends Usuario {
	private String ubicacion;
	private ArrayList<TipoInstrumento> capacidadesRevision = new ArrayList<TipoInstrumento>();
	private Map<Integer, Revision> mapaRevisiones;

	public Conservador(String login, String nombre, String apellidos, String email, String dni, String clave,
			int numeroMovil, String ubicacion) {
		super(login, nombre, apellidos, email, dni, clave, numeroMovil);
		this.ubicacion = ubicacion;
		mapaRevisiones = new HashMap<>();
	}

	public void crearRevision(Instrumento instrumento) {
		Revision revision;
		int identificador = mapaRevisiones.size();
		Date fechaInicio = new Date();
		Estado estado = Estado.PENDIENTE_REVISION;
		revision = new Revision(true, estado, fechaInicio, null, identificador, this, null, instrumento);
		mapaRevisiones.put(identificador, revision);
	}

	public int cerrarRevision(int identificadorRev, Estado estado, String comentario) throws InstrumentoException{
		Revision revision = mapaRevisiones.get(identificadorRev);
		if (revision == null) {
			throw new InstrumentoException("La revisión "+identificadorRev+" no está asignada a "+getLogin());
		}else if (estado != Estado.BAJA && estado != Estado.DISPONIBLE){
			throw new InstrumentoException("El estado "+estado+" no es válido.");
		}else {
			int identificador = revision.cerrarRevision(comentario, estado);
			return identificador;
		}
	}

	public boolean puedeRevisar(TipoInstrumento tipo) {
		int i = 0;
		boolean resul = false;
		if (capacidadesRevision.size() != 0) {
			while (capacidadesRevision.get(i) != null) {
				if (tipo.equals(capacidadesRevision.get(i))) {
					resul = true;
					break;
				}
				i++;
				if (i == capacidadesRevision.size()) {
					break;
				}
			}
		}
		return resul;
	}

	public Map<Integer, Revision> getMisRevisiones() {
		return mapaRevisiones;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public ArrayList<TipoInstrumento> getCapacidadesRevision() {
		return capacidadesRevision;
	}

	public void setCapacidadesRevision(ArrayList<TipoInstrumento> capacidadesRevision) {
		this.capacidadesRevision = capacidadesRevision;
	}

	public void addCapacidadRevision(TipoInstrumento capacidadRevision) {
		this.capacidadesRevision.add(capacidadRevision);
	}

	public String toString() {
		return super.toString() + "\n Despacho: " + getUbicacion() + "\n Tipo: conservador";
	}

}
