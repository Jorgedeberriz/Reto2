package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.ErrorCode;
import es.netmind.mypersonalbankapi.modelos.StatusMessage;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Validated
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class ClientesControllerRest {
    private static final Logger logger = LoggerFactory.getLogger(ClientesControllerRest.class);
    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private ICuentasRepoData cuentasRepo;
    @Autowired
    private IPrestamosRepoData prestamosRepo;
    @GetMapping(value="")
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<>(clientesRepo.findAll(), HttpStatus.OK);
    }
    @GetMapping(value="/{uid}")
    public ResponseEntity<Cliente> getOne(@PathVariable Integer uid) {
        return new ResponseEntity<>(clientesRepo.findById(uid).get(), HttpStatus.OK);
    }
    @PostMapping(value="/personal")
    public ResponseEntity<Cliente> save( @RequestBody @Valid Personal cliente) {
        cliente.setId(null);
        //System.out.println("Tipo cliente: " + tipoCliente);
        return new ResponseEntity<>(clientesRepo.save(cliente), HttpStatus.CREATED);

    }
    @PostMapping(value="/empresa")
    public ResponseEntity<Cliente> saveEmpresa( @RequestBody @Valid Empresa cliente) {
        cliente.setId(null);
        return new ResponseEntity<>(clientesRepo.save(cliente), HttpStatus.CREATED);

    }
    @PutMapping(value ="/personal/{uid}")
    public ResponseEntity<Object> updatePersonal(@PathVariable Integer uid, @RequestBody @Valid Personal cliente) {
        if (uid == cliente.getId()) {
            return new ResponseEntity<>(clientesRepo.save(cliente), HttpStatus.ACCEPTED) ;
        } else {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Id y product.id deben coincidir"), HttpStatus.PRECONDITION_FAILED) ;
        }
    }
    @PutMapping(value ="/empresa/{uid}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable Integer uid, @RequestBody @Valid Empresa cliente) {
        if (uid == cliente.getId()) {
            return new ResponseEntity<>(clientesRepo.save(cliente), HttpStatus.ACCEPTED) ;
        } else {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Id y product.id deben coincidir"), HttpStatus.PRECONDITION_FAILED) ;
        }
    }
    @GetMapping(value = "/evaluarPrestamo/{uid}")
    public ResponseEntity<Object> evaluarPrestamo(@PathVariable Integer uid, @RequestParam Double monto) {

        try {
            Optional<Cliente> opCliente = clientesRepo.findById(uid);
            Cliente cliente = opCliente.get();
            int numPrestamos = cliente.getPrestamos() != null ? cliente.getPrestamos().size() : 0;


            Prestamo prestamoSolictado = new Prestamo(null, LocalDate.now(), monto, monto, 10, 5, false, false, 5);

            boolean aceptable = cliente.evaluarSolicitudPrestamo(prestamoSolictado);
            if (aceptable) {
                return new ResponseEntity<>(new StatusMessage(HttpStatus.ACCEPTED.value(), "SÍ se puede conceder !!"), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(),  "NO puede conceder !! Saldo insuficiente."),HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ClienteException("Oops ha habido un problema, inténtelo más tarde 😞!",null) ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
