package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Parcelas.Construible.Construible;
import edu.fiuba.algo3.modelo.Parcelas.Construible.EsConstruible;
import edu.fiuba.algo3.modelo.Parcelas.Construible.NoEsConstruible;
import edu.fiuba.algo3.modelo.Errores.DefensaEnTerrenoErroneoError;

public class Tierra extends Parcela 
{
    private TorreBlanca torre;
    private Construible construible;
    
    public Tierra(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
        this.torre = new NoTorre();
        this.construible = new EsConstruible();
    }

    @Override
    public void construir(Defensa defensa)
    {
        DefensaEnTerrenoErroneoError.comprobarTierra(this, defensa);
        this.torre = (TorreBlanca)construible.construir(defensa, this.posicion);
        this.setConstruible(new NoEsConstruible());

    }

    public void setConstruible(Construible construible)
    {
        this.construible = construible;
    }
}
