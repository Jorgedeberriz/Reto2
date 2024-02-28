package es.netmind.mypersonalbankapi.servicios;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.exceptions.CuentaException;
import es.netmind.mypersonalbankapi.modelos.StatusMessage;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepoData;
import es.netmind.mypersonalbankapi.persistencia.ICuentasRepoData;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@Service
public class CuentasService implements ICuentasService {
    private static final Logger logger = LoggerFactory.getLogger(CuentasService.class);
    @Autowired
    private IClientesRepoData clientesRepo;
    @Autowired
    private ICuentasRepoData cuentasRepo;
    public List<Cuenta> getAll(Integer uid) throws ClienteException {
        if (!clientesRepo.existsById(uid))
            throw  new ClienteException( "Cliente inexistente");
        return cuentasRepo.findByMyCliente_id(uid);
    }
    public Cuenta getOne(Integer uid, Integer aid) throws ClienteException, CuentaException {
        if (!clientesRepo.existsById(uid))
            throw  new ClienteException( "Cliente inexistente");
        if (!cuentasRepo.existsById(aid))
            throw  new CuentaException( "Cuenta inexistente");
        return cuentasRepo.findById(aid).get();
    }

}
