package instrumentos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.InstrumentoException;
import usuarios.Conservador;

public class GestorInstrumentos {

	private Map<Integer, Instrumento> mapaInstrumentos;

	public GestorInstrumentos() {
			mapaInstrumentos = new HashMap<>();
		}
	public Instrumento crearInstrumento(Familia familia, TipoInstrumento tipo, String marca, String modelo,
			Tamano tamaño, String comentario) throws InstrumentoException {
		int identificador = mapaInstrumentos.size();
		Instrumento i; 
		if (mapaInstrumentos.containsKey(identificador)) 
			throw new InstrumentoException("Instrumento ya existente");
		else {
			Estado estado = Estado.PENDIENTE_REVISION;
			i = new Instrumento(familia, tipo, marca, modelo, tamaño, identificador, estado, comentario);

			mapaInstrumentos.put(identificador, i);			
		}
		return i;
	}
	public void cerrarRevisionInstrumento (int identificador, Estado estado, String comentario) throws InstrumentoException{
		Instrumento instrumento = mapaInstrumentos.get(identificador);
		instrumento.cerrarRevisionInstrumento(estado, comentario);
	}
	public List<Instrumento> asignarRevision (Conservador conservador, TipoInstrumento tipoInstrumento) {
		int identificador = 0;
		List<Instrumento> linsRev = new ArrayList<>();
		Instrumento instrumento;
		Estado estado;
		TipoInstrumento tipo;
		while (mapaInstrumentos.containsKey(identificador)) {
			instrumento = mapaInstrumentos.get(identificador);
			estado = instrumento.getEstado();
			if (estado==Estado.PENDIENTE_REVISION) {
				tipo = instrumento.getTipo();
				if (tipo==tipoInstrumento) {
					instrumento.crearRevision(conservador);
					linsRev.add(instrumento);
				}
			}
			identificador++;
		}
		return linsRev;
	}
}
