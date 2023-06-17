package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Lechuza;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;

public class Turno {

    private LinkedList<Defensa> defensas;
    private LinkedList<Enemigo> enemigos;
    private Camino camino;
    private Jugador jugador;


    public Turno(List<Parcela> camino, Jugador jugador)
    {
        this.jugador = jugador;
        this.camino = new Camino(camino);
        this.enemigos = new LinkedList<>();
        this.defensas = new LinkedList<>();
    }

    public Turno(Camino camino, Jugador jugador)
    {
        this.jugador = jugador;
        this.camino = camino;
        this.enemigos = new LinkedList<>();
        this.defensas = new LinkedList<>();
    }

    public Turno(Camino camino, Jugador jugador, LinkedList<Defensa> defensas)
    {
        this.jugador = jugador;
        this.camino = camino;
        this.enemigos = new LinkedList<>();
        this.defensas = defensas;
    }

    public void avanzarTurno(int numeroTurno) {
        avanzarEnemigos();
        if (numeroTurno >= 0)
        {
            List<Enemigo> enemigosTurno = CreadorEnemigos.crearEnemigos(numeroTurno, this.jugador, this.camino);
            for (Enemigo enemigo : enemigosTurno) 
            {
                this.enemigos.add(enemigo);
                //if (enemigo instanceof Lechuza) {((Lechuza)enemigo).setTorres(this.defensas);}
            }
            this.camino.aparecerEnemigos(enemigosTurno);
        }
        avanzarDefensas();
        comprobarCantidadEnemigos();
    }

    private void avanzarDefensas()
    {
        for (Defensa defensa : defensas) {
            defensa.avanzarTurno();
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
            return (this.enemigos.equals(turno.enemigos) && this.defensas.equals(turno.defensas));
        }
        return false;
    }
}
