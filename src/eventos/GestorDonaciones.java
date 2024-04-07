package eventos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.DonacionException;
import instrumentos.Instrumento;
import usuarios.Socio;

public class GestorDonaciones {

	private Map<Integer, Donacion> mapaDonaciones;

	public GestorDonaciones() {
		mapaDonaciones = new HashMap<>();
	}

	public void crearDonacion(Socio socio, Instrumento instrumento) throws DonacionException {// saber si login existe??
		int idDonacion = mapaDonaciones.size();
		Date fecha = new Date();
		if (mapaDonaciones.containsKey(idDonacion))
			throw new DonacionException("Donacion ya existente");
		else {
			Donacion d;
			d = new Donacion(socio, instrumento, fecha);
			mapaDonaciones.put(idDonacion, d);
		}
	}

	public void verMisDonaciones(String login) {
		List<Donacion> ldon = new ArrayList<>();
		int i = 0;
		Donacion don;
		Instrumento ins;
		Socio socio;
		while (mapaDonaciones.get(i) != null) {
			don = mapaDonaciones.get(i);
			socio = don.getSocio();
			if (socio.getLogin() == login)
				ldon.add(don);
			i++;
		}
		i = 0;
		while (ldon.get(i) != null) {
			don = ldon.get(i);
			ins = don.getInstrumento();
			socio = don.getSocio();
			System.out.println("Donacion " + i + "\nDonante: " + socio.getLogin() + "\nFecha: " + don.getFecha()
					+ "\nInstrumento: " + ins.getTipo());
			i++;
			if (i == ldon.size()) {
				break;
			}
		}
	}
}
