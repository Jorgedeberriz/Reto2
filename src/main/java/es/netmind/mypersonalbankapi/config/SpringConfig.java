package es.netmind.mypersonalbankapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ReposConfig.class})
@ComponentScan(basePackages = {"es.netmind.mypersonalbankapi.controladores"})
public class SpringConfig {
}
