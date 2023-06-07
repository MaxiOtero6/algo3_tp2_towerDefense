package edu.fiuba.algo3.modelo.Defensas;

public class EstadoActivado implements Estado {
    @Override
    public void avanzarTurno(Defensa defensa){
        defensa.atacar();
    }
}