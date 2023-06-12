package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Errores.DefensaEnTerrenoErroneoError;

public class Tierra extends Parcela 
{
    private Torre torre;
    private Construible construible;
    
    public Tierra(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
        this.construible = new EsConstruible();
    }

    @Override
    public void construir(Defensa defensa)
    {
        if (defensa instanceof Torre)
        {
            this.torre = (Torre)construible.construir(defensa, this.posicion);
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
