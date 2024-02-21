package es.netmind.mypersonalbankapi.controladores;

import com.mysql.cj.xdevapi.Client;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
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
    public ResponseEntity<Cliente> save( @RequestBody Personal cliente) {
        cliente.setId(null);
        //System.out.println("Tipo cliente: " + tipoCliente);
        return new ResponseEntity<>(clientesRepo.save(cliente), HttpStatus.CREATED);

    }
    @PostMapping(value="/empresa")
    public ResponseEntity<Cliente> saveEmpresa( @RequestBody Empresa cliente) {
        cliente.setId(null);
        //System.out.println("Tipo cliente: " + tipoCliente);
        return new ResponseEntity<>(clientesRepo.save(cliente), HttpStatus.CREATED);

    }
}
