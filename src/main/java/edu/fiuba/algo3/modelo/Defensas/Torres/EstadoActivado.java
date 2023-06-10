package edu.fiuba.algo3.modelo.Defensas.Torres;

public class EstadoActivado implements Estado {
    @Override
    public void avanzarTurno(Torre torre){
        torre.atacar();
    }
}