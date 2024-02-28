package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.StatusMessage;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
import es.netmind.mypersonalbankapi.servicios.IClientesServices;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Validated
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class ClientesControllerRest {
    private static final Logger logger = LoggerFactory.getLogger(ClientesControllerRest.class);
    @Autowired
    private IClientesServices clientesService;

    @Operation(summary = "Get clients", description = "Retorna todos los clientes del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "204", description = "Not content - empty list")
    })
    @GetMapping(value="")
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<>(clientesService.getAll(), HttpStatus.OK);
    }
    @Operation(summary = "Get a client by id", description = "Retorna un cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The client was not found")
    })
    @GetMapping(value="/{uid}")
    public ResponseEntity<Object> getOne(
            @Parameter(name="id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid) {
        return new ResponseEntity<>(clientesService.getOne(uid), HttpStatus.OK);
    }
    @Operation(summary = "Add personal client", description = "Alta cliente tipo personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request - Not valid client"),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity")
    })
    @PostMapping(value="/personal")
    public ResponseEntity<Cliente> save( @RequestBody @Valid Personal cliente) {
        cliente.setId(null);
        return new ResponseEntity<>(clientesService.save(cliente), HttpStatus.CREATED);

    }
    @Operation(summary = "Add business client", description = "Alta cliente tipo empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request - Not valid client"),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity")
    })
    @PostMapping(value="/empresa")
    public ResponseEntity<Cliente> saveEmpresa( @RequestBody @Valid Empresa cliente) {
        cliente.setId(null);
        return new ResponseEntity<>(clientesService.saveEmpresa(cliente), HttpStatus.CREATED);

    }
    @Operation(summary = "Modify personal client", description = "Modificacion cliente tipo personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad request - Not valid client"),
            @ApiResponse(responseCode = "412", description = "Precondition failed"),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity")
    })
    @PutMapping(value ="/personal/{uid}")
    public ResponseEntity<Object> updatePersonal(
            @Parameter(name="id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid,
            @RequestBody @Valid Personal cliente) {
        if (uid == cliente.getId()) {
            return new ResponseEntity<>(clientesService.updatePersonal(uid, cliente), HttpStatus.ACCEPTED) ;
        } else {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Id y cliente.id deben coincidir"), HttpStatus.PRECONDITION_FAILED) ;
        }
    }
    @Operation(summary = "Modify business client", description = "Modificacion cliente tipo empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad request - Not valid client"),
            @ApiResponse(responseCode = "412", description = "Precondition failed"),
            @ApiResponse(responseCode = "422", description = "Unprocessable entity")
    })
    @PutMapping(value ="/empresa/{uid}")
    public ResponseEntity<Object> updateEmpresa(
            @Parameter(name="id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid,
            @RequestBody @Valid Empresa cliente) {
        if (uid == cliente.getId()) {
            return new ResponseEntity<>(clientesService.updateEmpresa(uid, cliente), HttpStatus.ACCEPTED) ;
        } else {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Id y cliente.id deben coincidir"), HttpStatus.PRECONDITION_FAILED) ;
        }
    }
    @Operation(summary = "Evaluate client loan", description = "Evaluar solicitud prestamo de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad request - Loan not accepted"),
            @ApiResponse(responseCode = "412", description = "Precondition failed"),
    })

    @GetMapping(value = "/evaluarPrestamo/{uid}")
    public ResponseEntity<Object> evaluarPrestamo(
            @Parameter(name="id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid,
            @Parameter(name="id", description = "Amount", example = "1", required = true)
            @RequestParam @Min(1) Double monto) {

        try {
            boolean aceptable = clientesService.evaluarPrestamo(uid, monto);
            if (aceptable) {
                return new ResponseEntity<>(new StatusMessage(HttpStatus.ACCEPTED.value(), "SÍ se puede conceder !!"), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(),  "NO puede conceder !! Saldo insuficiente."),HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ClienteException("Oops ha habido un problema, inténtelo más tarde 😞!") ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
