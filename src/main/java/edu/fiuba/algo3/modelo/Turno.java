package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;

public class Turno {

    private LinkedList<Defensa> defensas;
    private LinkedList<Enemigo> enemigos;
    private Camino camino;
    private Jugador jugador;
    private int numeroTurno;


    public Turno(List<Parcela> camino, Jugador jugador)
    {
        this.jugador = jugador;
        this.camino = new Camino(camino);
        this.enemigos = new LinkedList<>();
        this.defensas = new LinkedList<>();
        this.numeroTurno = 0;
    }

    public Turno(Camino camino, Jugador jugador)
    {
        this.jugador = jugador;
        this.camino = camino;
        this.enemigos = new LinkedList<>();
        this.defensas = new LinkedList<>();
        this.numeroTurno = 0;
    }

    public Turno(Camino camino, Jugador jugador, LinkedList<Defensa> defensas)
    {
        this.jugador = jugador;
        this.camino = camino;
        this.enemigos = new LinkedList<>();
        this.defensas = defensas;
        this.numeroTurno = 0;
    }

    public void avanzarTurno(int numeroTurno)
    {
        avanzarEnemigos();
        crearOleada(numeroTurno);
        avanzarDefensas();
        comprobarCantidadEnemigos();
    }

    public void avanzarTurno() { 
        avanzarTurno(this.numeroTurno);
        incrementarTurno();
    }

    private void incrementarTurno()
    {
        this.numeroTurno++;
        if (this.numeroTurno == 12) {this.numeroTurno = 0;}
    }

    private void crearOleada(int numeroTurno)
    {
        List<Enemigo> enemigosTurno = CreadorEnemigos.crearEnemigos(numeroTurno, this.jugador, this.camino);
        for (Enemigo enemigo : enemigosTurno) 
        {
            this.enemigos.add(enemigo);
            enemigo.setDefensas(defensas);
        }
        this.camino.aparecerEnemigos(enemigosTurno);
    }

    private void avanzarDefensas()
    {
        int i = defensas.size() - 1;
        while (i >= 0)
        {
            defensas.get(i).avanzarTurno(defensas);
            i--;
        }
    }

    public void aniadirDefensa(Defensa defensa)
    {
        defensa.gastarCreditos(this.jugador);
        defensa.setEnemigos(enemigos);
        defensas.add(defensa);
    }

    private void avanzarEnemigos()
    {
        int i = enemigos.size() - 1;
        while (i >= 0)
        {
            enemigos.get(i).mover();
            i--;
        }
    }

    private void comprobarCantidadEnemigos(){
        int i = enemigos.size() - 1;
        while (i >= 0)
        {
            enemigos.get(i).comprobarSalud(enemigos);
            i--;
        }
        GanarPartidaError.comprobarGanarJuego(enemigos);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Turno)
        {
            Turno turno = (Turno)o;
            return (this.enemigos.equals(turno.enemigos) && this.defensas.equals(turno.defensas));
        }
        return false;
    }

    public LinkedList<Enemigo> obtenerEnemigos()
    {
        return this.enemigos;
    }

    public Jugador obtenerJugador() {
        return this.jugador;
    }
}