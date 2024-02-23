package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes/{uid}/prestamos")
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class PrestamosControllerRest {
    private static final Logger logger = LoggerFactory.getLogger(PrestamosControllerRest.class);
    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private IPrestamosRepoData prestamosRepo;
    @GetMapping(value="")
    public ResponseEntity<List<Prestamo>> getAll(@PathVariable Integer uid) {
        return new ResponseEntity<>(prestamosRepo.findByMyCliente_id(uid), HttpStatus.OK);
    }
    @GetMapping(value="/{lid}")
    public ResponseEntity<Prestamo> getOne(@PathVariable Integer uid, @PathVariable Integer lid) {

        return new ResponseEntity<>(prestamosRepo.findById(lid).get(), HttpStatus.OK);
    }

}
