package eventos;

import java.util.Date;

import instrumentos.Estado;
import instrumentos.Instrumento;
import usuarios.Conservador;

public class Revision {
private boolean pendiente;
private Estado estado;
private Date fechaInicio, fechaFin;
private int idRevision;
private Conservador conservador;
private String comentario;
private Instrumento instrumento;



public Revision(boolean pendiente, Estado estado, Date fechaInicio, Date fechaFin, int idRevision,
		Conservador conservador, String comentario, Instrumento instrumento) {
	super();
	this.pendiente = pendiente;
	this.estado = estado;
	this.fechaInicio = fechaInicio;
	this.fechaFin = fechaFin;
	this.idRevision = idRevision;
	this.conservador = conservador;
	this.comentario = comentario;
	this.instrumento = instrumento;
}
public int cerrarRevision(String comentario, Estado estado) {
	Date fechaFin = new Date();
	this.setPendiente(false);
	this.setComentario(comentario);	 
	this.setFechaFin(fechaFin);
	this.setEstado(estado);
	Instrumento instrumento = this.getInstrumento();
	return instrumento.getIdentificador();
}
public int getIdRevision() {
	return idRevision;
}
public Estado getEstado() {
	return estado;
}

public void setEstado(Estado estado) {
	this.estado = estado;
}

public boolean isPendiente() {
	return pendiente;
}

public void setPendiente(boolean pendiente) {
	this.pendiente = pendiente;
}

public Date getFechaInicio() {
	return fechaInicio;
}
public Date getFechaFin() {
	return fechaFin;
}
public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
}
public Conservador getConservador() {
	return conservador;
}
public String getComentario() {
	return comentario;
}
public void setComentario(String comentario) {
	this.comentario = comentario;
}

public Instrumento getInstrumento() {
	return instrumento;
}

public void setInstrumento(Instrumento instrumento) {
	this.instrumento = instrumento;
}
}
