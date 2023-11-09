package actividad;
import actividad.proceso.MiProceso;

public class main {

    public static void main(String[] args) {
        MiProceso p1 = new MiProceso(10, 20, 1500);
        MiProceso p2 = new MiProceso(10,20,300);
        p1.start();
        p2.start();

    }
}
