package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
//@ActiveProfiles("default")
@EnableAutoConfiguration

class ClienteDataRepositoryTest {

    @Autowired
    private IClientesRepoData repo;

    //@BeforeEach
    //void setUp() throws Exception {
    //    repo = new ClienteDBRepository();
    //}
    @Test
    void testBeans() {
        assertNotNull(repo);
        //System.out.println(repo.getDb_url());
    }

    @Test
    @Transactional
    void dadosClienteEmpresa_cuandoinsertarClienteEnDB_entoncesIdValido() throws Exception {
        Cliente cliente = new Empresa(null, "nuevoClienteE", "nce@nce.com", "C/Nueva Empresa", LocalDate.now(), true, false, "B12345678", null);

        repo.save(cliente);

        System.out.println(cliente);

        assertThat(cliente.getId(), greaterThan(0));
    }

    @Test
    void dadosClientePersonal_cuandoinsertarClienteEnDB_entoncesIdValido() throws Exception {
        Cliente cliente = new Personal(null, "nuevoClienteP", "ncp@ncp.com", "C/Nueva Persona", LocalDate.now(), true, false, "12345678B");

        repo.save(cliente);

        System.out.println(cliente);

        assertThat(cliente.getId(), greaterThan(0));
    }

    @Test
    @Transactional
    void dadoIdClientePersonal_cuandoRecuperarPorId_entoncesOk() throws Exception {
        Optional<Cliente> opCliente = repo.findById(1);
        Cliente ncliente = opCliente.get();
        System.out.println(ncliente);
        assertThat(ncliente.getNombre(), is("Juan Juanez"));
    }

    @Test
    @Transactional
    void dadoIdClienteEmpresa_cuandoRecuperarPorId_entoncesOk() throws Exception {
        Optional<Cliente> opCliente = repo.findById(3);
        Cliente ncliente = opCliente.get();
        System.out.println(ncliente);
        assertThat(ncliente.getNombre(), is("Servicios Informatico SL"));
    }

    @Test
    @Transactional   // internamente se hacen 2 queries cuando hacemos el sout ya que escuela tiene lista de estudiantes
    void getAll() {
        List<Cliente> clientes = repo.findAll();
        //System.out.println("clientes" + clientes);
        assertNotNull(clientes);
        assertTrue(clientes.size() >= 0);
    }

}