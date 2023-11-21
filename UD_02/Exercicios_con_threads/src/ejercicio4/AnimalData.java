package ejercicio4;

public class AnimalData {

    private int posicionAnimal;
    private final String nombreAnimal;
    private final char iconoNombre;

    /**
     * Objeto que contiene la información de cada animal para poder gestionar puntuación y nombres por pantalla
     * @param nombreAnimal
     * @param icono 
     */
    public AnimalData(String nombreAnimal, char icono) {
        this.nombreAnimal = nombreAnimal;
        this.posicionAnimal = 0;
        this.iconoNombre = icono;
    }

    public int getPosicionAnimal() {
        return posicionAnimal;
    }

    public void setPosicionAnimal(int posicionAnimal) {
        this.posicionAnimal = posicionAnimal;
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }

    public char getIconoNombre() {
        return iconoNombre;
    }
    
}
