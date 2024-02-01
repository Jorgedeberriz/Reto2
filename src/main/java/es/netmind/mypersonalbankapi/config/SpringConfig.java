package es.netmind.mypersonalbankapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import({PropertiesConfig.class,ReposConfig.class})
@ComponentScan(basePackages = {"es.netmind.mypersonalbankapi.controladores","es.netmind.mypersonalbankapi.persistencia"})
@EntityScan("es.netmind.mypersonalbankapi.modelos")
@EnableJpaRepositories(basePackages = "es.netmind.mypersonalbankapi.persistencia")
public class SpringConfig {
}
