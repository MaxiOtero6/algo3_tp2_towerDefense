package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class Mapa {
    private List<List<Parcela>> mapa;

    public Mapa(List<List<Parcela>> parcelas)
    {
        this.mapa = parcelas;
    }

    public List<List<Parcela>> obtenerMapa()
    {
        return this.mapa;
    }

    public void construir(Defensa defensa, Posicion posicion)
    {
        try
        {
            mapa.get(posicion.obtenerY()).get(posicion.obtenerY()).construir(defensa);
        }
        catch (TerrenoDeConstruccionInvalidoError e)
        {
            //Log
        }
    }


    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Mapa)
        {
            Mapa mapa = (Mapa)o;
            return (this.mapa.equals(mapa.obtenerMapa()));
        }
        return false;
    }
}
