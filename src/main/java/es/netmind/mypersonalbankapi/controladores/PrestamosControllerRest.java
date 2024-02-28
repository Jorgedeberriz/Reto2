package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.PrestamoException;
import es.netmind.mypersonalbankapi.modelos.StatusMessage;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.servicios.IPrestamoService;
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
import java.util.List;

@RestController
@Validated
@RequestMapping("/clientes/{uid}/prestamos")
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class PrestamosControllerRest {
    private static final Logger logger = LoggerFactory.getLogger(PrestamosControllerRest.class);

    @Autowired
    private IPrestamoService prestamosService;

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
        try {
            List<Prestamo> lista = prestamosService.getAll(uid);
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(prestamosService.getAll(uid), HttpStatus.OK);
        } catch (ClienteException e) {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(),e.getMessage()), HttpStatus.NOT_FOUND);
        }
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
        try {
            Prestamo pr = prestamosService.getOne(uid, lid);
            if (uid == pr.getMyCliente().getId()) {
                return new ResponseEntity<>(pr, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Prestamo no pertenece al cliente"),
                        HttpStatus.PRECONDITION_FAILED);
            }
        } catch (ClienteException | PrestamoException e) {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(),e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }
}
