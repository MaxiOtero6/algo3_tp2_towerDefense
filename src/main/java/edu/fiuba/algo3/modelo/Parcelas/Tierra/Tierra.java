package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class Tierra implements Parcela 
{
    private Defensa defensa;
    private Construible construible;
    
    @Override
    public void avanzarTurno()
    {
        ; 
    }    

    @Override
    public void construir()
    {
        //Consultar user la defensa
        construible.construir(defensa, this);
    }

    public void colocarDefensa(Defensa defensa)
    {
        this.defensa = defensa;
    }

    public void setConstruible(Construible construible)
    {
        this.construible = construible;
    }

}
