package edu.fiuba.algo3.modelo.Errores;

public class AgregarEnemigoError extends RuntimeException{
    public AgregarEnemigoError(){
        super("El jugador no tiene los creditos necesarios para construir");
    }
    
}
