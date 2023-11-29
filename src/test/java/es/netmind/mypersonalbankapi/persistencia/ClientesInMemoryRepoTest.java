package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("dev")
class ClientesInMemoryRepoTest {
    @Autowired
    private IClientesRepo repo;
 /*@Test
    void cuandoClienteCorrecto_entoncesAltaOk() throws Exception {
        Cliente cli = new Personal(4, "Nombre nuevo", "nc@dxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
        ClientesInMemoryRepo.getInstance().addClient(cli);
        assertThat(ClientesInMemoryRepo.getInstance().getClientById(4), samePropertyValuesAs(cli));
    }

    @Test
    void dadoCliente_cuandoFechaIncorrecta_entoncesClienteException() throws Exception {
        String fechaString = "2023-10-22";
        LocalDate fechaLocalDate = LocalDate.parse(fechaString);
        Cliente cli = new Personal(4, "Nuevo cliente", "ncdxc.com", "Calle Nueva 1", fechaLocalDate, true, false, "87654321A");
        assertThrows(ClienteException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }


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
*/
    @Test
    void dadoIdClientePersonal_cuandoRecuperarPorId_entoncesOk() throws Exception {
        Cliente ncliente = repo.getClientById(4);
        System.out.println(ncliente);
        assertThat(ncliente.getNombre(), is("practica perfil"));
    }

}