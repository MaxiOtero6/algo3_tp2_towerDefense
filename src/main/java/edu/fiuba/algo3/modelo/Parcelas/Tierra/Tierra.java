package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.AgregarEnemigoError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public class Tierra implements Parcela 
{
    private Torre torre;
    private Construible construible;
    private Posicion posicion;
    
    public Tierra(int coordenadaX, int coordenadaY)
    {
        this.posicion = new Posicion(coordenadaX, coordenadaY);
        this.construible = new EsConstruible();
    }

    @Override
    public void construir(Torre torre) throws TerrenoDeConstruccionInvalidoError
    {
        this.torre = construible.construir(torre, this.posicion);
        this.setConstruible(new NoEsConstruible());
    }

    public void setConstruible(Construible construible)
    {
        this.construible = construible;
    }

    @Override
    public void agregarEnemigo(Enemigo enemigo) throws AgregarEnemigoError
    {
        throw new AgregarEnemigoError();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Tierra)
        {
            Tierra tierra = (Tierra)o;
            return (this.posicion.equals(tierra.posicion));
        }
        return false;
    }
}
