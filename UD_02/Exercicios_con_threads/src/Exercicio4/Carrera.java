package Exercicio4;

public class Carrera {

    private static int posicionTortuga = 0;
    private static int posicionLiebre = 0;

    public Carrera() {
        ThreadAnimales tortuga = new ThreadAnimales(EnumTipoAnimal.TORTUGA, posicionTortuga);
        ThreadAnimales liebre = new ThreadAnimales(EnumTipoAnimal.LIEBRE, posicionLiebre);

        Thread threadTortuga = new Thread(tortuga);
        Thread threadLiebre = new Thread(liebre);

        threadTortuga.start();
        threadLiebre.start();
    }
    
}
