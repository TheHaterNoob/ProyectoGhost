package DAO;

import Home.Player;
import java.util.ArrayList;

public class PlayerDAO {

    public ArrayList<Player> usuarios;

    public PlayerDAO() {
        usuarios = new ArrayList<>();

    }

    public int buscar(String usuario) {
        int n = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(usuario)) {
                n = i;
                break;
            }
        }
        return n;
    }

    public boolean insertar(Player usuario) {
        if (buscar(usuario.getUsuario()) == -1) {
            usuarios.add(usuario);
            return true;
        } else {
            return false;
        }
    }

    public boolean modificar(Player usuario) {
        if (buscar(usuario.getUsuario()) != -1) {
            Player usuarioaux = obtener(usuario.getUsuario());
            usuarioaux.setContrasenia(usuario.getContrasenia());
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(String usuario) {
        if (buscar(usuario) != -1) {
            usuarios.remove(buscar(usuario));
            return true;
        } else {
            return false;
        }
    }

    public Player obtener(String usuario) {

        if (buscar(usuario) != -1) {
            return usuarios.get(buscar(usuario));
        } else {
            return null;
        }
    }

    public boolean autentificar(String usuario, String contrasenia) {
        if (obtener(usuario) != null) {
            Player usuarioConsulta = obtener(usuario);
            if (usuarioConsulta.getUsuario().equals(usuario) && usuarioConsulta.getContrasenia().equals(contrasenia)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
