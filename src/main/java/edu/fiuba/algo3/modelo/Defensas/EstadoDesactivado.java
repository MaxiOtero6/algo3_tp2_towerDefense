package edu.fiuba.algo3.modelo.Defensas;

public class EstadoDesactivado implements Estado {

    @Override
    public void avanzarTurno(Defensa defensa){
        if(defensa.progresarConstruccion()){
            defensa.cambiarEstado();
        };
    }
    
}
