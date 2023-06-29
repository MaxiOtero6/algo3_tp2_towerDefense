package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

class ContenedorTorre {
    private Defensa defensa;
    public boolean puseDefensa = false;

    public Defensa getTorre() {
        return defensa;
    }

    public void setTorre(Defensa defensa) {
        this.defensa = defensa;
        puseDefensa = false;
    }

    public void ponerTorre() {
        puseDefensa = true;
    }

    public boolean puseTorre() {
        return puseDefensa;
    }
}