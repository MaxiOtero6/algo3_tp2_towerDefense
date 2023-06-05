package edu.fiuba.algo3.modelo.Parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.fiuba.algo3.modelo.Enemigos.Arania;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;

public class CreadorEnemigos {

    private final static JsonNode ROOT = ParserJSON.leerJSON(
                                "src/main/resources/json/enemigos.json"
                                        );
    
    public static List<Enemigo> crearEnemigos(int numeroTurno)
    {
        List<Enemigo> enemigosTurno = new LinkedList<>();

        int cantidadHormigas;
        int cantidadAranias;

        try
        {
            for (JsonNode turno : ROOT)
            {
                if ((turno.get("turno").asInt() - 1) == numeroTurno)
                {    
                    cantidadHormigas = turno.get("enemigos").get("hormiga").asInt();
                    cantidadAranias = turno.get("enemigos").get("arana").asInt();
                    
                    for (int i = 0; i < cantidadHormigas; i++) {
                            enemigosTurno.add(new Hormiga());
                        }
                        
                    for (int i = 0; i < cantidadAranias; i++) {
                            enemigosTurno.add(new Arania());
                        }
                }
            }

            return enemigosTurno;
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }
}
