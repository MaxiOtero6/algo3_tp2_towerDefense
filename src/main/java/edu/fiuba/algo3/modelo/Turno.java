package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Lechuza;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;

public class Turno {

    private LinkedList<TorreBlanca> torres;
    private LinkedList<TrampaArenosa> trampas;
    private LinkedList<Enemigo> enemigos;
    private Camino camino;
    private Jugador jugador;


    public Turno(List<Parcela> camino, Jugador jugador)
    {
        this.jugador = jugador;
        this.camino = new Camino(camino);
        this.enemigos = new LinkedList<>();
        this.torres = new LinkedList<>();
        this.trampas = new LinkedList<>();
    }

    public Turno(Camino camino, Jugador jugador)
    {
        this.jugador = jugador;
        this.camino = camino;
        this.enemigos = new LinkedList<>();
        this.torres = new LinkedList<>();
        this.trampas = new LinkedList<>();
    }

    public Turno(Camino camino, Jugador jugador, LinkedList<TorreBlanca> torres, LinkedList<TrampaArenosa> trampas)
    {
        this.jugador = jugador;
        this.camino = camino;
        this.enemigos = new LinkedList<>();
        this.torres = torres;
        this.trampas = trampas;
    }

    public void avanzarTurno(int numeroTurno) {
        avanzarEnemigos();
        comprobarDefensas();
        if (numeroTurno >= 0)
        {
            List<Enemigo> enemigosTurno = CreadorEnemigos.crearEnemigos(numeroTurno, this.jugador, this.camino);
            for (Enemigo enemigo : enemigosTurno) 
            {
                this.enemigos.add(enemigo);
                if (enemigo instanceof Lechuza) {((Lechuza)enemigo).setTorres(this.torres);}
            }
            this.camino.aparecerEnemigos(enemigosTurno);
        }
        avanzarDefensas();
        comprobarCantidadEnemigos();
    }

    private void avanzarDefensas()
    {
        for (TorreBlanca torre : torres) {
            torre.avanzarTurno();
        }

        for (TrampaArenosa trampa : trampas) {
            trampa.avanzarTurno();
        }
    }

    public void aniadirTorre(TorreBlanca torre)
    {
        torre.setEnemigos(enemigos);
        torres.add(torre);
    }

    public void aniadirTrampa(TrampaArenosa trampa)
    {
        trampa.setEnemigos(enemigos);
        trampas.add(trampa);
    }

    private void avanzarEnemigos()
    {
        for (Enemigo enemigo : enemigos) {
            enemigo.mover();
        }
    }

    private void comprobarCantidadEnemigos(){
        sacarMuertos();
        if (enemigos.size() == 0){
            throw new GanarPartidaError();
        }
    }

    private void comprobarDefensas(){
        sacarDefensasDestruidas();
    }

    private void sacarDefensasDestruidas()
    {
        for (TorreBlanca torre : torres) {
            if (torre.estaDestruida()) {torres.remove(torre);}
        }

        for (TrampaArenosa trampa : trampas) {
            if (trampa.estaDestruida()) {trampas.remove(trampa);}
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
            return (this.enemigos.equals(turno.enemigos) && this.torres.equals(turno.torres) && this.trampas.equals(turno.trampas));
        }
        return false;
    }

}
