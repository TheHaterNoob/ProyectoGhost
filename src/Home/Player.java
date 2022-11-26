package Home;

public class Player {

    private String usuario;
    private String contrasenia;
    private int puntos;

    public Player(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        puntos=0;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public int getPuntos() {
            return puntos;
	}
    public void setPuntos(int puntos) {
            this.puntos=puntos;
	}

}
