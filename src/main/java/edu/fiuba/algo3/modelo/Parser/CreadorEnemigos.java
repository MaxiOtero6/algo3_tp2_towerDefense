package edu.fiuba.algo3.modelo.Parser;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import edu.fiuba.algo3.modelo.Enemigos.Arania;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;

public class CreadorEnemigos {
    
    public static List<List<Enemigo>> crearEnemigos()
    {
        List<List<Enemigo>> enemigos = new ArrayList<>();
        List<Enemigo> enemigosTurno;

        int cantidadHormigas;
        int cantidadAranias;

        JsonNode root = ParserJSON.leerJSON(
            "src\\main\\resources\\json\\enemigos.json"
            );
    
        try
        {
            for (JsonNode turno : root)
            {
                enemigosTurno = new ArrayList<>();
                
                cantidadHormigas = turno.get("enemigos").get("hormiga").asInt();
                cantidadAranias = turno.get("enemigos").get("arana").asInt();
                
                for (int i = 0; i < cantidadHormigas; i++) {
                    enemigosTurno.add(new Hormiga());
                }
                
                for (int i = 0; i < cantidadAranias; i++) {
                    enemigosTurno.add(new Arania());
                }
                enemigos.add(enemigosTurno);
            }

            return enemigos;
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }
}
