package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.ErrorCode;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.*;
import es.netmind.mypersonalbankapi.utils.ClientesUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ClientesController {
    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private ICuentasRepoData cuentasRepo;
    @Autowired
    private IPrestamosRepoData prestamosRepo;

    //@Autowired
    //private IClienteDBRepository clientesRepoDB;

    //static {
    //    try {
    //        clientesRepoDB = new ClienteDBRepository();
    //    } catch (Exception e) {
    //        throw new RuntimeException(e);
    //    }
    //}

    //private static ICuentasRepo cuentasRepo = CuentasInMemoryRepo.getInstance();
    //private static IPrestamosRepo prestamosRepo = PrestamosInMemoryRepo.getInstance();


    public void mostrarLista() {
        System.out.println("\nLista de clientes:");
        System.out.println("───────────────────────────────────");
        List<Cliente> clientes = clientesRepo.findAll();
        for (Cliente cl : clientes) {
            try {
                if (cl.validar()) {
                    System.out.println("(" + cl.getId() + ") " + cl.getNombre() + " " + cl.getId());
                } else throw new ClienteException("Cliente NO válido", ErrorCode.INVALIDCLIENT);
            } catch (ClienteException e) {
                System.out.println("El cliente solicitado tiene datos erroneos 😞! Ponte en contacto con el admin. \nCode: " + e.getCode());
            } catch (Exception e) {
                System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            }

        }
    }

    public void mostrarDetalle(Integer uid) {
        System.out.println("\nDetalle de cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Cliente> cl = clientesRepo.findById(uid);
            System.out.println(cl);
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    public void add(String[] args) {
        System.out.println("\nAñadiendo cliente");
        System.out.println("───────────────────────────────────");
        try {
            Cliente cl = ClientesUtils.extractClientFromArgsForCreate(args);
            if (cl.validar()) {
                clientesRepo.save(cl);
            } else throw new ClienteException("Cliente NO válido", ErrorCode.INVALIDCLIENT);
            System.out.println("Cliente añadido: " + cl + " 🙂");
            mostrarLista();
        } catch (ClienteException e) {
            System.out.println("Cliente NO válido 😞! \nCode: " + e.getCode());
        } catch (DateTimeException e) {
            System.out.println("⚠ LAS FECHAS DEBEN TENER EL FORMATO yyyy-mm-dd, por ejemplo 2023-12-01 ⚠");
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            e.printStackTrace();
        }

    }
    @Transactional
    public void eliminar(Integer uid) {
        System.out.println("\nBorrando cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Cliente> opCl = clientesRepo.findById(uid);
            Cliente cl = opCl.get();
            clientesRepo.delete(cl);

            System.out.println("Cliente borrado 🙂!!");
            mostrarLista();
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    public void actualizar(Integer uid, String[] args) {
        System.out.println("\nActualizando cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Cliente> opCl = clientesRepo.findById(uid);
            Cliente cl = opCl.get();
            System.out.println("cl.getClass():" + cl.getClass() + " " + cl);
            ClientesUtils.updateClientFromArgs(cl, args);
            clientesRepo.save(cl);
            System.out.println("Cliente actualizado 🙂!!");
            System.out.println(cl);
            mostrarLista();
        } catch (ClienteException e) {
            System.out.println("Cliente NO encontrado 😞! \nCode: " + e.getCode());
        } catch (DateTimeException e) {
            System.out.println("⚠ LAS FECHAS DEBEN TENER EL FORMATO yyyy-mm-dd, por ejemplo 2023-12-01 ⚠");
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            e.printStackTrace();
        }

    }

    public void evaluarPrestamo(Integer uid, Double cantidad) {
        System.out.println("\nEvaluando préstamos de " + cantidad + " EUR para el  cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Cliente> opCliente = clientesRepo.findById(uid);
            Cliente cliente = opCliente.get();
            System.out.println("Saldo total del cliente: " + cliente.obtenerSaldoTotal());
            int numPrestamos = cliente.getPrestamos() != null ? cliente.getPrestamos().size() : 0;
            System.out.println("Número total de préstamos del cliente: " + numPrestamos);

            Prestamo prestamoSolictado = new Prestamo(null, LocalDate.now(), cantidad, cantidad, 10, 5, false, false, 5);

            boolean aceptable = cliente.evaluarSolicitudPrestamo(prestamoSolictado);
            if (aceptable) System.out.println("SÍ se puede conceder 🙂!!");
            else System.out.println("NO puede conceder 😞!! Saldo insuficiente.");

        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            e.printStackTrace();
        }

    }
}
