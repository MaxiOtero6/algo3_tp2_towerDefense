package edu.fiuba.algo3.modelo.Parser;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import edu.fiuba.algo3.modelo.Parcelas.*;

public class CreadorMapa {
    
    private final static JsonNode ROOT = ParserJSON.leerJSON(
                                "src/main/resources/json/mapa.json"
                                        );

    private static int coordenadaXLargada;
    private static int coordenadaYLargada;
    private static int coordenadaXMeta;
    private static int coordenadaYMeta;

    public static List<List<Parcela>> crearMapa(List<Parcela> camino)
    {
        List<List<Parcela>> parcelas = new LinkedList<>();
        for (int i = 1; i - 1 < 15; i++) {
            parcelas.add(new LinkedList<Parcela>());
        }

        String indice;
        String parcela;
        int j;
        boolean posLargadaAsignada = false;

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
                        parcelas.get(i - 1).add(new Tierra(j, i - 1));
                    }

                    else if ("Rocoso".equals(parcela))
                    {
                        parcelas.get(i - 1).add(new Rocoso(j, i - 1));
                    }

                    else if ("Pasarela".equals(parcela))
                    {
                        Pasarela nuevPasarela = new Pasarela(j, i - 1);
                        parcelas.get(i - 1).add(nuevPasarela);
                        if (camino != null) {camino.add(nuevPasarela);}

                        if (!posLargadaAsignada) 
                        {
                            coordenadaXLargada = j;
                            coordenadaYLargada = i - 1;
                            posLargadaAsignada = true;
                        }

                        coordenadaXMeta = j;
                        coordenadaYMeta = i - 1;
                    }

                    j++;
                }
            }
            Largada largada = new Largada(coordenadaXLargada, coordenadaYLargada);
            Meta meta = new Meta(coordenadaXMeta, coordenadaYMeta);

            if (camino != null)
            {   
                camino.set(0, largada);
                camino.set(camino.size() - 1, meta);
            }

            parcelas.get(coordenadaYLargada).set(coordenadaXLargada, largada);
            parcelas.get(coordenadaYMeta).set(coordenadaXMeta, meta);

            CreadorCaminoL.setMapa(parcelas);
            CreadorCaminoL.setXLargada(coordenadaXLargada);
            CreadorCaminoL.setYLargada(coordenadaYLargada);
            CreadorCaminoL.setXMeta(coordenadaXMeta);
            CreadorCaminoL.setYMeta(coordenadaYMeta);

            CreadorCaminoH.setMapa(parcelas);
            CreadorCaminoH.setXMeta(coordenadaXMeta);
            CreadorCaminoH.setYMeta(coordenadaYMeta);

            return (parcelas);
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }
}
