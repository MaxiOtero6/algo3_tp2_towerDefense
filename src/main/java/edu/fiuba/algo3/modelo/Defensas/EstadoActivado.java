package edu.fiuba.algo3.modelo.Defensas;

public class EstadoActivado implements Estado {
    private final Defensa defensa;
    public EstadoActivado(Defensa defensa){
        this.defensa = defensa;
    }
    public void avanzarTurno(){
        atacar();
    }
    private void atacar(){
        //atacar
    }
}