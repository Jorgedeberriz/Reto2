package es.netmind.mypersonalbankapi.modelos.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Usuario {
    private Integer idUsuario;
    private String name;
    private String perfil;
    private String contrasenya;

}
