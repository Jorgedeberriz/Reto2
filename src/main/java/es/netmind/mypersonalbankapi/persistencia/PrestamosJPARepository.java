package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Setter
@Getter
@Repository
public class PrestamosJPARepository implements IPrestamosRepo{
    @PersistenceContext  // Accede al emf; emf.createEntityManager();
    private EntityManager em;

    @Override
    public List<Prestamo> getAll() {
        return null;
    }

    @Override
    public Prestamo getLoanById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Prestamo addLoan(Prestamo prestamo) throws Exception {
        return null;
    }

    @Override
    public boolean deleteLoan(Prestamo prestamo) throws Exception {
        return false;
    }

    @Override
    public Prestamo updateLoan(Prestamo prestamo) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public List<Prestamo> getLoansByClient(Integer uid) throws Exception {
        TypedQuery query = em.createQuery("SELECT s FROM Prestamo s WHERE cliente_id = :cliente_id", Prestamo.class);
          query.setParameter("cliente_id",uid);
        List<Prestamo> result = query.getResultList();
          return result;
    }

    @Override
    public Prestamo getLoansByClientAndId(Integer uid, Integer aid) throws Exception {
        return null;
    }
}
