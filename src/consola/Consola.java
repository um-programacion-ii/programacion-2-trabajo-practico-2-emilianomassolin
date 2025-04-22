package consola;

import gestor.GestorRecursos;
import gestor.GestorUsuarios;
import modelo.recurso.*;
import modelo.usuario.Usuario;
import notificaciones.ServicioNotificaciones;

import java.util.Scanner;

public class Consola {
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private ServicioNotificaciones servicioNotificaciones;
    private Scanner scanner;

    public Consola(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, ServicioNotificaciones servicioNotificaciones) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.servicioNotificaciones = servicioNotificaciones;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n--- Sistema de Biblioteca Digital ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Salir");
            System.out.println("4. Registrar Recurso");
            System.out.println("5. Listar Recursos");
            System.out.println("6. Prestar Recurso");
            System.out.println("7. Devolver Recurso");
            System.out.println("8. Renovar Recurso");
            System.out.println("9. Buscar usuario por ID");
            System.out.println("10 Buscar recurso por titulo");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // consumir newline

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> gestorUsuarios.listarUsuarios();
                case 3 -> System.out.println("Finalizando...");
                case 4 -> registrarRecurso();
                case 5 -> gestorRecursos.listarRecursos();
                case 6 -> prestarRecurso();
                case 7 -> devolverRecurso();
                case 8 -> renovarRecurso();
                case 9 -> buscarUsuarioPorId();
                case 10 -> buscarRecursoPorTitulo();

                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }

    private void registrarUsuario() {
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre, email);
        gestorUsuarios.registrarUsuario(usuario);
        System.out.println("Usuario registrado con éxito.");
    }
    private void registrarRecurso() {
        System.out.println("Seleccionar tipo de recurso:");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Audiolibro");
        System.out.print("Opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1 -> registrarLibro();
            case 2 -> registrarRevista();
            case 3 -> registrarAudiolibro();
            default -> System.out.println("❌ Opción no válida.");
        }
    }
    private void registrarLibro() {
        System.out.print("Título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        gestorRecursos.agregarRecurso(new Libro(titulo, autor));
        System.out.println("✅ Libro registrado.");
    }

    private void registrarRevista() {
        System.out.print("Título de la revista: ");
        String titulo = scanner.nextLine();
        System.out.print("Número de edición: ");
        int edicion = Integer.parseInt(scanner.nextLine());
        gestorRecursos.agregarRecurso(new Revista(titulo, edicion) {
            @Override
            public String getEstado() {
                return "";
            }
        });
        System.out.println("✅ Revista registrada.");
    }

    private void registrarAudiolibro() {
        System.out.print("Título del audiolibro: ");
        String titulo = scanner.nextLine();
        System.out.print("Narrador: ");
        String narrador = scanner.nextLine();
        gestorRecursos.agregarRecurso(new Audiolibro(titulo, narrador));
        System.out.println("✅ Audiolibro registrado.");
    }
    private void prestarRecurso() {
        System.out.print("Ingrese el título del recurso a prestar: ");
        String titulo = scanner.nextLine();
        var recurso = gestorRecursos.buscarPorTitulo(titulo);

        if (recurso instanceof Prestable prestable) {
            if (prestable.prestar()) {
                servicioNotificaciones.notificar("✅ Recurso prestado exitosamente: " + titulo);
            } else {
                servicioNotificaciones.notificar("⚠️ El recurso ya está prestado: " + titulo);
            }
        } else {
            servicioNotificaciones.notificar("❌ Este recurso no se puede prestar: " + titulo);
        }
    }


    private void devolverRecurso() {
        System.out.print("Ingrese el título del recurso a devolver: ");
        String titulo = scanner.nextLine();
        var recurso = gestorRecursos.buscarPorTitulo(titulo);

        if (recurso instanceof Prestable prestable) {
            if (prestable.devolver()) {
                servicioNotificaciones.notificar("✅ Recurso devuelto correctamente: " + titulo);
            } else {
                servicioNotificaciones.notificar("⚠️ Este recurso no estaba prestado: " + titulo);
            }
        } else {
            servicioNotificaciones.notificar("❌ Este recurso no es retornable: " + titulo);
        }
    }


    private void renovarRecurso() {
        System.out.print("Ingrese el título del recurso a renovar: ");
        String titulo = scanner.nextLine();
        var recurso = gestorRecursos.buscarPorTitulo(titulo);

        if (recurso instanceof Renovable renovable) {
            if (renovable.renovar()) {
                servicioNotificaciones.notificar("✅ Recurso renovado: " + titulo);
            } else {
                servicioNotificaciones.notificar("⚠️ No se puede renovar este recurso (ya alcanzó el límite o no está prestado): " + titulo);
            }
        } else {
            servicioNotificaciones.notificar("❌ Este recurso no es renovable: " + titulo);
        }
    }
    private void buscarUsuarioPorId() {
        System.out.print("Ingrese el ID del usuario: ");
        String id = scanner.nextLine();
        var usuario = gestorUsuarios.buscarUsuario(id);
        if (usuario != null) {
            System.out.println("🔍 Usuario encontrado: " + usuario);
        } else {
            System.out.println("❌ No se encontró ningún usuario con ese ID.");
        }
    }
    private void buscarRecursoPorTitulo() {
        System.out.print("Ingrese el título a buscar: ");
        String titulo = scanner.nextLine();
        RecursoDigital resultado = gestorRecursos.buscarPorTitulo(titulo);

        if (resultado != null) {
            System.out.println("🔍 Recurso encontrado:");
            System.out.println(resultado);
        } else {
            System.out.println("❌ No se encontró ningún recurso con ese título.");
        }
    }




}
