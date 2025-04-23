package reportes;

import modelo.prestamo.Prestamo;
import modelo.recurso.CategoriaRecurso;
import modelo.recurso.RecursoDigital;
import modelo.usuario.Usuario;

import java.util.*;
import java.util.stream.Collectors;

public class GeneradorReportes {

    private List<Prestamo> historialPrestamos = List.of();

    public GeneradorReportes() {
        this.historialPrestamos = historialPrestamos;
    }

    // 📊 Recursos más prestados
    public void mostrarRecursosMasPrestados() {
        System.out.println("\n📚 Recursos más prestados:");

        Map<RecursoDigital, Long> conteo = historialPrestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<RecursoDigital, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.printf("• %s → %d préstamos%n", e.getKey().getTitulo(), e.getValue()));
    }

    // 🙋 Usuarios más activos
    public void mostrarUsuariosMasActivos() {
        System.out.println("\n👤 Usuarios más activos:");

        Map<Usuario, Long> conteo = historialPrestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.printf("• %s → %d préstamos%n", e.getKey().getNombre(), e.getValue()));
    }

    // 📂 Estadísticas por categoría
    public void mostrarEstadisticasPorCategoria() {
        System.out.println("\n📈 Estadísticas de uso por categoría:");

        Map<CategoriaRecurso, Long> conteo = historialPrestamos.stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getCategoria(), Collectors.counting()));

        conteo.forEach((categoria, cantidad) ->
                System.out.printf("• %s → %d préstamos%n", categoria, cantidad));
    }
}
