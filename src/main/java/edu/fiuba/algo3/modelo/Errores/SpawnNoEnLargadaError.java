package edu.fiuba.algo3.modelo.Errores;

public class SpawnNoEnLargadaError extends RuntimeException
{
    public SpawnNoEnLargadaError(){
        super("No se puede aparecer enemigos en esta pasarela");
    }
}
