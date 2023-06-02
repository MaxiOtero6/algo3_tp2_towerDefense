package edu.fiuba.algo3.modelo.Parser;

import edu.fiuba.algo3.modelo.Mapa;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Tierra;
import edu.fiuba.algo3.modelo.Posicion;

public class CreadorMapa {
    
    public static Mapa crearMapa()
    {
        List<List<Parcela>> parcelas = new ArrayList<>();
        for (int i = 1; i - 1 < 15; i++) {
            parcelas.add(new ArrayList<Parcela>());
        }

        JsonNode root = ParserJSON.leerJSON(
            "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\Parser\\mapa.json"
            );

        String indice;
        String parcela;
        int j;
        try
        {
            for (int i = 1; i - 1 < 15; i++) 
            {
                indice = String.format("%d",i);
                j = 0;
                
                for (JsonNode parcelaJson : root.get("Mapa").get(String.format(indice))) 
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
                    }
                    
                    j++;
                }
            }
            return (new Mapa(parcelas));
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }
}
