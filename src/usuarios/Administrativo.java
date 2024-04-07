package usuarios;

public class Administrativo extends Usuario{
private String ubicacion;

public Administrativo(String login, String nombre, String apellidos,
			String email, String dni, String clave, int numeroMovil, String ubicacion){
		super(login, nombre, apellidos, email, dni, clave, numeroMovil);
		this.ubicacion = ubicacion;
	}

public String getUbicacion() {
	return ubicacion;
}

public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
}

public String toString() {
	return super.toString() 
		+ "\n Despacho: " + getUbicacion() 
		+ "\n Tipo: administrativo";
}

}
