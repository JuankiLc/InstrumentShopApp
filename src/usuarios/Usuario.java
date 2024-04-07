package usuarios;

public abstract class Usuario {
private String login, nombre, apellidos, email, dni, clave;
private int numeroMovil;

public Usuario(String login, String nombre, String apellidos,
		String email, String dni, String clave, int numeroMovil) {
	this.login = login;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.email = email;
	this.dni = dni;
	this.clave = clave;
	this.numeroMovil = numeroMovil;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getNombre() {
	return nombre;
}

public String getApellidos() {
	return apellidos;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getDni() {
	return dni;
}

public String getClave() {
	return clave;
}

public void setClave(String clave) {
	this.clave = clave;
}

public int getNumeroMovil() {
	return numeroMovil;
}

public void setNumeroMovil(int numeroMovil) {
	this.numeroMovil = numeroMovil;
}

public String toString() {
	return "Usuario: " + getLogin() + 
		"\n Nombre: " + getNombre() + " " + getApellidos() +
		"\n Email: " + getEmail() +
		"\n DNI: " + getDni() +
		"\n Clave: " + getClave() + 
		"\n Numero: " + getNumeroMovil();
}


}

