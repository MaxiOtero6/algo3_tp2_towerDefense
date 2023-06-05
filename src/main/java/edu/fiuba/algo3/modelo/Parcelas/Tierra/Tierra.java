package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

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

    public void agregarEnemigo(Enemigo enemigo) throws Exception
    {
        throw new Exception("No se puede agregar un enemigo en esta parcela!");
    }

    public Posicion obtenerPosicion()
    {
        return this.posicion;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o.getClass() == this.getClass())
        {
            Tierra tierra = (Tierra)o;
            return (this.posicion.equals(tierra.obtenerPosicion()));
        }
        return false;
    }
}
