package es.netmind.mypersonalbankapi.servicios;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.PrestamoException;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamosServices implements IPrestamoService{
    private static final Logger logger = LoggerFactory.getLogger(PrestamosServices.class);
    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private IPrestamosRepoData prestamosRepo;

    public List<Prestamo> getAll(Integer uid) throws ClienteException {
        if (!clientesRepo.existsById(uid))
            throw  new ClienteException("Cliente inexistente");

        return prestamosRepo.findByMyCliente_id(uid);
    }

    public Prestamo getOne(Integer uid, Integer lid) throws ClienteException, PrestamoException {

        if (!clientesRepo.existsById(uid))
            throw  new ClienteException( "Cliente inexistente");
        if (!prestamosRepo.existsById(lid))
            throw  new PrestamoException( "Prestamo inexistente");

        return prestamosRepo.findById(lid).get();
    }
}
