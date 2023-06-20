package edu.fiuba.algo3.modelo.Parser;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class CreadorCaminoH {

    private static int coordenadaXMeta;
    private static int coordenadaYMeta;
    private static List<List<Parcela>> mapa;

    public static void setMapa(List<List<Parcela>> _mapa_)
    {
        mapa = _mapa_;
    }

    public static void setXMeta(int xMeta)
    {
        coordenadaXMeta = xMeta;
    }

    public static void setYMeta(int yMeta)
    {
        coordenadaYMeta = yMeta;
    }

    public static List<Parcela> crearCaminoH(Posicion posicion)
    {
        int i = 0;
        int j = 0;
        List<Parcela> caminoH = new LinkedList<>();
        int coordenadaXInicial = coordenadaXMeta - posicion.diferenciaEnX(coordenadaXMeta);
        int coordenadaYInicial = coordenadaYMeta - posicion.diferenciaEnY(coordenadaYMeta);

        caminoH.add(mapa.get(coordenadaYInicial + j).get(coordenadaXInicial + i));

        while ((coordenadaXInicial + i != coordenadaXMeta) || (coordenadaYInicial + j != coordenadaYMeta))
        {
            if (coordenadaXInicial + i < coordenadaXMeta) {i++;}
            else if (coordenadaXInicial + i > coordenadaXMeta) {i--;}
            if (coordenadaYInicial + j < coordenadaYMeta) {j++;}
            else if (coordenadaYInicial + j > coordenadaYMeta) {j--;}

            caminoH.add(mapa.get(coordenadaYInicial + j).get(coordenadaXInicial + i));
        }
        return caminoH;
    }
}
