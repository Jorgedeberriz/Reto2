package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPrestamosRepoData extends JpaRepository<Prestamo, Integer> {
    public List<Prestamo> findByMyCliente_Id(int id);
}
