package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientesRepoData extends JpaRepository<Cliente, Integer> {
     public Cliente findClienteById(Integer id);
}
