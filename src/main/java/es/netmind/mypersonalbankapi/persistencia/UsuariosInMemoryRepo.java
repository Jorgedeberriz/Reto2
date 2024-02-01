package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosInMemoryRepo implements IUsuariosRepo {
    private static UsuariosInMemoryRepo instance;
    private final static List<Usuario> usuarios;

    static {
        usuarios = new ArrayList<>();
        try {
            usuarios.add(new Usuario(1, "pepe", "admin", "sdfwete"));
            usuarios.add(new Usuario(2, "maria", "gestor", "dfdtwew"));
            usuarios.add(new Usuario(3, "albert", "usuario", "dfertsdfg"));
        } catch (Exception e) {
            System.out.println("⚠ Error al crear clientes: " + e.getMessage());
        }
    }

    private UsuariosInMemoryRepo() {
    }

    public static UsuariosInMemoryRepo getInstance() {
        if (instance == null) instance = new UsuariosInMemoryRepo();
        return instance;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Usuario addUsuario(Usuario cliente) throws Exception {
        return null;
    }

    @Override
    public boolean deleteUsuario(Usuario cliente) throws Exception {
        return false;
    }

    @Override
    public Usuario updateUsuario(Usuario cliente) throws Exception {
        return null;
    }

}
