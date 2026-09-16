package taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
	private static String[] listaNombreInscr = new String[100];
	private static String[] listaApellidoInscr = new String[100];
	private static String[] listaRutInscr = new String[100];
	private static String[] listaParaleloInscr = new String[100];

	private static String[] listaNombreSoli = new String[100];
	private static String[] listaApellidoSoli = new String[100];

	private static String[] listaNombreAdmi = new String[100];
	private static String[] listaApellidoAdmi = new String[100];
	private static String[] listaRutAdmi = new String[100];
	private static String[] listaParaleloAdmi = new String[100];

	private static String[] listaNombreRechazado = new String[100];
	private static String[] listaApellidoRechazado = new String[100];
	private static String[] listaRutRechazado = new String[100];
	private static String[] listaRazonRechazado = new String[100];

	private static int contInscritos = 0;
	private static int contRechazados = 0;
	private static int contSolicitudes = 0;
	private static int contAdmitidos = 0;
	private static Scanner s = new Scanner(System.in);

	/**
	 * mostrar el menu y llamar a la funcion dependiendo del caso
	 * 
	 */
	public static void main(String[] args) {
		String opcion;
		do {
			System.out.println("Sistema de control del grupo de poo" + "\n 1)Cargar archivos"
					+ "\n 2)Procesar solicitudes" + "\n 3)ingresar manual");

			System.out.print("Ingrese opcion");
			opcion = s.nextLine();

			switch (opcion) {
			case "1":
				procesarArchivos();
				System.out.println(contInscritos);
				System.out.println(contSolicitudes);

				break;

			case "2":
				procesarsolicitudes();
				System.out.println(contAdmitidos);
				System.out.println(contRechazados);

				break;
			case "3":
				inscripcionmanual();
				break;

			case "6":
				analisisEstadistico();
				break;
			default:
				System.out.println("Opcion invalida");
				break;

			}

		} while (opcion != "7");

	}

	private static void analisisEstadistico() {
		System.out.println("---Analisis estadistico---");

		double porcentaje = 0;

		if (contSolicitudes > 0) {
			double admitidos = 0;
			porcentaje = (contRechazados * 100) / contSolicitudes;
			admitidos = (contAdmitidos * 100) / contSolicitudes;

			System.out.println("Total de intentos de ingreso:" + contSolicitudes);
			System.out.println("Rechazados:" + contRechazados + " (" + porcentaje + "%)");
			System.out.println("Taza de admision:" + admitidos + "%");

		} else {
			System.out.println("Total de intentos de ingreso:" + contSolicitudes);
			System.out.println("Rechazados:" + contRechazados + " (" + porcentaje + "%)");
			System.out.println("Taza de admision:0%");
		}

		int contc1 = 0, contc2 = 0;
		for (int i = 0; i < contAdmitidos; i++) {
			if (listaParaleloAdmi[i].equals("C1")) {
				contc1++;

			} else {
				contc2++;
			}
			

		}
		System.out.println("Admitidos por paralelo C1:" + contc1);
		System.out.println("Admitidos por paralelo C2:" + contc2);
	}

	/**
	 * esta funcion hace control ingresando manualmente si es que ingresa por rut o
	 * nombre y tambien ve si hay espacio o no, si se acepta lo añade a admitidos y
	 * si no,a rechazados
	 * 
	 */
	private static void inscripcionmanual() {

		System.out.println("Ingrese opcion 1 o 2 " + "\n1. Por nombre completo" + "\n2. Por rut");

		String opcion;

		do {
			opcion = s.nextLine();
		} while (!opcion.equals("1") && (!opcion.equals("2")));

		if (opcion.equals("1")) {
			System.out.println("Ingrese nombre y apellido");
			System.out.print("nombre: ");
			String nombre = s.nextLine();
			System.out.print("apellido: ");
			String apellido = s.nextLine();

			boolean encontrado = false;

			for (int i = 0; i < contInscritos; i++) {
				if (listaNombreInscr[i].equals(nombre) && listaApellidoInscr[i].equals(apellido)) {
					if (contAdmitidos < 100) {
						listaNombreAdmi[contAdmitidos] = nombre;
						listaApellidoAdmi[contAdmitidos] = apellido;
						encontrado = true;
						String rut, paralelo;
						do {
							System.out.println("Ingresa el rut: ");
							rut = s.nextLine();
							System.out.println("Ingresa el paralelo: ");
							paralelo = s.nextLine();

						} while ((!paralelo.equals("C1") && (!paralelo.equals("C2"))) || rut.equals(""));

						listaRutAdmi[contAdmitidos] = rut;
						listaParaleloAdmi[contAdmitidos] = paralelo;
						contAdmitidos++;
					} else {
						System.out.print("No hay espacio ups");
					}
				}
			}

			if (!encontrado) {
				listaNombreRechazado[contRechazados] = nombre;
				listaApellidoRechazado[contRechazados] = apellido;
				listaRutRechazado[contRechazados] = "desconocido";
				listaRazonRechazado[contRechazados] = "No esta inscrito";

			}

		} else {
			System.out.println("Ingrese rut");
			System.out.print("rut: ");
			String rut = s.nextLine();

			boolean encontrado = false;
			for (int i = 0; i < contInscritos; i++) {
				if (listaRutInscr[i].equals(rut)) {
					if (contAdmitidos < 100) {
						listaRutAdmi[contAdmitidos] = rut;
						encontrado = true;
						String nombre, apellido, paralelo;
						do {
							System.out.println("Ingresa el nombre: ");
							nombre = s.nextLine();
							System.out.println("Ingresa el apellido: ");
							apellido = s.nextLine();
							System.out.println("Ingresa el paralelo: ");
							paralelo = s.nextLine();

						} while ((!paralelo.equals("C1") && (!paralelo.equals("C2"))) || nombre.equals("")
								|| apellido.equals(""));

						listaNombreAdmi[contAdmitidos] = nombre;
						listaApellidoAdmi[contAdmitidos] = apellido;
						listaParaleloAdmi[contAdmitidos] = paralelo;

						contAdmitidos++;
					} else {
						System.out.print("No hay espacio ups");
					}
				}

			}
			if (!encontrado) {
				listaNombreRechazado[contRechazados] = "desconocido";
				listaApellidoRechazado[contRechazados] = "desconocido";
				listaRutRechazado[contRechazados] = rut;
				listaRazonRechazado[contRechazados] = "No esta inscrito";
			}
		}

	}

	/**
	 * en esta funcion se compara la lista de solicitudes y ve si es que esta en la
	 * listaiscritos para ver si lo manda a listaadmitidos o listarechazados
	 */
	private static void procesarsolicitudes() {
		for (int i = 0; i < contSolicitudes; i++) {
			if (buscar(i)) {
				if (contAdmitidos >= 100) {
					System.out.println("Perdon no hay espacio en el grupo");

				} else {
					listaNombreAdmi[contAdmitidos] = listaNombreSoli[i];
					listaApellidoAdmi[contAdmitidos] = listaApellidoSoli[i];
					for (int e = 0; e < contInscritos; e++) {
						if ((listaNombreSoli[i].equals(listaNombreInscr[e])
								&& listaApellidoInscr[e].equals(listaApellidoSoli[i]))) {
							listaRutAdmi[contAdmitidos] = listaRutInscr[e];
							listaParaleloAdmi[contAdmitidos] = listaParaleloInscr[e];

						}
					}

					contAdmitidos++;
				}
			} else {
				listaNombreRechazado[contRechazados] = listaNombreSoli[i];
				listaApellidoRechazado[contRechazados] = listaApellidoSoli[i];
				listaRazonRechazado[contRechazados] = "Esta persona no pertenece a ningun paralelo";
				listaRutRechazado[contRechazados] = "";
				contRechazados++;
			}
		}

	}

	/**
	 * en esta funcion ve si es que el nombre esta en la lista de nombres y
	 * apellidos incritos
	 * 
	 * @param i indice que queremos buscar
	 * @return un booleano indicando si se encontro o no el elemento
	 */
	private static boolean buscar(int i) {
		String nombre = listaNombreSoli[i];
		String apellido = listaApellidoSoli[i];
		for (int e = 0; e < contInscritos; e++) {
			if ((nombre.equals(listaNombreInscr[e]) && listaApellidoInscr[e].equals(apellido))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * en esta funcion abre el archivo alumnos y guarda la info en las listas de
	 * inscritos y tambien abre el arxhivo solicitudes y guarda la info en las
	 * listas de solicitudes
	 * 
	 */
	private static void procesarArchivos() {
		try {
			File arch = new File("Alumnos.txt");
			Scanner lector = new Scanner(arch);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split(";");
				String nombre = partes[0];
				String apellido = partes[1];
				String rut = partes[2];
				String paralelo = partes[3];
				if (contInscritos >= 100) {
					System.out.println("Se lleno el espacioooooooooo");
				} else {

					listaNombreInscr[contInscritos] = nombre;
					listaApellidoInscr[contInscritos] = apellido;
					listaRutInscr[contInscritos] = rut;
					listaParaleloInscr[contInscritos] = paralelo;

					contInscritos++;

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No existe el archivo Alumnooooooooooooooooooooooooos");

		}

		try {
			File arch = new File("Solicitudes.txt");
			Scanner lector = new Scanner(arch);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split("-");
				String nombre = partes[0];
				String apellido = partes[1];

				if (contSolicitudes >= 100) {
					System.out.println("Se lleno el espacioooooooooo");
				} else {

					listaNombreSoli[contSolicitudes] = nombre;
					listaApellidoSoli[contSolicitudes] = apellido;
					contSolicitudes++;

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No existe el archivo Solicitudeeeeeeeeeeeeeeeeeeeeees");

		}

	}
}
