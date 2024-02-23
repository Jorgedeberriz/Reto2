package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes/{uid}/cuentas")
@Tag(name = "MyPersonalBank", description = "My Personal Bank APIs")
public class CuentasControllerRest {
    private static final Logger logger = LoggerFactory.getLogger(CuentasControllerRest.class);
    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private ICuentasRepoData cuentasRepo;
    @GetMapping(value="")
    public ResponseEntity<List<Cuenta>> getAll(@PathVariable Integer uid) {
        return new ResponseEntity<>(cuentasRepo.findByMyCliente_id(uid), HttpStatus.OK);
    }
    @GetMapping(value="/{aid}")
    public ResponseEntity<Cuenta> getOne(@PathVariable Integer uid, @PathVariable Integer aid) {

        return new ResponseEntity<>(cuentasRepo.findById(aid).get(), HttpStatus.OK);
    }

}
