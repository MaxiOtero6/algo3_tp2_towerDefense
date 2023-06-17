package edu.fiuba.algo3.modelo.Errores;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class GanarPartidaError extends RuntimeException{

    public GanarPartidaError(){
        super("Ganaste");
    }

    public static void comprobarGanarJuego(LinkedList<Enemigo> enemigos)
    {
        if (enemigos.size() == 0){
            throw new GanarPartidaError();
        }
    }
}
