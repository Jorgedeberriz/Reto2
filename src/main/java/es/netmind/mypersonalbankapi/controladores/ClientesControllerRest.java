package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class ClientesControllerRest {
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
    @PostMapping(value="")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clientesRepo.save(cliente), HttpStatus.CREATED);
    }
}
