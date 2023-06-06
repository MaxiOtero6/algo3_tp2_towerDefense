package edu.fiuba.algo3.modelo.Errores;

public class CreditosInsuficientesError extends RuntimeException{
    public CreditosInsuficientesError(){
        super("El jugador no tiene los creditos necesarios para construir");
    }
}
