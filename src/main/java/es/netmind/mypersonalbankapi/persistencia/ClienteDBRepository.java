package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.usuario.Usuario;
import es.netmind.mypersonalbankapi.properties.PropertyValues;

import java.sql.*;
import java.util.List;

public class ClienteDBRepository implements IClienteDBRepository {
    private static String db_url = null;

    public ClienteDBRepository() throws Exception {
        PropertyValues props = new PropertyValues();
        db_url = props.getPropValues().getProperty("db_url");
    }


    @Override
    public boolean existeCliente(String email, String pass) throws Exception {
        return false;
    }

    @Override
    public Cliente getCliente(Integer id) throws ClienteException, Exception {
        return null;
    }

    @Override
    public List<Cliente> getClientes(String iniciales) throws Exception {
        return null;
    }

    @Override
    public Cliente insertUsuario(Cliente nuevoCliente) throws Exception {
                String sql = "INSERT INTO usuario values (?,NULL,?,?,?,?,?,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, nuevoCliente.getClass().getName());
            stmt.setInt(3, nuevoCliente.isActivo() ? 1 : 0);
            stmt.setString(4,nuevoCliente.getAlta().toString());
            stmt.setString(5,nuevoCliente.getDireccion());
            




            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                nuevoCliente.setId(genKeys.getInt(2));
            } else {
                throw new SQLException("Usuario creado erroneamente!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return nuevoCliente;

    }

    @Override
    public Cliente updateCliente(Usuario unCliente) throws Exception {
        return null;
    }

    @Override
    public boolean deleteCliente(Integer id) throws Exception {
        return false;
    }
}
