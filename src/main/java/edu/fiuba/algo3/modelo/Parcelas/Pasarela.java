package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Errores.DefensaEnTerrenoErroneoError;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Trampas.NoTrampa;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Parcelas.Construible.Construible;
import edu.fiuba.algo3.modelo.Parcelas.Construible.EsConstruible;
import edu.fiuba.algo3.modelo.Parcelas.Construible.NoEsConstruible;

public class Pasarela extends Parcela {

    private TrampaArenosa trampa;
    private Construible construible;

    public Pasarela(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
        this.construible = new EsConstruible();
        this.trampa = new NoTrampa();
    }
    
    @Override
    public void construir(Defensa defensa)
    {
        DefensaEnTerrenoErroneoError.comprobarPasarela(this, defensa);
        this.trampa = (TrampaArenosa)construible.construir(defensa, this.posicion);
        this.setConstruible(new NoEsConstruible());
    }

    public void setConstruible(Construible construible)
    {
        this.construible = construible;
    }

    @Override
    public void agregarEnemigo(Enemigo enemigo)
    {
        super.agregarEnemigo(enemigo);
        this.trampa.avanzarEntreTurno();
    }
}
