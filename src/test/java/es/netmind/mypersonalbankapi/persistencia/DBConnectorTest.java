package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class DBConnectorTest {
    @Autowired
    private DBConnector dbc;
    @Test
    void connect_isOK() {
        try {
            dbc.connect();
            assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
