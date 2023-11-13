package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

class ClienteDBRepositoryTest {
    private IClienteDBRepository repo;

    @BeforeEach
    void setUp() throws Exception {
        repo = new ClienteDBRepository();
    }

    @Test
    void dadosClienteEmpresa_cuandoinsertarClienteEnDB_entoncesIdValido() throws Exception {
        Cliente cliente = new Empresa(null, "nuevoClienteE", "nce@nce.com", "C/Nueva E", LocalDate.now(), true, false,"B12345678",null);

        repo.insertCliente(cliente);

        System.out.println(cliente);

        assertThat(cliente.getId(), greaterThan(0));
    }

     @Test
    void dadosClientePersonal_cuandoinsertarClienteEnDB_entoncesIdValido() throws Exception {
        Cliente cliente = new Personal(null, "nuevoClienteP", "ncp@ncp.com", "C/Nueva P", LocalDate.now(), true, false,"12345678B");

        repo.insertCliente(cliente);

        System.out.println(cliente);

        assertThat(cliente.getId(), greaterThan(0));
    }
}