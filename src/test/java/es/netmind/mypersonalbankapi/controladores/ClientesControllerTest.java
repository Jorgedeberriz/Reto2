package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

    @Test
    void mostrarLista() {
        clicon.mostrarLista();
        assertThat(outContent.toString(), containsString("Juan"));
    }

    @Test
    void mostrarDetalle() {
        clicon.mostrarDetalle(1);
        assertThat(outContent.toString(), containsString("Juan"));
    }

    @Test
    void eliminar() throws Exception {
        clicon.eliminar(35);
        assertThat(outContent.toString(), containsString("Cliente borrado 🙂!!"));
    }

    @Test
    void actualizar() {
        String[] args = {null, null, "nueva direccion", null, null, null, null};
        clicon.actualizar(3,args);
        assertThat(outContent.toString(), containsString("Cliente actualizado"));
    }

    @Test
    void dadoClienteSinPrestamosySaldoenCuenta_cuandoEvaluarPrestamo_entoncesOK() {
        clicon.evaluarPrestamo(2, 200D);
        assertThat(outContent.toString(), containsString("SÍ se puede conceder"));
    }
    @Test
    void dadoClienteConPrestamosyPocoSaldoenCuenta_cuandoEvaluarPrestamo_entoncesNOK() {
        clicon.evaluarPrestamo(3, 2000D);
        assertThat(outContent.toString(), containsString("NO puede conceder"));
    }
}