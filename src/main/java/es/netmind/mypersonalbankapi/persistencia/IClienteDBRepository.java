package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.usuario.Usuario;

import java.util.List;

public interface IClienteDBRepository {

    public boolean existeCliente(String email, String pass) throws Exception;

    public Cliente getCliente(Integer id) throws ClienteException, Exception;

    public List<Cliente> getClientes(String iniciales) throws Exception;

    public Cliente insertCliente(Cliente nuevoCliente) throws Exception;

    public Cliente updateCliente(Usuario unCliente) throws Exception;

    public boolean deleteCliente(Integer id) throws Exception;


}
