package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.ErrorCode;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.properties.PropertyValues;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.List;
@Getter
@Setter
public class ClienteDBRepository implements IClientesRepo {
    private String db_url = null;

    public ClienteDBRepository() throws Exception {
        PropertyValues props = new PropertyValues();
        db_url = props.getPropValues().getProperty("db_url");
    }


    @Override
    public List<Cliente> getAll() {
        return null;
    }

    @Override
    public Cliente getClientById(Integer id) throws ClienteException, Exception {
        Cliente cliente = null;

        try (
                Connection conn = DriverManager.getConnection(db_url);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM cliente c WHERE c.id='" + id + "' LIMIT 1")
        ) {
            if (rs.next()) {
                if (rs.getString("dtype").equals("Empresa")) {
                    String[] unidades = null;
                    if (rs.getString("unidades_de_negocio") != null) {
                        unidades = rs.getString("unidades_de_negocio").split("\\-");  //Teniendo en cuenta que las unidades se separararian por guion
                    }
                    cliente = new Empresa(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("cif"),
                            unidades
                    );
                } else if (rs.getString("dtype").equals("Personal")) {
                    cliente = new Personal(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("dni")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return cliente;
    }


    @Override
    public Cliente addClient(Cliente nuevoCliente) throws Exception {
        if (!nuevoCliente.validar()) {
            throw new ClienteException("Cliente no válido", ErrorCode.INVALIDCLIENT);
        } else {

            String sql = "INSERT INTO cliente values (?,NULL,?,?,?,?,?,?,?,?,?)";

            try (
                    Connection conn = DriverManager.getConnection(db_url);
                    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ) {
                nuevoCliente.validar();
                String[] classnameArray = nuevoCliente.getClass().getName().split("\\.");
                String classname = classnameArray[classnameArray.length - 1];
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
                    } else stmt.setString(9, null);
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
    }

    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }

}
