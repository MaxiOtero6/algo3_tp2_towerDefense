package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;

public class Turno {

    private LinkedList<Defensa> defensas;
    private LinkedList<Enemigo> enemigos;


    public Turno(List<Pasarela> camino){
        Camino.obtenerCamino().agregarPasarelas(camino);
        this.enemigos = new LinkedList<>();
        this.defensas = new LinkedList<>();
    }

    public void avanzarTurno(int numeroTurno) {
        avanzarEnemigos();
        if (numeroTurno >= 0)
        {
            List<Enemigo> enemigosTurno = CreadorEnemigos.crearEnemigos(numeroTurno);
            for (Enemigo enemigo : enemigosTurno) 
            {
                this.enemigos.add(enemigo);
            }
            Camino.obtenerCamino().aparecerEnemigos(enemigosTurno);
        }

        for (Defensa defensa : defensas) {
            defensa.avanzarTurno();
        }

        comprobarCantidadEnemigos();
    }

    public void aniadirDefensa(Defensa defensa)
    {
        defensa.setEnemigos(enemigos);
        defensas.add(defensa);
    }

    private void avanzarEnemigos()
    {
        Camino.obtenerCamino().moverEnemigos();
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
