package edu.fiuba.algo3.modelo.Torres;

public class EstadoDesactivado implements Estado {

    @Override
    public void avanzarTurno(Torre torre){
        if(torre.progresarConstruccion()){
            torre.cambiarEstado();
        };
    }
    
}
