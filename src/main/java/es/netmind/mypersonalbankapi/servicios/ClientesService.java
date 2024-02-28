package es.netmind.mypersonalbankapi.servicios;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesService implements IClientesServices{

    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private ICuentasRepoData cuentasRepo;
    @Autowired
    private IPrestamosRepoData prestamosRepo;


    public List<Cliente> getAll() {
        return clientesRepo.findAll();
    }

    public Cliente getOne(Integer uid) throws ClienteException {
        //if (!clientesRepo.existsById(uid)) throw new GlobalException("Cliente no encontrado");
        if (!clientesRepo.existsById(uid))
            throw new ClienteException("Cliente no encontrado");
        return clientesRepo.findById(uid).get();
    }

    public Cliente save(Personal cliente) {
        cliente.setId(null);
        //System.out.println("Tipo cliente: " + tipoCliente);
        return clientesRepo.save(cliente);
    }

    public Cliente saveEmpresa(Empresa cliente) {
        cliente.setId(null);
        clientesRepo.save(cliente);
        return cliente;

    }

    public Cliente updatePersonal(Integer uid, Personal cliente) throws ClienteException {
        if (!clientesRepo.existsById(uid))
            throw new ClienteException("Cliente no encontrado");
        return clientesRepo.save(cliente);

    }

    public Cliente updateEmpresa(Integer uid, Empresa cliente) {
        if (!clientesRepo.existsById(uid))
            throw new ClienteException("Cliente inexistente");
        return clientesRepo.save(cliente);
    }

    public boolean evaluarPrestamo(Integer uid, Double monto) throws ClienteException {

        if (!clientesRepo.existsById(uid))
            throw new ClienteException("Cliente inexistente");

        Optional<Cliente> opCliente = clientesRepo.findById(uid);
        Cliente cliente = opCliente.get();
        int numPrestamos = cliente.getPrestamos() != null ? cliente.getPrestamos().size() : 0;


        Prestamo prestamoSolictado = new Prestamo(null, LocalDate.now(), monto, monto, 10, 5, false, false, 5);

        return cliente.evaluarSolicitudPrestamo(prestamoSolictado);

    }
}
