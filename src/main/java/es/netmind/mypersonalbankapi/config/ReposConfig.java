package es.netmind.mypersonalbankapi.config;

import es.netmind.mypersonalbankapi.persistencia.ClienteDBRepository;
import es.netmind.mypersonalbankapi.persistencia.ClientesInMemoryRepo;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ReposConfig {
/*
    @Value("${db_url}")
    String dbUrl;

    @Bean
    @Profile({"default"})
    public IClientesRepo getClienteDBRepository() throws Exception {
        ClienteDBRepository repo = new ClienteDBRepository();
        repo.setDb_url(dbUrl);
        System.out.println("Entra perfil default");
        return repo;
    }

    @Bean
    @Profile("dev")
    public IClientesRepo getClientesInMemory() throws Exception {
        ClientesInMemoryRepo repo = new ClientesInMemoryRepo();
        //repo.setDb_url(dbUrl);
        System.out.println("Entra perfil dev");
        return repo;
    }*/
}
