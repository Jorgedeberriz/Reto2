package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.GlobalException;
import es.netmind.mypersonalbankapi.modelos.StatusMessage;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.IPrestamosRepoData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/clientes/{uid}/prestamos")
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class PrestamosControllerRest {
    private static final Logger logger = LoggerFactory.getLogger(PrestamosControllerRest.class);
    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private IPrestamosRepoData prestamosRepo;

    @Operation(summary = "Get clients loans", description = "Retorna todos los prestamos de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "204", description = "Not content - empty list"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value = "")
    public ResponseEntity<Object> getAll(
            @Parameter(name = "id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid) {
        if (!clientesRepo.existsById(uid))
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(), "Cliente inexistente"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(prestamosRepo.findByMyCliente_id(uid), HttpStatus.OK);
    }

    @Operation(summary = "Get a loan by id", description = "Retorna un prestamo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The loan was not found")
    })
    @GetMapping(value = "/{lid}")
    public ResponseEntity<Object> getOne(
            @Parameter(name = "id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid,
            @Parameter(name = "id", description = "Loan id", example = "1", required = true)
            @PathVariable @Min(1) Integer lid) {

        if (!clientesRepo.existsById(uid))
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(), "Cliente inexistente"), HttpStatus.NOT_FOUND);
        if (!prestamosRepo.existsById(lid))
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(), "Prestamo inexistente"), HttpStatus.NOT_FOUND) ;

        Prestamo pr = prestamosRepo.findById(lid).get();
        if (uid == pr.getId()) {
            return new ResponseEntity<>(pr, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Prestamo no pertenece al cliente"),
                    HttpStatus.PRECONDITION_FAILED);


        }

    }
}
