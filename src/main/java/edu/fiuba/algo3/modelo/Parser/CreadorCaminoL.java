package edu.fiuba.algo3.modelo.Parser;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class CreadorCaminoL {
    private static int coordenadaXLargada;
    private static int coordenadaYLargada;
    private static int coordenadaXMeta;
    private static int coordenadaYMeta;
    private static List<List<Parcela>> mapa;

    public static void setMapa(List<List<Parcela>> _mapa_)
    {
        mapa = _mapa_;
    }

    public static void setXLargada(int xLargada)
    {
        coordenadaXLargada = xLargada;
    }

    public static void setYLargada(int yLargada)
    {
        coordenadaYLargada = yLargada;
    }

    public static void setXMeta(int xMeta)
    {
        coordenadaXMeta = xMeta;
    }

    public static void setYMeta(int yMeta)
    {
        coordenadaYMeta = yMeta;
    }

    public static List<Parcela> crearCaminoL()
    {
        int i = 0;
        int j = 0;
        List<Parcela> caminoL = new LinkedList<>();

        while ((coordenadaXLargada + i != coordenadaXMeta) && (coordenadaYLargada + j != coordenadaYMeta))
        {
            if (coordenadaXLargada < coordenadaXMeta) {i++;}
            else if (coordenadaXLargada > coordenadaXMeta) {i--;}
            else if (coordenadaYLargada < coordenadaYMeta) {j++;}
            else if (coordenadaYLargada > coordenadaYMeta) {j--;}

            caminoL.add(mapa.get(coordenadaYLargada + j).get(coordenadaXLargada + i));
        }
        return caminoL;
    }
}
