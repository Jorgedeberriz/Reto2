package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ClienteDBRepositoryTest {
    private IClienteDBRepository repo;

    @BeforeEach
    void setUp() throws Exception {
        repo = new ClienteDBRepository();
    }

    @Test
    void dadosClienteEmpresa_cuandoinsertarClienteEnDB_entoncesIdValido() throws Exception {
        Cliente cliente = new Empresa(1, "nuevoClienteE", "nce@nce.com", "C/Nueva Empresa", LocalDate.now(), true, false,"B12345678",null);

        repo.insertCliente(cliente);

        System.out.println(cliente);

        assertThat(cliente.getId(), greaterThan(0));
    }

     @Test
    void dadosClientePersonal_cuandoinsertarClienteEnDB_entoncesIdValido() throws Exception {
        Cliente cliente = new Personal(null, "nuevoClienteP", "ncp@ncp.com", "C/Nueva Persona", LocalDate.now(), true, false,"12345678B");

        repo.insertCliente(cliente);

        System.out.println(cliente);

        assertThat(cliente.getId(), greaterThan(0));
    }
    @Test
    void dadoCliente_cuandoMailIncorrecto_entoncesClienteException() throws Exception {
        Cliente cli = new Personal(null, "Nombre", "ncdxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
        assertThrows(Exception.class, () -> {
            repo.insertCliente(cli);
        });
    }
    @Test
    void dadoIdClientePersonal_cuandoRecuperarPorId_entoncesOk() throws Exception {
        Cliente ncliente =  repo.getCliente(1);
        System.out.println(ncliente);
        assertThat(ncliente.getNombre(), is("Juan Juanez"));
    }

    @Test
    void dadoIdClienteEmpresa_cuandoRecuperarPorId_entoncesOk() throws Exception {
        Cliente ncliente =  repo.getCliente(3);
        System.out.println(ncliente);
        assertThat(ncliente.getNombre(), is("Servicios Informatico SL"));
    }
}