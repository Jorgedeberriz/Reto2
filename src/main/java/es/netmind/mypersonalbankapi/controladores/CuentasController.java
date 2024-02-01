package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CuentasController {
    @Autowired
    private ICuentasRepoData cuentasRepo;
    @Autowired
    private IClientesRepoData clientesRepo;

    public void mostrarLista(Integer uid) {
        System.out.println("\nLista de cuentas del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Cuenta> cuentas = cuentasRepo.findByMyCliente_Id(uid);
            if (cuentas != null && cuentas.size() > 0) System.out.println(cuentas);
            else System.out.println("El cliente no tiene cuentas!");
        } catch (Exception e) {
            System.out.println("Cliente NO encontrado 😞!");
        }
    }

    public void mostrarDetalle(Integer uid, Integer aid) {
        System.out.println("\nDetalle de cuenta: " + aid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Cuenta> opCuenta = cuentasRepo.findById(aid);
            Cuenta cuenta = opCuenta.get();
            System.out.println(cuenta);
        } catch (Exception e) {
            System.out.println("Cuenta NO encontrada para el cliente 😞!");
        }
    }

    public void eliminar(Integer uid, Integer aid) {
        System.out.println("\nBorrando cuenta: " + aid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Optional<Cuenta> opCuenta = cuentasRepo.findById(aid);
            Cuenta cu = opCuenta.get();
            cuentasRepo.delete(cu);
            //if (borrado) {
                Optional<Cliente> opCl = clientesRepo.findById(uid);
                Cliente cl = opCl.get();
                cl.delisgarCuenta(cu);
                System.out.println("Cuenta borrada 🙂!!");
                mostrarLista(uid);
            //} else System.out.println("Cuenta NO borrada 😞!! Consulte con su oficina.");
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    public static void add(Integer uid, String[] args) {
        System.out.println("uid: " + uid);
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    public static void actualizar(Integer uid, Integer aid, String[] args) {
        System.out.println("uid: " + uid);
        System.out.println("aid: " + aid);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
