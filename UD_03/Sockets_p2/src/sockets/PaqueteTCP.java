package sockets;

import java.io.Serializable;

public class PaqueteTCP implements Serializable {
    int puerto_origen;
    int puerto_destino;
    String mensaje;

    public PaqueteTCP(int puerto_origen, int puerto_destino, String mensaje) {
        this.puerto_origen = puerto_origen;
        this.puerto_destino = puerto_destino;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaqueteTCP{");
        sb.append("puerto_origen=").append(puerto_origen);
        sb.append(", puerto_destino=").append(puerto_destino);
        sb.append(", mensaje=").append(mensaje);
        sb.append('}');
        return sb.toString();
    }
    
}
