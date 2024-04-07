package excepciones;

public class DonacionException extends Exception{
private static final long serialVersionUID = 1L;
	
	public DonacionException(String causa) {
		super(causa);
	}
}