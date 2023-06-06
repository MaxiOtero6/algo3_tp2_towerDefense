package edu.fiuba.algo3.modelo.Errores;

public class PerderPartidaError extends RuntimeException {
    public PerderPartidaError(){
        super("Perdiste");
    }
}
