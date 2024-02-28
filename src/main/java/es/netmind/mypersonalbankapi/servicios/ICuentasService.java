package es.netmind.mypersonalbankapi.servicios;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.CuentaException;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;

import java.util.List;

public interface ICuentasService {
    public List<Cuenta> getAll(Integer uid) throws ClienteException;
    public Cuenta getOne(Integer uid, Integer aid) throws ClienteException, CuentaException;

}
