package es.netmind.mypersonalbankapi.persistencia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBConnectorTest {
    @Test
    void connect_isOK() {
        DBConnector dbc = new DBConnector();
        try {
            dbc.connect();
            assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
