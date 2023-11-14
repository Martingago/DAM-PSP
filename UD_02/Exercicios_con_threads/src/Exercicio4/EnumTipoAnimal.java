package Exercicio4;

public enum EnumTipoAnimal {
    
    /**
     * Clase ENUM con los tipos de animales existentes.
     * Cada uno de éstos tipos ejecutará su función específica (para evitar tener que crear un thread específico para cada animal)
     * De ésta forma lo que consigo es crear un único thread, y pasarle como parámetro un ENUM
     */
    
    TORTUGA {
        @Override
        public int movimiento() {
            MovimientoAnimales mov = new MovimientoAnimales();
            return mov.movimientoTortuga();
        }
    },
    
    LIEBRE{
        @Override
        public int movimiento(){
            MovimientoAnimales mov = new MovimientoAnimales();
            return mov.movimientoLiebre();
        }
    };
    
    /**
     * Funcion abstracta que se sobreescribirá con la función del tipo de animal que se ponga en la llamada
     * @return int con el valor del movimiento del animal
     */
    public abstract int movimiento();

}
