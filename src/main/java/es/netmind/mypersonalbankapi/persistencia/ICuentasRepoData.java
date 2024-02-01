package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentasRepoData extends JpaRepository<Cuenta, Integer> {
}
