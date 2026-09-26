# Taller 01: Sistema de Control del Grupo POO

Sistema para el control de acceso y administración de un grupo de estudio de Programación Orientada a Objetos (POO). El programa filtra automáticamente las solicitudes de ingreso cruzando los datos contra la lista oficial del curso, administra la lista de inscritos, genera reportes versionados y entrega métricas estadísticas sobre el proceso.

\---

### **Integrantes**

* **Nombre:** Sophia Villalobos | **RUT:** 21137863-8 | **Usuario GitHub:** SophiaVillalobos-GitH | **Carrera:** Ingeniería Civil en Computación e Informática

\---

### **Estructura del Proyecto** 

Para la gestión de los datos se utilizaron **vectores estáticos paralelos** de capacidad fija (100 elementos) y procesamiento procedimental desacoplado en funciones estáticas, garantizando una administración eficiente de memoria sin uso de colecciones dinámicas de la librería estándar:

1. **Vectores Paralelos de Alumnos Inscritos y Solicitudes:**

   * Almacenan el estado oficial del curso (`listaNombreInscr`, `listaApellidoInscr`, `listaRutInscr`, `listaParaleloInscr`) cargado desde `Alumnos.txt`.
   * Registran los intentos de ingreso desde `Solicitudes.txt` y distribuyen a los alumnos en listas de admitidos o rechazados.
2. **Administración y Persistencia en Disco:**

   * Permite reasignar paralelos (C1 ⇄ C2), eliminar estudiantes de forma coherente (desplazando posiciones en los vectores sin dejar espacios vacíos) e inscribir nuevos alumnos.
   * Reescribe el archivo `Alumnos.txt` mediante `BufferedWriter` para asegurar la persistencia de datos entre ejecuciones.
3. **Sistema de Reportes Versionados:**

   * Exporta archivos individuales (`ReporteC1-VX.txt`, `ReporteC2-VX.txt` y `Rechazados-VX.txt`), incrementando su número de versión (`VX`) de forma independiente en cada generación.

\---

### **Estructura del Repositorio**

```text
.
├── README.md
├── Alumnos.txt
├── Solicitudes.txt
└── src/
    └── taller/
        └── App.java
```

* **`taller/App.java`**: Clase principal que alberga el ciclo del menú interactivo, la lectura/escritura de archivos con control de excepciones (`IOException`, `FileNotFoundException`), la administración del curso, la generación de reportes y el análisis estadístico.

\---

### **Instrucciones de ejecución**

> \*\*Importante:\*\* Los archivos `Alumnos.txt` y `Solicitudes.txt` deben estar ubicados en la raíz del proyecto (directorio de ejecución).

1. **Abrir una terminal** en el directorio raíz del repositorio.
2. **Compilar** la clase principal dentro del paquete `taller`:

```bash
   javac -d . src/taller/App.java
   ```

3. **Ejecutar** la aplicación:

```bash
   java taller.App
   ```

\---

### **Manejo de Casos Borde y Validaciones**

* **Control de Archivos:** Validación previa de existencia de `Alumnos.txt` y `Solicitudes.txt`; si un archivo no existe, emite una advertencia sin interrumpir el programa.
* **Control de Entradas del Usuario:** Validación de menú que previene bucles infinitos o caídas ante ingresos alfanuméricos fuera de rango.
* **Formatos Específicos:** Registro con el mensaje especial `"Sin nombre registrado, RUT: ..."` cuando se realiza una inscripción manual de un RUT no inscrito en la lista oficial.
* **Límite de Capacidad:** Verificación de límites en vectores para evitar excepciones de tipo `ArrayIndexOutOfBoundsException`.

