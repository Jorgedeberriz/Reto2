package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PrestamosController {
    @Autowired
    private IPrestamosRepoData prestamosRepo;
    @Autowired
    private IClientesRepoData clientesRepo;

    public void mostrarLista(Integer uid) {
        System.out.println("\nLista de prestamos del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Prestamo> prestamos = prestamosRepo.findByMyCliente_Id(uid);
            if (prestamos != null && prestamos.size() > 0) System.out.println(prestamos);
            else System.out.println("El cliente no tiene prestamos!");
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }

    public void mostrarDetalle(Integer uid, Integer lid) {
        System.out.println("\nDetalle de prestamo: " + lid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Prestamo> opPrestamo = prestamosRepo.findById(lid);
            Prestamo prestamo = opPrestamo.get();
            System.out.println(prestamo);
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }

    public void eliminar(Integer uid, Integer lid) {
        System.out.println("\nBorrando prestamo: " + lid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Prestamo> opPrestamo = prestamosRepo.findById(lid);
            Prestamo pr = opPrestamo.get();

            prestamosRepo.delete(pr);
            //if (borrado) {
                Optional<Cliente> opCl = clientesRepo.findById(uid);
                Cliente cl = opCl.get();
                cl.delisgarPrestamo(pr);
                System.out.println("Préstamo borrada 🙂!!");
                mostrarLista(uid);
            //} else System.out.println("Préstamo NO borrado 😞!! Consulte con su oficina.");
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
