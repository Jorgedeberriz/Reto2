package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICuentasRepoData extends JpaRepository<Cuenta, Integer> {
    public List<Cuenta> findByMyCliente_Id(int id);
}
