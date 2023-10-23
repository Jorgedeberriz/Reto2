package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import org.junit.jupiter.api.Test;
import es.netmind.mypersonalbankapi.exceptions.*;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientesControllerTest {
    @Test
    void altaCliente_Ok() throws Exception {
        Cliente cli = new Personal(4, "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
        String[] args = {"personal", "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", String.valueOf(LocalDate.now()), "87654321A"};
        ClientesController.add(args);
        assertThat(ClientesInMemoryRepo.getInstance().getClientById(4),samePropertyValuesAs(cli));
    }

    @Test
    void dadoCliente_cuandoEmailIncorrecto_entoncesClienteException() throws Exception {
        Cliente cli = new Personal(4, "Nuevo cliente", "ncdxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
        assertThrows(ClienteException.class, () -> {
            ClientesInMemoryRepo.getInstance().addClient(cli);
        });
    }


}