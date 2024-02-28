package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.PrestamoException;
import es.netmind.mypersonalbankapi.modelos.StatusMessage;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.servicios.ICuentasService;
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
@RequestMapping("/clientes/{uid}/cuentas")
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class CuentasControllerRest {
    private static final Logger logger = LoggerFactory.getLogger(CuentasControllerRest.class);
    @Autowired
    private ICuentasService cuentasService;

    @Operation(summary = "Get clients accounts", description = "Returna todos las cuentas de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "204", description = "Not content - empty list"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping(value = "")
    public ResponseEntity<List<Cuenta>> getAll(
            @Parameter(name = "id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid) {
        try {
            List<Cuenta> lista = cuentasService.getAll(uid);
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cuentasService.getAll(uid), HttpStatus.OK);

        } catch (ClienteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get a account by id", description = "Retorna una cuenta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The account was not found"),
            @ApiResponse(responseCode = "412", description = "Precondition failed")
    })
    @GetMapping(value = "/{aid}")
    public ResponseEntity<Object> getOne(
            @Parameter(name = "id", description = "Client id", example = "1", required = true)
            @PathVariable @Min(1) Integer uid,
            @Parameter(name = "id", description = "Account id", example = "1", required = true)
            @PathVariable @Min(1) Integer aid) {


        try {
            Cuenta cu = cuentasService.getOne(uid, aid);
            if (uid == cu.getId()) {
                return new ResponseEntity<>(cu, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Cuenta no pertenece al cliente"),
                        HttpStatus.PRECONDITION_FAILED);
            }
        } catch (ClienteException  e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }   catch (PrestamoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
