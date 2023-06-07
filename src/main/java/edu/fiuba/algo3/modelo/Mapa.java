package edu.fiuba.algo3.modelo;

import java.util.List;

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
