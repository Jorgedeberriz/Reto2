package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.usuario.Usuario;

import java.util.List;

public interface IUsuariosRepo {
    public List<Usuario> getAll();

    public Usuario getUsuarioById(Integer id) throws Exception;

    public Usuario addUsuario(Usuario cliente) throws Exception;

    public boolean deleteUsuario(Usuario cliente) throws Exception;

    public Usuario updateUsuario(Usuario cliente) throws Exception;

}
