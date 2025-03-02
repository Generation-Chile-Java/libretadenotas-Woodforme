import java.util.*;

class LibretaDeNotas {
    private HashMap<String, ArrayList<Double>> calificaciones;

    public LibretaDeNotas() {
        calificaciones = new HashMap<>();
    }

    public void agregarEstudiante(String nombre, ArrayList<Double> notas) {
        calificaciones.put(nombre, notas);
    }

    public double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    public double notaMaxima(ArrayList<Double> notas) {
        return Collections.max(notas);
    }

    public double notaMinima(ArrayList<Double> notas) {
        return Collections.min(notas);
    }

    public void mostrarPromedios() {
        for (Map.Entry<String, ArrayList<Double>> entry : calificaciones.entrySet()) {
            String nombre = entry.getKey();
            ArrayList<Double> notas = entry.getValue();
            double promedio = calcularPromedio(notas);
            System.out.println("Estudiante: " + nombre + ", Promedio: " + promedio);
        }
    }

    public void verificarAprobacion(String nombre, double notaAprobatoria) {
        if (calificaciones.containsKey(nombre)) {
            ArrayList<Double> notas = calificaciones.get(nombre);
            double promedio = calcularPromedio(notas);
            if (promedio >= notaAprobatoria) {
                System.out.println("El estudiante " + nombre + "aprobado con esta nota.");
            } else {
                System.out.println("El estudiante " + nombre + "reprobado con esta nota.");
            }
        } else {
            System.out.println("Error, estudiante no encontrado.");
        }
    }

    public void verificarNotaPromedioCurso(String nombre, double nota) {
        if (calificaciones.containsKey(nombre)) {
            ArrayList<Double> notas = calificaciones.get(nombre);
            double promedio = calcularPromedio(notas);
            if (nota > promedio) {
                System.out.println("La nota está por sobre el promedio del curso.");
            } else if (nota < promedio) {
                System.out.println("La nota está por debajo del promedio del curso.");
            } else {
                System.out.println("La nota es igual al promedio del curso.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibretaDeNotas libreta = new LibretaDeNotas();

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();
        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
            String nombre = scanner.next();
            ArrayList<Double> notas = new ArrayList<>();
            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota " + (j + 1) + " de " + nombre + ": ");
                double nota = scanner.nextDouble();
                notas.add(nota);
            }
            libreta.agregarEstudiante(nombre, notas);
        }

        int opcion;
        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante.");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.");
            System.out.println("0. Salir del Menú.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    libreta.mostrarPromedios();
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombreEstudiante = scanner.next();
                    System.out.print("Ingrese la nota de aprobación: ");
                    double notaAprobatoria = scanner.nextDouble();
                    libreta.verificarAprobacion(nombreEstudiante, notaAprobatoria);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    nombreEstudiante = scanner.next();
                    System.out.print("Ingrese la nota a verificar: ");
                    double nota = scanner.nextDouble();
                    libreta.verificarNotaPromedioCurso(nombreEstudiante, nota);
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}