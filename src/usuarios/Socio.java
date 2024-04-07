package usuarios;

public class Socio extends Usuario{
private String curso;

public Socio(String login, String nombre, String apellidos,
			String email, String dni, String clave, int numeroMovil, String curso){
		super(login, nombre, apellidos, email, dni, clave, numeroMovil);
		this.curso = curso;
		}

public String getCurso() {
	return curso;
}

public void setCurso(String curso) {
	this.curso = curso;
}

public String toString() {	
	return super.toString() 
		+ "\n Curso: " + getCurso()
		+ "\n Tipo: socio";
}
}
