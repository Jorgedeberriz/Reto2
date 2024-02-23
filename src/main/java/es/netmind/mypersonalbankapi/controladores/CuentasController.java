package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CuentasController {
    @Autowired
    private static ICuentasRepoData cuentasRepo;
    @Autowired
    private static IClientesRepoData clientesRepo;

    public static void mostrarLista(Integer uid) {
        System.out.println("\nLista de cuentas del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Cuenta> cuentas = cuentasRepo.findByMyCliente_id(uid);
            if (cuentas != null && cuentas.size() > 0) System.out.println(cuentas);
            else System.out.println("El cliente no tiene cuentas!");
        } catch (Exception e) {
            System.out.println("Cliente NO encontrado 😞!");
        }
    }
    @Transactional
    public static void mostrarDetalle(Integer uid, Integer aid) {
        System.out.println("\nDetalle de cuenta: " + aid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cuenta cuenta = cuentasRepo.findById(aid).get();
            if (cuenta.getMyCliente().getId() == uid){
                System.out.println(cuenta);
            } else {
                System.out.println("Cuenta no del cliente.");
            }
        } catch (Exception e) {
            System.out.println("Cuenta NO encontrada para el cliente 😞!");
        }
    }

    public static void eliminar(Integer uid, Integer aid) {
        System.out.println("\nBorrando cuenta: " + aid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cuenta cu = cuentasRepo.findById(aid).get();
            if (cu.getMyCliente().getId() == uid) {
                cuentasRepo.delete(cu);

                Cliente cl = clientesRepo.findById(uid).get();
                cl.delisgarCuenta(cu);
                System.out.println("Cuenta borrada 🙂!!");
                mostrarLista(uid);
            } else System.out.println("Cuenta NO borrada 😞!! Cuenta no del cliente");

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
