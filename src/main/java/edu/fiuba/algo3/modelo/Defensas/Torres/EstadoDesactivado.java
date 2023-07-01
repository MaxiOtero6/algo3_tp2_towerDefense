package edu.fiuba.algo3.modelo.Defensas.Torres;

public class EstadoDesactivado implements Estado {

    @Override
    public void avanzarTurno(TorreBlanca torre){
        torre.progresarConstruccion();
    }
    
}
