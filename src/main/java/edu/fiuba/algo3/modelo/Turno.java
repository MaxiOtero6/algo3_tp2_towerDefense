package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
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
        List<Enemigo> enemigos = CreadorEnemigos.crearEnemigos(numeroTurno);
        for (Enemigo enemigo : enemigos) 
        {
            enemigos.add(enemigo);
        }
        Camino.obtenerCamino().aparecerEnemigos(enemigos);

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
        if (enemigos.size() == 0){
            throw new GanarPartidaError();
        }
    }

    public List<Enemigo> obtenerEnemigos()
    {
        return enemigos;
    }

    public List<Defensa> obtenerDefensas()
    {
        return defensas;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Turno)
        {
            Turno turno = (Turno)o;
            return (this.enemigos.equals(turno.obtenerEnemigos()) && this.defensas.equals(turno.obtenerDefensas()));
        }
        return false;
    }

}
