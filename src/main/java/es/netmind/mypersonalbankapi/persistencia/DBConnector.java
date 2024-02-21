package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.properties.PropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class DBConnector {
    /*@Value("${db_url}")*/
    String dbUrl;
    public void connect() throws SQLException, IOException {
        String db_url = dbUrl;
        Connection conn = DriverManager.getConnection(db_url);
        System.out.println("Conectado!");
        conn.close();
    }
}
