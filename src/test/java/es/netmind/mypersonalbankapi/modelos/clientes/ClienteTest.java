package es.netmind.mypersonalbankapi.modelos.clientes;

import es.netmind.mypersonalbankapi.controladores.ClientesController;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.exceptions.*;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClienteTest {
    @Test
    void altaCliente_Ok() throws Exception {
        Cliente cli = new Personal(4, "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
        String[] args = {"personal", "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", String.valueOf(LocalDate.now()), "87654321A"};
        ClientesController.add(args);
        assertThat(ClientesInMemoryRepo.getInstance().getClientById(4), samePropertyValuesAs(cli));
    }

    /*
    @Test
    void dadoCliente_cuandoFechaIncorrecta_entoncesClienteException() throws Exception {
        String fechaString = "00-00-0000";
        LocalDate fechaLocalDate = LocalDate.parse(fechaString);
        Cliente cli = new Personal(4, "Nuevo cliente", "ncdxc.com", "Calle Nueva 1", fechaLocalDate, true, false, "87654321A");
        assertThrows(DateTimeException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }
    */

    @Test
    void dadoCliente_cuandoNombreIncorrecto_entoncesClienteException() throws Exception {
        Cliente cli = new Personal(4, "Nom", "ncdxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
        assertThrows(ClienteException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }


    @Test
    void dadoCliente_cuandoEmailIncorrecto_entoncesClienteException() throws Exception {
        Cliente cli = new Personal(4, "Nuevo cliente", "ncdxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
        assertThrows(ClienteException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }

    @Test
    void dadoCliente_cuandoDireccionIncorrecta_entoncesClienteException() throws Exception {
        Cliente cli = new Personal(4, "Nuevo cliente", "nc@dxc.com", "C/", LocalDate.now(), true, false, "87654321A");
        assertThrows(ClienteException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }

    @Test
    void dadoClientePersonal_cuandoNifIncorrecto_entoncesClienteException() throws Exception {
        Cliente cli = new Personal(4, "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321");
        assertThrows(ClienteException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }

    @Test
    void dadoClienteEmpresa_cuandoCifIncorrecto_entoncesClienteException() throws Exception {
        String[] unidadesNegocio = {"Contabilidad"};
        Cliente cli = new Empresa(4, "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "A8654321", unidadesNegocio);
        assertThrows(ClienteException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }


}