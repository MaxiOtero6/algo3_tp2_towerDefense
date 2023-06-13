package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import edu.fiuba.algo3.modelo.Errores.DefensaEnTerrenoErroneoError;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Construible;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.EsConstruible;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.NoEsConstruible;

public class Pasarela extends Parcela {

    private TrampaArenosa trampa;
    private Construible construible;

    public Pasarela(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
        this.construible = new EsConstruible();
    }
    
    @Override
    public void construir(Defensa defensa)
    {
        if (defensa instanceof TrampaArenosa)
        {
            this.trampa = (TrampaArenosa)construible.construir(defensa, this.posicion);
            this.setConstruible(new NoEsConstruible());
        }
        else
        {
            throw new DefensaEnTerrenoErroneoError();
        }
    }

    public void setConstruible(Construible construible)
    {
        this.construible = construible;
    }
}
