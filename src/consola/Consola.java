package consola;

import excepciones.RecursoNoDisponibleException;
import excepciones.UsuarioNoEncontradoException;
import gestor.GestorPrestamos;
import gestor.GestorRecursos;
import gestor.GestorReservas;
import gestor.GestorUsuarios;
import modelo.recurso.*;
import modelo.reserva.Reserva;
import modelo.usuario.Usuario;
import notificaciones.ServicioNotificaciones;
import util.ComparadoresRecursos;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private final GestorPrestamos gestorPrestamos;
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private ServicioNotificaciones servicioNotificaciones;
    private Scanner scanner;
    private GestorReservas gestorReservas;

    public Consola(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, ServicioNotificaciones servicioNotificaciones) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.servicioNotificaciones = servicioNotificaciones;
        this.scanner = new Scanner(System.in);
        this.gestorPrestamos =new GestorPrestamos();
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
            System.out.println("11 Filtrar recurso por categoria");
            System.out.println("12. Ordenar recursos por título");
            System.out.println("13. Ordenar recursos por categoría");
            System.out.println("14. Ordenar recursos por estado");
            System.out.println("15. Reservar y ver estado de recurso ");




            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // consumir newline

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> gestorUsuarios.listarUsuarios();
                case 3 -> System.out.println("Finalizando...");
                case 4 -> registrarRecurso();
                case 5 -> gestorRecursos.listarRecursos();
                case 6 -> gestionarPrestamos();
                case 7 -> devolverPrestamo();
                case 8 -> renovarRecurso();
                case 9 -> buscarUsuarioPorId();
                case 10 -> buscarRecursoPorTitulo();
                case 11 -> filtrarRecursosPorCategoria();
                case 12 -> ordenarRecursosPor(ComparadoresRecursos.porTituloAsc(), "Título");
                case 13 -> ordenarRecursosPor(ComparadoresRecursos.porCategoriaAsc(), "Categoría");
                case 14 -> ordenarRecursosPor(ComparadoresRecursos.porEstadoAsc(), "Estado");
                case 15 -> reservarRecurso();



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

        try {
            var usuario = gestorUsuarios.buscarUsuario(id);
            System.out.println("🔍 Usuario encontrado: " + usuario);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("❌ " + e.getMessage());
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
    private void filtrarRecursosPorCategoria() {
        System.out.println("📋 Categorías disponibles:");
        for (CategoriaRecurso cat : CategoriaRecurso.values()) {
            System.out.println(" - " + cat.name());
        }

        System.out.print("Ingrese la categoría: ");
        String categoriaStr = scanner.nextLine();

        try {
            CategoriaRecurso categoria = CategoriaRecurso.valueOf(categoriaStr.toUpperCase());

            List<RecursoDigital> filtrados = gestorRecursos.filtrarPorCategoria(categoria);

            if (filtrados.isEmpty()) {
                System.out.println("❌ No se encontraron recursos en la categoría: " + categoria);
            } else {
                System.out.println("📂 Recursos encontrados en categoría " + categoria + ":");
                filtrados.forEach(System.out::println);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Categoría inválida. Intente nuevamente.");
        }
    }


    private void ordenarRecursosPor(Comparator<RecursoDigital> comparador, String criterio) {
        List<RecursoDigital> ordenados = gestorRecursos.obtenerRecursosOrdenados(comparador);
        System.out.println("📑 Recursos ordenados por " + criterio + ":");
        ordenados.forEach(System.out::println);
    }
    private void gestionarPrestamos() {
        System.out.print("Ingrese el ID del usuario: ");
        String idUsuario = scanner.nextLine();
        Usuario usuario = null;
        try {
            usuario = gestorUsuarios.buscarUsuario(idUsuario);
        } catch (UsuarioNoEncontradoException e) {
            throw new RuntimeException(e);
        }

        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }

        System.out.print("Ingrese el título del recurso: ");
        String titulo = scanner.nextLine();
        RecursoDigital recurso = gestorRecursos.buscarPorTitulo(titulo);

        try {
            gestorPrestamos.prestarRecurso(usuario, recurso);
            System.out.println("✅ Préstamo registrado exitosamente.");
        } catch (RecursoNoDisponibleException e) {
            System.out.println("⚠️ Error al prestar recurso: " + e.getMessage());
        }
    }

    private void devolverPrestamo() {
        System.out.print("Ingrese el título del recurso a devolver: ");
        String titulo = scanner.nextLine();
        RecursoDigital recurso = gestorRecursos.buscarPorTitulo(titulo);

        gestorPrestamos.devolverRecurso(recurso);
        System.out.println("✅ Recurso devuelto.");
    }
    private void reservarRecurso() {
        System.out.println("\n📌 Reservar recurso no disponible:");
        System.out.print(" titulo del recurso: ");
        String idRecurso = scanner.nextLine();
        System.out.print("🆔 ID del usuario: ");
        String idUsuario = scanner.nextLine();

        RecursoDigital recurso = gestorRecursos.buscarPorTitulo(idRecurso);
        if (recurso == null) {
            System.out.println("❌ Recurso no encontrado.");
            return;
        }

        try {
            Usuario usuario = gestorUsuarios.buscarUsuario(idUsuario);

            if (!recurso.getEstado().equalsIgnoreCase("NO DISPONIBLE")) {
                System.out.println("ℹ️ El recurso está disponible. Podés hacer el préstamo directamente.");
                return;
            }

            Reserva nuevaReserva = new Reserva(usuario, recurso);
            gestorReservas.agregarReserva(nuevaReserva);
            System.out.println("✅ Reserva registrada. Serás notificado cuando el recurso esté disponible.");

        } catch (UsuarioNoEncontradoException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }


}
