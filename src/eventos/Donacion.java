package eventos;

import java.util.Date;

import instrumentos.Instrumento;
import usuarios.Socio;

public class Donacion {
private Socio socio;
private Instrumento instrumento;
private Date fecha;


public Donacion(Socio socio, Instrumento instrumento, Date fecha) {
	super();
	this.socio = socio;
	this.instrumento = instrumento;
	this.fecha = fecha;
}
public Socio getSocio() {
	return socio;
}
public Instrumento getInstrumento() {
	return instrumento;
}
public Date getFecha() {
	return fecha;
}
}
