package es.netmind.mypersonalbankapi.servicios;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.PrestamoException;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;

import java.util.List;

public interface IPrestamoService {
    public List<Prestamo> getAll(Integer uid) throws ClienteException;

    public Prestamo getOne(Integer uid, Integer lid) throws ClienteException, PrestamoException;
}
