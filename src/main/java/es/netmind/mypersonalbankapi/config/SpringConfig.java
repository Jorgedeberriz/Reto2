package es.netmind.mypersonalbankapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PropertiesConfig.class,ReposConfig.class})
@ComponentScan(basePackages = {"es.netmind.mypersonalbankapi.controladores","es.netmind.mypersonalbankapi.persistencia"})
public class SpringConfig {
}
