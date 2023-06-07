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
    }

    public void aniadirDefensa(Defensa defensa)
    {
        defensa.setEnemigos(enemigos);
        defensas.add(defensa);
    }

    private void avanzarEnemigos(){
        if (!(Camino.obtenerCamino().tieneEnemigos())){
            throw new GanarPartidaError();
        }
    }

}
