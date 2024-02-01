package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
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
class CuentaDataRepositoryTest {

    @Autowired
    private ICuentasRepoData repo;

    @Test
    void testBeans() {
        assertNotNull(repo);
        //System.out.println(repo.getDb_url());
    }

    @Test
    void dadouncliente_recuperarListaCuentas() throws Exception {
        List<Cuenta> lista = repo.findByMyCliente_Id(3);
        System.out.println("lista:" + lista);

        assertThat(lista.size(), greaterThan(0));
    }
    @Test
    void dadounaCuenta_mostrarDetalle() {
        Optional<Cuenta> optionalCuenta = repo.findById(1);
        Cuenta cuenta = optionalCuenta.get();
        assertEquals(cuenta.getId(),1);
    }
}