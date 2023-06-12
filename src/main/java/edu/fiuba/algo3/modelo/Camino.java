package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.SpawnNoEnLargadaError;

public class Camino {
    private List<Parcela> parcelas;

    public Camino(List<Parcela> parcelas)
    {
        this.parcelas = parcelas;
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
        while (!parcelas.get(iterador).compararPosicion(posicion) && iterador < parcelas.size() - 1)
        {
            iterador++;
        }
        
        int indiceDestino = iterador + velocidad;
        if (indiceDestino > parcelas.size() - 1) { indiceDestino = parcelas.size() - 1; }

        Parcela pasarelaFinal = parcelas.get(indiceDestino);

        pasarelaFinal.agregarEnemigo(enemigo);
    }

    /** Itera desde la meta hacia la largada para evitar perder informacion
     * @param velocidad
     * @param posicion
     * @param enemigo
     */
    public void moverEnemigos()
    {
        int iterador = parcelas.size() - 1;
        while (iterador >= 0)
        {
            parcelas.get(iterador).avanzarTurno();
            iterador--;
        }
    }

    public void aparecerEnemigos(List<Enemigo> enemigos)
    {
        try
        {
            (parcelas.get(0)).aparecerEnemigos(enemigos);
        }
        catch (SpawnNoEnLargadaError e)
        {
            //Log
        }
    }

}
