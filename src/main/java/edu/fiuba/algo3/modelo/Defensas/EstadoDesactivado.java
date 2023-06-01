package edu.fiuba.algo3.modelo.Defensas;

public class EstadoDesactivado implements Estado {
    private final Defensa defensa;
    public EstadoDesactivado(Defensa defensa){
        this.defensa = defensa;
    }
    public void avanzarTurno(){
        construir();
    }
    private void construir(){
       if(defensa.progresarConstruccion()){
            defensa.cambiarEstado();
       };
    }
    
}
