package es.netmind.mypersonalbankapi.config;

import org.springframework.context.annotation.Configuration;

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
