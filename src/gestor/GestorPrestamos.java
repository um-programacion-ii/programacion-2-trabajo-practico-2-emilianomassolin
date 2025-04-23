package gestor;

import modelo.usuario.Usuario;
import modelo.recurso.RecursoDigital;
import modelo.prestamo.Prestamo;
import modelo.recurso.Prestable;
import excepciones.RecursoNoDisponibleException;

import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private final List<Prestamo> prestamos = new ArrayList<>();

    public void prestarRecurso(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso instanceof Prestable prestable) {
            if (prestable.prestar()) {
                prestamos.add(new Prestamo(usuario, recurso));
            } else {
                throw new RecursoNoDisponibleException("El recurso ya está prestado.");
            }
        } else {
            throw new RecursoNoDisponibleException("El recurso no se puede prestar.");
        }
    }

    public void devolverRecurso(RecursoDigital recurso) {
        for (Prestamo p : prestamos) {
            if (p.getRecurso().equals(recurso) && p.getFechaDevolucion() == null) {
                if (recurso instanceof Prestable prestable) {
                    prestable.devolver();
                }
                p.devolver();
                break;
            }
        }
    }

    public List<Prestamo> obtenerPrestamos() {
        return prestamos;
    }
}
