package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Setter
@Getter
@Repository
public class ClienteJPARepository implements IClientesRepo{
    @PersistenceContext  // Accede al emf; emf.createEntityManager();
    private EntityManager em;

    @Override
    public List<Cliente> getAll() {
        return null;
    }

    @Override
    public Cliente getClientById(Integer id) throws Exception {
        return null;
    }
    @Override
    @Transactional
    public Cliente addClient(Cliente cliente) throws Exception {
        em.persist(cliente);
        return cliente;
    }

    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }
}
