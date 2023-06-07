package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class Mapa {
    private List<List<Parcela>> mapa;

    public Mapa(List<List<Parcela>> parcelas)
    {
        this.mapa = parcelas;
    }
}
