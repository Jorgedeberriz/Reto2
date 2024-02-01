package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@ActiveProfiles("dev")
@EnableAutoConfiguration
class ClientesControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @Autowired
    private IClientesRepoData repo;

    @Autowired
    private ClientesController clicon;

    /*@BeforeEach
    void sepUp() throws Exception {
        repo = new ClienteDBRepository();
    }*/


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    //@Test
    //void altaCliente_Ok() throws Exception {
    //    Cliente cli = new Personal(4, "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", LocalDate.now(), true, false, "87654321A");
    //    String[] args = {"personal", "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", String.valueOf(LocalDate.now()), "87654321A"};
    //    ClientesController.add(args);
    //    assertThat(ClientesInMemoryRepo.getInstance().getClientById(4), samePropertyValuesAs(cli));
    //    assertThat(outContent.toString(), containsString("Cliente añadido"));
    //}

    //@Test
    //void altaClienteMailIncorrecto() throws Exception {
    //    String[] args = {"Personal", "Nuevo Cliente", "ncdxc.com", "Calle Nueva", String.valueOf(LocalDate.now()), "87654321A"};
    //    ClientesController.add(args);
    //    System.out.println(outContent);
    //    assertThat(outContent.toString(), containsString("Cliente NO válido"));
    //}

    @Test
    void altaClienteDB_Ok() {
        String[] args = {"personal", "Nuevo cliente", "nc@dxc.com", "Calle Nueva 1", String.valueOf(LocalDate.now()), "87654321A"};
        clicon.add(args);
        assertThat(outContent.toString(), containsString("Cliente añadido"));
    }
    @Test
    void altaClienteMailIncorrectoDB() throws Exception {
        String[] args = {"Personal", "Nuevo Cliente", "ncdxc.com", "Calle Nueva", String.valueOf(LocalDate.now()), "87654321A"};
        clicon.add(args);
        System.out.println(outContent);
        assertThat(outContent.toString(), containsString("Cliente NO válido"));
    }


    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}