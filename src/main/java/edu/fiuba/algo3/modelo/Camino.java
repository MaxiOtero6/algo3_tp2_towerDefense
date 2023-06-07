package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.SpawnNoEnLargadaError;

public class Camino {
    private LinkedList<Pasarela> pasarelas;
    private static Camino camino;

    private Camino()
    {
        this.pasarelas = new LinkedList<Pasarela>();
    }

    public void agregarPasarelas(List<Pasarela> pasarelas)
    {
        this.pasarelas = new LinkedList<>(pasarelas);
    }

    
    /* Busca la pasarela que requiere, al indice de la misma se suma la velocidad del
     * enemigo y mueve al enemigo a tal pasarela.
     * @param velocidad
     * @param posicion
     * @param enemigo
     */
    public void moverEnemigo(int velocidad, Posicion posicion, Enemigo enemigo)
    {
        int iterador = 0;
        while (!pasarelas.get(iterador).compararPosicion(posicion) && iterador < pasarelas.size() - 1)
        {
            iterador++;
        }
        
        int indiceDestino = iterador + velocidad;
        if (indiceDestino < 0) { indiceDestino = 0; }

        Pasarela pasarelaFinal = pasarelas.get(indiceDestino);

        pasarelaFinal.agregarEnemigo(enemigo);
    }

    /** Itera desde la meta hacia la largada para evitar perder informacion
     * @param velocidad
     * @param posicion
     * @param enemigo
     */
    public void moverEnemigos()
    {
        int iterador = pasarelas.size() - 1;
        while (iterador >= 0)
        {
            pasarelas.get(iterador).avanzarTurno();
            iterador--;
        }
    }

    public static Camino obtenerCamino()
    {
        if (camino == null)
        {
            camino = new Camino();
        }
        return camino;
    }

    public void borrarPasarelas(){
        this.pasarelas = new LinkedList<Pasarela>();
    }

    public void aparecerEnemigos(List<Enemigo> enemigos)
    {
        try
        {
            (pasarelas.get(0)).aparecerEnemigos(enemigos);
        }
        catch (SpawnNoEnLargadaError e)
        {
            //Log
        }
    }

}
