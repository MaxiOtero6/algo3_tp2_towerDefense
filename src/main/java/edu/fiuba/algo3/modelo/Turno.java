package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;

public class Turno {

    private LinkedList<Torre> torres;
    private LinkedList<Enemigo> enemigos;
    private Camino camino;
    private Jugador jugador;


    public Turno(List<Parcela> camino, Jugador jugador)
    {
        this.jugador = jugador;
        this.camino = new Camino(camino);
        this.enemigos = new LinkedList<>();
        this.torres = new LinkedList<>();
    }

    public void avanzarTurno(int numeroTurno) {
        avanzarEnemigos();
        if (numeroTurno >= 0)
        {
            List<Enemigo> enemigosTurno = CreadorEnemigos.crearEnemigos(numeroTurno, this.jugador, this.camino);
            for (Enemigo enemigo : enemigosTurno) 
            {
                this.enemigos.add(enemigo);
            }
            this.camino.aparecerEnemigos(enemigosTurno);
        }

        for (Torre torre : torres) {
            torre.avanzarTurno();
        }

        comprobarCantidadEnemigos();
    }

    public void aniadirTorre(Torre torre)
    {
        torre.setEnemigos(enemigos);
        torres.add(torre);
    }

    private void avanzarEnemigos()
    {
        this.camino.moverEnemigos();
    }

    private void comprobarCantidadEnemigos(){
        sacarMuertos();
        if (enemigos.size() == 0){
            throw new GanarPartidaError();
        }
    }

    private void sacarMuertos()
    {
        int i = enemigos.size() - 1;
        while (i >= 0)
        {
            if (!this.enemigos.get(i).estaVivo()) {enemigos.remove(i);}
            i--;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Turno)
        {
            Turno turno = (Turno)o;
            return (this.enemigos.equals(turno.enemigos) && this.torres.equals(turno.torres));
        }
        return false;
    }

}
