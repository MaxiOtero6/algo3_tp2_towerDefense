package edu.fiuba.algo3.modelo.Parser;

import edu.fiuba.algo3.modelo.Mapa;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Tierra;
import edu.fiuba.algo3.modelo.Posicion;

public class CreadorMapa {
    
    private final static JsonNode ROOT = ParserJSON.leerJSON(
                                "src/main/resources/json/mapa.json"
                                        );

    public static List<List<Parcela>> crearMapa(List<Pasarela> camino)
    {
        List<List<Parcela>> parcelas = new LinkedList<>();
        for (int i = 1; i - 1 < 15; i++) {
            parcelas.add(new LinkedList<Parcela>());
        }

        String indice;
        String parcela;
        int j;
        try
        {
            for (int i = 1; i - 1 < 15; i++) 
            {
                indice = String.format("%d",i);
                j = 0;
                
                for (JsonNode parcelaJson : ROOT.get("Mapa").get(String.format(indice))) 
                {
                    parcela = parcelaJson.toString();
                    parcela = parcela.substring(1, parcela.length() - 1);
                    
                    if ("Tierra".equals(parcela))
                    {
                        parcelas.get(i - 1).add(new Tierra(new Posicion(j, i - 1)));
                    }
                    else if ("Rocoso".equals(parcela))
                    {
                        parcelas.get(i - 1).add(new Rocoso(new Posicion(j, i - 1)));
                    }
                    else if ("Pasarela".equals(parcela))
                    {
                        parcelas.get(i - 1).add(new Pasarela(new Posicion(j, i - 1)));
                        if (camino != null) {camino.add(new Pasarela(new Posicion(j, i - 1)));}
                    }
                    
                    j++;
                }
            }
            if (camino != null)
            {   
                camino.set(0, new Largada(camino.get(0).getPosicion()));
                camino.set(camino.size() - 1, new Meta(camino.get(camino.size() - 1).getPosicion()));
            }
                return (parcelas);
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }
}
