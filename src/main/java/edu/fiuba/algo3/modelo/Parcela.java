package edu.fiuba.algo3.modelo;

public abstract class Parcela {
    private Construible construible;

    public abstract void avanzarTurno();

    public void construir()
    {
        construible.construir();
    }

    public void setConstruible(Construible construible)
    {
        this.construible = construible;
    }
}
