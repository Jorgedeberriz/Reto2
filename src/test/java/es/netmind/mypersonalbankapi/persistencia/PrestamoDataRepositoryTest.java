package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
class PrestamoDataRepositoryTest {

    @Autowired
    private IPrestamosRepoData repo;

    @Test
    void testBeans() {
        assertNotNull(repo);
        //System.out.println(repo.getDb_url());
    }

    @Test
    void dadouncliente_recuperarListaPrestamos() throws Exception {
        List<Prestamo> lista = repo.findByMyCliente_Id(3);
        System.out.println("lista:" + lista);

        assertThat(lista.size(), greaterThan(0));
    }
    @Test
    void dadounPrestamo_mostrarDetalle() {
        Optional<Prestamo> opPres = repo.findById(1);
        Prestamo pres = opPres.get();
        assertEquals(pres.getId(),1);
    }
}