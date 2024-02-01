package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrestamosRepoData extends JpaRepository<Prestamo, Integer> {
}
