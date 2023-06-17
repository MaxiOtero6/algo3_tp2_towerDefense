package edu.fiuba.algo3.modelo.Errores;

public class PerderPartidaError extends RuntimeException {
    public PerderPartidaError(){
        super("Perdiste");
    }

    public static void comprobarPerderJuego(int vida)
    {
        if (vida <= 0){
            throw new PerderPartidaError();
        }
    }
}
