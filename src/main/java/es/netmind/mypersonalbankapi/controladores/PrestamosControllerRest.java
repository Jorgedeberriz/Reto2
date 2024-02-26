package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get clients loans", description = "Returna todos los prestamos de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "204", description = "Not content - empty list"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value="")
    public ResponseEntity<List<Prestamo>> getAll(@PathVariable Integer uid) {
        return new ResponseEntity<>(prestamosRepo.findByMyCliente_id(uid), HttpStatus.OK);
    }
    @Operation(summary = "Get a loan by id", description = "Retorna un prestamo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The loan was not found")
    })
    @GetMapping(value="/{lid}")
    public ResponseEntity<Prestamo> getOne(@PathVariable Integer uid, @PathVariable Integer lid) {

        return new ResponseEntity<>(prestamosRepo.findById(lid).get(), HttpStatus.OK);
    }

}
