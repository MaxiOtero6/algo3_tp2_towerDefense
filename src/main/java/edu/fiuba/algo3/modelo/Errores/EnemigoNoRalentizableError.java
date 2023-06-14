package edu.fiuba.algo3.modelo.Errores;

public class EnemigoNoRalentizableError extends RuntimeException{
    public EnemigoNoRalentizableError(){
        super("No se puede ralentizar este tipo de enemigo!");
    }
}
