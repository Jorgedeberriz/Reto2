package es.netmind.mypersonalbankapi.servicios;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;

import java.util.List;

public interface IClientesServices {

    public List<Cliente> getAll();

    public Cliente getOne(Integer uid) throws ClienteException;

    public Cliente save(Personal cliente);

    public Cliente saveEmpresa(Empresa cliente);

    public Cliente updatePersonal(Integer uid, Personal cliente) throws ClienteException;

    public Cliente updateEmpresa(Integer uid, Empresa cliente);

    public boolean evaluarPrestamo(Integer uid, Double monto) throws ClienteException;
}
