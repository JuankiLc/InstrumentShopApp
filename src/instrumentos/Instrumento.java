package instrumentos;

import java.util.ArrayList;
import java.util.Date;

import eventos.Revision;
import excepciones.InstrumentoException;
import usuarios.Conservador;
 
public class Instrumento {
private String marca, modelo;
private TipoInstrumento tipo;
private Familia familia;
private Tamano tamaño;
private int identificador;
private Estado estado;
private ArrayList<String> comentarios = new ArrayList<String>();
private ArrayList<Revision> revisiones = new ArrayList<Revision>();



public Instrumento(Familia familia, TipoInstrumento tipo, String marca, String modelo,
		Tamano tamaño, int identificador, Estado estado,
		String comentario) {
	this.familia = familia;
	this.tipo = tipo;
	this.marca = marca;
	this.modelo = modelo;
	this.tamaño = tamaño;
	this.identificador = identificador;
	this.estado = estado;
	this.comentarios.add(comentario);
	//meto aqui crearrevision o fuera
}

public Revision crearRevision(Conservador conservador) {
	Revision revision;
	Date fechaInicio = new Date();
	int identificador = revisiones.size();
	Estado estado = Estado.PENDIENTE_REVISION;
	revision = new Revision(true, estado, fechaInicio, null, identificador, conservador, null, this);
	this.revisiones.add(revision);
	return revision;
}

public void cerrarRevisionInstrumento(Estado estado, String comentario) throws InstrumentoException{
	int ultimo = revisiones.size();
	Revision revision = revisiones.get(ultimo-1);
	if (revision.isPendiente()==false) 
		throw new InstrumentoException("El instrumento no tiene una revisión pendiente.");
	else {
		revision.cerrarRevision(comentario, estado);
		this.setEstado(estado);
		this.addComentario(comentario);
	}
}


public ArrayList<Revision> getRevisiones() {
	return revisiones;
}
public Familia getFamilia() {
	return familia;
}
public TipoInstrumento getTipo() {
	return tipo;
}
public String getMarca() {
	return marca;
}
public String getModelo() {
	return modelo;
}
public Tamano getTamaño() {
	return tamaño;
}
public int getIdentificador() {
	return identificador;
}
public Estado getEstado() {
	return estado;
}
public void setEstado(Estado estado) {
	this.estado = estado;
}

public void addComentario(String comentario) {
	this.comentarios.add(comentario);
}
}
