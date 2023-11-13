package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
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
    public Cliente insertCliente(Cliente nuevoCliente) throws Exception {
        String sql = "INSERT INTO cliente values (?,NULL,?,?,?,?,?,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            String[] classnameArray = nuevoCliente.getClass().getName().split("\\.");
            String classname = classnameArray[classnameArray.length-1];
            stmt.setString(1, classname);
            stmt.setInt(2, nuevoCliente.isActivo() ? 1 : 0);
            stmt.setString(3, nuevoCliente.getAlta().toString());
            stmt.setString(4, nuevoCliente.getDireccion());
            stmt.setString(5, nuevoCliente.getEmail());
            stmt.setInt(6, nuevoCliente.isMoroso() ? 1 : 0);
            stmt.setString(7, nuevoCliente.getNombre());
            if (nuevoCliente instanceof Empresa) {
                stmt.setString(8, ((Empresa) nuevoCliente).getCif());
                if (((Empresa) nuevoCliente).getUnidadesNegocio() != null) {
                    stmt.setString(9, ((Empresa) nuevoCliente).getUnidadesNegocio().toString());
                }else stmt.setString(9, null);
                stmt.setString(10, null);
            } else if (nuevoCliente instanceof Personal) {
                stmt.setString(8, null);
                stmt.setString(9, null);
                stmt.setString(10, ((Personal) nuevoCliente).getDni());
            }

            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                nuevoCliente.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Cliente creado erroneamente!!!");
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
