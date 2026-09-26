//Sophia Villalobos -21137863-8- ICCI

package taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	
	private static int contVerC1 = 1;
	private static int contVerC2 = 1;
	private static int contVerRec = 1;

	private static Scanner s = new Scanner(System.in);

	/**
	 * mostrar el menu y llamar a la funcion dependiendo del caso
	 * 
	 */
	public static void main(String[] args) {
		String opcion;
		do {
			System.out.println("Sistema de control del grupo de poo" + "\n 1)Cargar archivos"
					+ "\n 2)Procesar solicitudes" + "\n 3)ingresar manual+"
							+ "\n4) Administración del curso+"
							+ "\n5) Generar reportes+"
							+ "\n6) Análisis estadístico+"
							+ "\n7) Salir");

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
			case "4":
                administracionCurso();
                break;

            case "5":
                generarReportes();
                break;
			case "6":
				analisisEstadistico();
				break;
			default:
				System.out.println("Opcion invalida");
				break;

			}

		} while (!opcion.equals("7"));

	}
	/**Muestra el submenú para seleccionar 
	 * y exportar los reportes */
	private static void generarReportes() {
        String opcion = "";
        while (!opcion.equals("4")) {
            System.out.println("\n--- Generar Reportes ---");
            System.out.println("1) Generar Reporte C1");
            System.out.println("2) Generar Reporte C2");
            System.out.println("3) Generar Reporte Rechazados");
            System.out.println("4) Volver");
            System.out.print("Ingrese opción: ");
            opcion = s.nextLine();

            switch (opcion) {
                case "1":
                    generarReporteParalelo("C1");
                    break;
                case "2":
                    generarReporteParalelo("C2");
                    break;
                case "3":
                    generarReporteRechazados();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
	/** Genera un archivo de texto 
     *  con la lista de alumnos admitidos del paralelo indicado */
    private static void generarReporteParalelo(String paralelo) {
        int version = 0;
        if (paralelo.equalsIgnoreCase("C1")) {
            version = contVerC1;
        } else {
            version = contVerC2;
        }

        String nombreArchivo = "Reporte" + paralelo.toUpperCase() + "-V" + version + ".txt";

        try {
            FileWriter fw = new FileWriter(nombreArchivo, false);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("=== Miembros del grupo - Paralelo " + paralelo.toUpperCase() + " ===");
            bw.newLine();

            for (int i = 0; i < contAdmitidos; i++) {
                if (listaParaleloAdmi[i].equalsIgnoreCase(paralelo)) {
                    bw.write(listaNombreAdmi[i] + " " + listaApellidoAdmi[i] + " - " + listaRutAdmi[i]);
                    bw.newLine();
                }
            }

            bw.close();
            System.out.println("Reporte generado con éxito: " + nombreArchivo);

            if (paralelo.equalsIgnoreCase("C1")) {
                contVerC1++;
            } else {
                contVerC2++;
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el reporte.");
        }
    }
    /** Genera un archivo de texto
     * con el registro de solicitudes rechazadas y sus motivos. */
    private static void generarReporteRechazados() {
        String nombreArchivo = "Rechazados-V" + contVerRec + ".txt";

        try {
            FileWriter fw = new FileWriter(nombreArchivo, false);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("=== Solicitudes rechazadas ===");
            bw.newLine();

            for (int i = 0; i < contRechazados; i++) {
                String nombre = listaNombreRechazado[i];
                String apellido = listaApellidoRechazado[i];
                String rut = listaRutRechazado[i];
                String razon = listaRazonRechazado[i];

                if (nombre.equalsIgnoreCase("desconocido") || nombre.equals("")) {
                    bw.write("Sin nombre registrado, RUT: " + rut);
                } else {
                    bw.write(nombre + " " + apellido + " - " + razon);
                }
                bw.newLine();
            }

            bw.close();
            System.out.println("Reporte de rechazados generado con éxito: " + nombreArchivo);
            contVerRec++;
        } catch (IOException e) {
            System.out.println("Error al escribir el reporte de rechazados.");
        }
    }
    /** Muestra las estadísticas del sistema
     * y la distribución por paralelo. */

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
                System.out.println("RUT no encontrado. Registrado en lista de rechazados.");

			}
		}

	}
	/** esta funcion despliega el menú para gestionar los alumnos del curso 
	 * (cambiar paralelo,eliminar e inscribir). */

	private static void administracionCurso() {
        String opcion = "";
        while (!opcion.equals("4")) {
            System.out.println("\n--- Administración del curso ---");
            System.out.println("1) Cambiar paralelo de un alumno");
            System.out.println("2) Eliminar alumno del curso");
            System.out.println("3) Inscribir alumno nuevo");
            System.out.println("4) Volver");
            System.out.print("Ingrese opción: ");
            opcion = s.nextLine();

            switch (opcion) {
                case "1":
                    cambiarParalelo();
                    break;
                case "2":
                    eliminarAlumno();
                    break;
                case "3":
                    inscribirAlumnoNuevo();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
	/** esta modifica el paralelo c1 y c2 de un alumno según su rut
	 * y actualiza su estado en el archivo. */

    private static void cambiarParalelo() {
        System.out.print("Ingrese RUT del alumno: ");
        String rut = s.nextLine();

        int pos = -1;
        for (int i = 0; i < contInscritos; i++) {
            if (listaRutInscr[i].equalsIgnoreCase(rut)) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            System.out.println("El RUT no pertenece a ningún alumno inscrito.");
            return;
        }

        System.out.println("Alumno: " + listaNombreInscr[pos] + " " + listaApellidoInscr[pos] +
                           " (actualmente en " + listaParaleloInscr[pos] + ")");

        String nuevoParalelo = "";
        do {
            System.out.print("Nuevo paralelo (C1/C2): ");
            nuevoParalelo = s.nextLine();
        } while (!nuevoParalelo.equalsIgnoreCase("C1") && !nuevoParalelo.equalsIgnoreCase("C2"));

        listaParaleloInscr[pos] = nuevoParalelo.toUpperCase();

        for (int j = 0; j < contAdmitidos; j++) {
            if (listaRutAdmi[j].equalsIgnoreCase(rut)) {
                listaParaleloAdmi[j] = nuevoParalelo.toUpperCase();
                break;
            }
        }

        actualizarArchivoAlumnos();
    }
    /** eata funcion elimina a un alumno del curso por su rut,
     *  remueve sus accesos al grupo y guarda los cambios. */

    private static void eliminarAlumno() {
        System.out.print("Ingrese RUT del alumno a eliminar: ");
        String rut = s.nextLine();

        int pos = -1;
        for (int i = 0; i < contInscritos; i++) {
            if (listaRutInscr[i].equalsIgnoreCase(rut)) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            System.out.println("El RUT no pertenece a ningún alumno en la lista.");
            return;
        }

        System.out.println("Eliminando a: " + listaNombreInscr[pos] + " " + listaApellidoInscr[pos]);

        for (int i = pos; i < contInscritos - 1; i++) {
            listaNombreInscr[i] = listaNombreInscr[i + 1];
            listaApellidoInscr[i] = listaApellidoInscr[i + 1];
            listaRutInscr[i] = listaRutInscr[i + 1];
            listaParaleloInscr[i] = listaParaleloInscr[i + 1];
        }
        contInscritos--;

        int posAdmi = -1;
        for (int j = 0; j < contAdmitidos; j++) {
            if (listaRutAdmi[j].equalsIgnoreCase(rut)) {
                posAdmi = j;
                break;
            }
        }

        if (posAdmi != -1) {
            for (int j = posAdmi; j < contAdmitidos - 1; j++) {
                listaNombreAdmi[j] = listaNombreAdmi[j + 1];
                listaApellidoAdmi[j] = listaApellidoAdmi[j + 1];
                listaRutAdmi[j] = listaRutAdmi[j + 1];
                listaParaleloAdmi[j] = listaParaleloAdmi[j + 1];
            }
            contAdmitidos--;
        }

        actualizarArchivoAlumnos();
    }
    /** esta funcion inscribe a un nuevo alumno en la lista oficial validando datos 
     *  y rut no duplicado. */

    private static void inscribirAlumnoNuevo() {
        if (contInscritos >= 100) {
            System.out.println("No hay capacidad en los vectores para más alumnos.");
            return;
        }

        String nombre = "";
        do {
            System.out.print("Nombre: ");
            nombre = s.nextLine();
        } while (nombre.equals(""));

        String apellido = "";
        do {
            System.out.print("Apellido: ");
            apellido = s.nextLine();
        } while (apellido.equals(""));

        String rut = "";
        boolean duplicado = false;
        do {
            System.out.print("RUT: ");
            rut = s.nextLine();

            duplicado = false;
            for (int i = 0; i < contInscritos; i++) {
                if (listaRutInscr[i].equalsIgnoreCase(rut)) {
                    duplicado = true;
                    System.out.println("Este RUT ya está registrado.");
                    break;
                }
            }
        } while (rut.equals("") || duplicado);

        String paralelo = "";
        do {
            System.out.print("Paralelo (C1/C2): ");
            paralelo = s.nextLine();
        } while (!paralelo.equalsIgnoreCase("C1") && !paralelo.equalsIgnoreCase("C2"));

        listaNombreInscr[contInscritos] = nombre;
        listaApellidoInscr[contInscritos] = apellido;
        listaRutInscr[contInscritos] = rut;
        listaParaleloInscr[contInscritos] = paralelo.toUpperCase();
        contInscritos++;

        actualizarArchivoAlumnos();
    }
    /** lo que hace esta funcion es que sobrescribe el archivo Alumnos.txt con los datos actualizados
     *  de los inscritos */

    public static void actualizarArchivoAlumnos() {
        try {
            FileWriter fw = new FileWriter("Alumnos.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < contInscritos; i++) {
                String linea = listaNombreInscr[i] + ";" + 
                               listaApellidoInscr[i] + ";" + 
                               listaRutInscr[i] + ";" + 
                               listaParaleloInscr[i];

                bw.write(linea);

                if (i < contInscritos - 1) {
                    bw.newLine();
                }
            }

            bw.close();
            System.out.println("Archivo Alumnos.txt actualizado correctamente.");

        } catch (IOException e) {
            System.out.println("[Error] Hubo un problema al guardar el archivo: " + e.getMessage());
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
