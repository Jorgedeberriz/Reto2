package es.netmind.mypersonalbankapi.config;

import es.netmind.mypersonalbankapi.persistencia.ClienteDBRepository;
import es.netmind.mypersonalbankapi.persistencia.IClienteDBRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReposConfig {

    @Value("${db_url}")
    String dbUrl;
    @Bean
    public IClienteDBRepository getClienteDBRepository() throws Exception {
        ClienteDBRepository repo = new ClienteDBRepository();
        repo.setDb_url(dbUrl);
        return repo;
    }
}
