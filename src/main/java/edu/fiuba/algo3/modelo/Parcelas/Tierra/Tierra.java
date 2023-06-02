package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class Tierra implements Parcela 
{
    private Defensa defensa;
    private Construible construible;
    private Posicion posicion;
    
    public Tierra(Posicion posicion)
    {
        this.posicion = posicion;
        this.construible = new EsConstruible();
    }

    @Override
    public void avanzarTurno()
    {
        ; 
    }    

    @Override
    public void construir(Defensa defensa) throws Exception
    {
        this.colocarDefensa(construible.construir(defensa));
    }

    public void colocarDefensa(Defensa defensa)
    {
        this.defensa = defensa;
        this.setConstruible(new NoEsConstruible());
    }

    public void setConstruible(Construible construible)
    {
        this.construible = construible;
    }

}
