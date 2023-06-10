package edu.fiuba.algo3.modelo.Parser;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class CreadorEnemigos {

    private final static JsonNode ROOT = ParserJSON.leerJSON(
                                "src/main/resources/json/enemigos.json"
                                        );
    
    /** 11 >= numeroTurno >= 0 */
    public static List<Enemigo> crearEnemigos(int numeroTurno, Jugador jugador, Camino camino)
    {
        List<Enemigo> enemigosTurno = new LinkedList<>();

        int cantidadHormigas, cantidadAranias, cantidadLechuzas, cantidadTopos;

        try
        {
            for (JsonNode turno : ROOT)
            {
                if ((turno.get("turno").asInt() - 1) == numeroTurno)
                {    
                    cantidadHormigas = turno.get("enemigos").get("hormiga").asInt();
                    cantidadAranias = turno.get("enemigos").get("arana").asInt();
                    cantidadTopos = turno.get("enemigos").get("topo").asInt();
                    cantidadLechuzas = turno.get("enemigos").get("lechuza").asInt();

                    for (int i = 0; i < cantidadHormigas; i++) 
                    {
                        enemigosTurno.add(new Hormiga(jugador));
                    }
                        
                    for (int i = 0; i < cantidadAranias; i++) 
                    {
                        enemigosTurno.add(new Arania(jugador));
                    }
                    
                    for (int i = 0; i < cantidadTopos; i++) 
                    {
                        enemigosTurno.add(new Topo(jugador));
                    }
                    
                    for (int i = 0; i < cantidadLechuzas; i++) 
                    {
                        enemigosTurno.add(new Lechuza(jugador));
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
