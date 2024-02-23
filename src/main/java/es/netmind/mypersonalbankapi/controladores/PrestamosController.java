package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.PrestamoException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class PrestamosController {
    @Autowired
    private static IPrestamosRepoData prestamosRepo;
    @Autowired
    private static IClientesRepoData clientesRepo;

    public static void mostrarLista(Integer uid) {
        System.out.println("\nLista de prestamos del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Prestamo> prestamos = prestamosRepo.findByMyCliente_id(uid);
            if (prestamos != null && prestamos.size() > 0) System.out.println(prestamos);
            else System.out.println("El cliente no tiene prestamos!");
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }

    public static void mostrarDetalle(Integer uid, Integer lid) {
        System.out.println("\nDetalle de prestamo: " + lid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Prestamo prestamo = prestamosRepo.findById(lid).get();
            if (prestamo.getMyCliente().getId()== uid) {
                System.out.println(prestamo);
            } else {
                System.out.println("Prestamo NO encontrado para el cliente 😞!");
            }
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }
    @Transactional
    public static void eliminar(Integer uid, Integer lid) {
        System.out.println("\nBorrando prestamo: " + lid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Prestamo pr = prestamosRepo.findById(lid).get();
            if (pr.getMyCliente().getId()== uid) {
                prestamosRepo.delete(pr);
                Cliente cl = clientesRepo.findById(uid).get();
                cl.delisgarPrestamo(pr);
                System.out.println("Préstamo borrado 🙂!!");
                mostrarLista(uid);
            } else System.out.println("Préstamo NO borrado 😞!! Préstamo no del cliente");

        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    public static void add(Integer uid, String[] args) {
        System.out.println("uid: " + uid);
        for (String arg : args) {
            System.out.println(arg);
        }
        // TODO
    }

    public static void actualizar(Integer uid, Integer lid, String[] args) {
        System.out.println("uid: " + uid);
        System.out.println("lid: " + lid);
        for (String arg : args) {
            System.out.println(arg);
        }
        // TODO
    }
}
