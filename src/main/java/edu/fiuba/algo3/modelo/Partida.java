package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;

public class Partida {

    private Turno turno;
    private Mapa mapa;



    public Partida(List<List<Parcela>> parcelas, List<Pasarela> camino) {
        this.mapa = new Mapa(parcelas);
        this.turno = new Turno(camino);
    }

    public void iniciar()
    {
        int i = 0;
        int j = 0;
        while (j < 500)
        {
            turno.avanzarTurno(i);
            i++;
            if (i == 12) {i = 0;}
            j++;
        }
    }

    public void iniciarJuego()
    {
        try
        {
            iniciar();
        }
        catch (GanarPartidaError g)
        {

        }
        catch (PerderPartidaError p)
        {

        }
    }

    public void construir(Defensa defensa, Posicion posicion)
    {
        this.mapa.construir(defensa, posicion);
        this.turno.aniadirDefensa(defensa);
    }


    public Mapa obtenerMapa()
    {
        return this.mapa;
    }

    public Turno obtenerTurno()
    {
        return this.turno;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Partida)
        {
            Partida partida = (Partida)o;
            return (this.mapa.equals(partida.obtenerMapa()) && this.turno.equals(partida.obtenerTurno()));
        }
        return false;
    }

}
