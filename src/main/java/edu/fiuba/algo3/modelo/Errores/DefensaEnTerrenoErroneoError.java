package edu.fiuba.algo3.modelo.Errores;

public class DefensaEnTerrenoErroneoError extends RuntimeException{
    public DefensaEnTerrenoErroneoError(){
        super("No se puede construir este tipo de defensa en esta parcela!");
    }
}
