package arranque;

import java.util.List;
import instrumentos.GestorInstrumentos;
import usuarios.GestorUsuarios;
import excepciones.*;
import controladores.*;
import eventos.GestorDonaciones;
import usuarios.Usuario;

import instrumentos.TipoInstrumento;
import instrumentos.Familia;
import instrumentos.Tamano;
import instrumentos.Estado;

public class PruebaIter1 {
	/**
	 * M�todo main(). No se esperan par�metros.
	 * 
	 * @param args par�metros por l�nea de comandos que no se tratan.
	 */
	public static void main(String[] args) {

		// *************************************
		// *******INICIALIZACION GESTORES*******
		// *************************************
		// Instancio el gestor de usuarios
		GestorUsuarios gu = new GestorUsuarios();
		// Creo administrativo inicial
		try {
			gu.crearUsuario("Rogelio Admin", "Rogelio", "Perez", "rogelio@email.com", "73849165G", "claveRogelio",
					675437845, null, "Despacho 5", "Administrativo");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		// Instancio el banco de instrumentos
		GestorInstrumentos gi = new GestorInstrumentos();
		GestorDonaciones gd = new GestorDonaciones();

		// *************************************
		// ****INICIALIZACION CONTROLADORES*****
		// *************************************
		// Instancio controladores de sesi�n
		ControladorSesionAdministrativo csadmin = new ControladorSesionAdministrativo(gu, gi, gd);
		ControladorSesionConservador cscons = new ControladorSesionConservador(gu, gi);
		ControladorSesionSocio cssoc = new ControladorSesionSocio(gu, gd); // s�lo se usa para el CU opcional

		System.out.println("////////////////////////////////////////////////////////");
		System.out.println("// CASOS DE USO ITERACI�N 0");
		System.out.println("////////////////////////////////////////////////////////\n");
		casosUsoAdminIter0(csadmin);

		System.out.println("\n\n////////////////////////////////////////////////////////");
		System.out.println("// CASOS DE USO ITERACI�N 1");
		System.out.println("////////////////////////////////////////////////////////");
		casosUsoConserIter1a(cscons);
		casosUsoAdminIter1(csadmin);
		casosUsoConserIter1b(cscons);
		casosUsoSocioIter1opt(cssoc);
	}

	/**
	 * M�todo que realiza los casos de uso de los administrativos de la iteraci�n 0
	 * 
	 * @param csadmin controlador de sesi�n de los administrativos
	 */
	private static void casosUsoAdminIter0(ControladorSesionAdministrativo csadmin) {
		System.out.println("/// CASOS DE USO ADMINISTRATIVO ITER 0///\n");

		// ------------------------------------
		// -- Usuario admin (ADMINISTRATIVO) --
		// ------------------------------------
		System.out.println("<<inicio sesi�n admin>>");
		System.out.println("(admin inicial creado en el main)\n");
		try {
			csadmin.identificarUsuario("Rogelio Admin", "claveRogelio");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		// *************************************
		// *********CREACION DE USUARIOS********
		// *************************************
		System.out.println("CREACION DE USUARIOS");
		try {
			System.out.println("admin crea tres conservadores");
			csadmin.crearConservador("cons0", "Nora", "Fernandez", "nora@email.com", "62837654H", "clave", 543297458,
					"Despacho 8");
			csadmin.crearConservador("cons1", "Maria", "Lopez", "maria@email.com", "62396654H", "clave", 549164458,
					"Despacho 9");
			csadmin.crearConservador("cons2", "Daniel", "Rodriguez", "daniel@email.com", "62897254H", "clave",
					543975458, "Despacho 7");
			System.out.println("admin crea tres socios");
			csadmin.crearSocio("socio0", "Raquel", "Mata", "raquel@email.com", "97428467K", "clave", 836025473,
					"Primero de piano");
			csadmin.crearSocio("socio1", "Jaime", "Jim�nez", "jaime@email.com", "781722441F", "clave", 667321448,
					"Segundo de solfeo");
			csadmin.crearSocio("socio2", "Blanca", "Torres", "blanca@email.com", "773199021W", "clave", 613930991,
					"Cuarto de guitarra");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		// *************************************
		// ************LISTAR USUARIOS**********
		// *************************************
		System.out.println("\nLISTAR USUARIOS");
		System.out.println("\nlista de conservadores:");
		try {
			List<Usuario> descs = csadmin.listarUsuariosTipo("Conservador");
			for (Usuario desc : descs)
				System.out.println(desc.toString() + "\n");
			System.out.println("hay " + descs.size() + " usuarios de tipo \"Conservador\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nlista de socios:");
		try {
			List<Usuario> descs = csadmin.listarUsuariosTipo("Socio");
			for (Usuario desc : descs)
				System.out.println(desc.toString() + "\n");
			System.out.println("hay " + descs.size() + " usuarios de tipo \"Socio\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nlista de administrativos:");
		try {
			List<Usuario> descs = csadmin.listarUsuariosTipo("Administrativo");
			for (Usuario desc : descs)
				System.out.println(desc.toString() + "\n");
			System.out.println("hay " + descs.size() + " usuarios de tipo \"Administrativo\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n<<cierre sesi�n admin>>");
		csadmin.cerrarSesion();
	}

	/**
	 * M�todo que realiza los casos de uso de los conservadores de la iteraci�n 1
	 * (primera parte)
	 * 
	 * @param cscons controlador de sesi�n de los conservadores
	 */
	private static void casosUsoConserIter1a(ControladorSesionConservador cscons) {
		System.out.println("\n/// CASOS DE USO CONSERVADOR ITER 1 - primera parte ///\n");

		// *************************************
		// *******REGISTRAR CAPACIDADES REVISI�N*****
		// *************************************
		System.out.println("REGISTRAR CAPACIDADES REVISI�N\n");

		// ------------------------------------
		// -- Usuario cons0 (CONSERVADOR) --
		// ------------------------------------
		System.out.println("<<inicio sesi�n cons0>>");
		try {
			cscons.identificarUsuario("cons0", "clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("cons0 indica que puede revisar violines y chelos");
		try {
			cscons.addCapacidadRevision(TipoInstrumento.VIOLIN);
			cscons.addCapacidadRevision(TipoInstrumento.CHELO);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesi�n cons0>>");
		cscons.cerrarSesion();

		// ------------------------------------
		// -- Usuario cons1 (CONSERVADOR) --
		// ------------------------------------
		System.out.println("\n<<inicio sesi�n cons1>>");
		try {
			cscons.identificarUsuario("cons1", "clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("cons0 indica que puede revisar violines y flautas traveseras");
		try {
			cscons.addCapacidadRevision(TipoInstrumento.VIOLIN);
			cscons.addCapacidadRevision(TipoInstrumento.FLAUTA_TRAVESERA);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesi�n cons1>>");
		cscons.cerrarSesion();
	}

	/**
	 * M�todo que realiza los casos de uso de los administrativos de la iteraci�n 1
	 * 
	 * @param csadmin controlador de sesi�n de los administrativos
	 */
	private static void casosUsoAdminIter1(ControladorSesionAdministrativo csadmin) {
		System.out.println("\n/// CASOS DE USO ADMINISTRATIVO ITER 1 ///\n");

		// ------------------------------------
		// -- Usuario admin (ADMINISTRATIVO) --
		// ------------------------------------
		System.out.println("<<inicio sesi�n admin>>");
		try {
			csadmin.identificarUsuario("Rogelio Admin", "claveRogelio");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		// *************************************
		// *******DONAR INSTRUMENTO*****
		// *************************************
		System.out.println("\nDONAR INSTRUMENTO");
		System.out.println("\nsocio0 dona varios instrumentos");
		try {
			csadmin.donarInstrumento(TipoInstrumento.VIOLIN, Familia.CUERDA_FROTADA, Tamano.PEQUENO, "Stentor",
					"SR1400", "gastado", "socio0");
			csadmin.donarInstrumento(TipoInstrumento.CHELO, Familia.CUERDA_FROTADA, Tamano.GRANDE, "Gewa", null,
					"incluye arco", "socio0");
			csadmin.donarInstrumento(TipoInstrumento.VIOLIN, Familia.CUERDA_FROTADA, Tamano.MEDIANO, "Yamaha", null,
					"como nuevo", "socio0");
			csadmin.donarInstrumento(TipoInstrumento.FLAUTA_TRAVESERA, Familia.VIENTO_MADERA, Tamano.MEDIANO,
					"Muramatsu", null, "sin funda", "socio0");
			System.out.println("admin intenta ahora registrar otro instrumento de un socio que no existe");
			csadmin.donarInstrumento(TipoInstrumento.FLAUTA_TRAVESERA, Familia.VIENTO_MADERA, Tamano.GRANDE,
					"Muramatsu", null, "verde", "socioNO");
		} catch (UsuarioException | InstrumentoException | DonacionException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nsocio1 dona m�s instrumentos");
		try {
			csadmin.donarInstrumento(TipoInstrumento.XILOFONO, Familia.PERCUSION, Tamano.MEDIANO, null, null, "lacado",
					"socio1");
			csadmin.donarInstrumento(TipoInstrumento.XILOFONO, Familia.PERCUSION, Tamano.GRANDE, "Yamaha", null,
					"sin lacar", "socio1");
			csadmin.donarInstrumento(TipoInstrumento.CHELO, Familia.CUERDA_FROTADA, Tamano.GRANDE, "ACME", "ACME 500",
					"marca acme", "socio1");
			csadmin.donarInstrumento(TipoInstrumento.VIOLIN, Familia.CUERDA_FROTADA, Tamano.PEQUENO, "Yamaha", null,
					"cuerda rota", "socio1");
		} catch (UsuarioException | InstrumentoException | DonacionException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n<<cierre sesi�n admin>>");
		csadmin.cerrarSesion();
	}

	/**
	 * M�todo que realiza los casos de uso de los conservadores de la iteraci�n 1
	 * (segunda parte)
	 * 
	 * @param cscons controlador de sesi�n de los conservadores
	 */
	private static void casosUsoConserIter1b(ControladorSesionConservador cscons) {
		System.out.println("\n/// CASOS DE USO CONSERVADOR ITER 1 - segunda parte ///\n");

		// *************************************
		// *******VER MIS REVISIONES*****
		// *************************************
		System.out.println("VER MIS REVISIONES + REGISTRAR REVISI�N");

		// ------------------------------------
		// -- Usuario cons0 (CONSERVADOR) --
		// ------------------------------------
		System.out.println("\n<<inicio sesi�n cons0>>");
		try {
			cscons.identificarUsuario("cons0", "clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons0 consulta sus revisiones pendientes");
		try {
			cscons.verMisRevisiones();
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons0 completa sus revisiones pendientes");
		try {
			cscons.registrarRevisionInstrumento(1, Estado.DISPONIBLE, "Lo he dejado perfecto");
			cscons.registrarRevisionInstrumento(6, Estado.DISPONIBLE, "La almohadilla est� un poco floja");
			System.out.println("(�sta fallar� porque no es una revisi�n asignada a �l)");
			cscons.registrarRevisionInstrumento(0, Estado.DISPONIBLE, null);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		try {
			cscons.registrarRevisionInstrumento(7, Estado.BAJA, "La carcoma ha podido con la madera");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons0 consulta sus revisiones pendientes y hechas");
		try {
			cscons.verMisRevisiones();
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesi�n cons0>>");
		cscons.cerrarSesion();

		// ------------------------------------
		// -- Usuario cons1 (CONSERVADOR) --
		// ------------------------------------
		System.out.println("\n<<inicio sesi�n cons1>>");
		try {
			cscons.identificarUsuario("cons1", "clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons1 consulta sus revisiones pendientes");
		try {
			cscons.verMisRevisiones();
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons1 completa sus revisiones");
		try {
			cscons.registrarRevisionInstrumento(0, Estado.DISPONIBLE, "Fino, fino");
			System.out.println("(�sta fallar� porque el estado tras la revisi�n no es v�lido)");
			cscons.registrarRevisionInstrumento(2, Estado.PRESTADO, null);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		try {
			cscons.registrarRevisionInstrumento(2, Estado.DISPONIBLE, null);
			cscons.registrarRevisionInstrumento(3, Estado.DISPONIBLE, "Lista para una orquesta");
			System.out.println("(�sta fallar� porque la revisi�n ya se hizo)");
			cscons.registrarRevisionInstrumento(2, Estado.DISPONIBLE, null);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons1 consulta sus revisiones pendientes y hechas");
		try {
			cscons.verMisRevisiones();
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesi�n cons1>>");
		cscons.cerrarSesion();

		// ------------------------------------
		// -- Usuario cons2 (CONSERVADOR) --
		// ------------------------------------
		System.out.println("\n<<inicio sesi�n cons2>>");
		try {
			cscons.identificarUsuario("cons2", "clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons2 consulta sus revisiones pendientes");
		// no tendr� nada asignado...
		try {
			cscons.verMisRevisiones();
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("cons2 indica que puede revisar xil�fonos");
		try {
			cscons.addCapacidadRevision(TipoInstrumento.XILOFONO);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\ncons2 consulta sus revisiones pendientes otra vez");
		System.out.println("(tendr� asignados todos los xil�fonos)");
		try {
			cscons.verMisRevisiones();
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesi�n cons2>>");
		cscons.cerrarSesion();
	}

	/**
	 * M�todo que realiza los casos de uso optativos de la iteraci�n 1
	 * 
	 * @param cssoc controlador de sesi�n de los socios
	 */
	private static void casosUsoSocioIter1opt(ControladorSesionSocio cssoc) {
		System.out.println("\n\n/// CASOS DE USO OPTATIVOS ITER 1 ///\n");

		System.out.println("VER DONACIONES\n");

		// ------------------------------------
		// -- Usuario socio1 (SOCIO) --
		// ------------------------------------
		System.out.println("<<inicio sesi�n socio1>>\n");
		try {
			cssoc.identificarUsuario("socio1", "clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("socio1 pide un listado de sus donaciones");
		try {
			cssoc.verMisDonaciones();
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesi�n socio1>>");
		cssoc.cerrarSesion();
	}
}
