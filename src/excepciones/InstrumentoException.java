package excepciones;

public class InstrumentoException extends Exception{
private static final long serialVersionUID = 1L;
	
	public InstrumentoException(String causa) {
		super(causa);
	}
}
