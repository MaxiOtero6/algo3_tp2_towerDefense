package edu.fiuba.algo3.modelo.Defensas.Trampas;

import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.ObjetivoTrampa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class TrampaArenosa extends Defensa
{
    private ObjetivoTrampa objetivo;
    private int turnosUtiles;


    public TrampaArenosa() 
    {
        super(25);
        this.objetivo = new ObjetivoTrampa();
        this.turnosUtiles = 3;

    }

    @Override
    public void avanzarTurno() 
    {
    
    }

    @Override
    public void atacar() 
    {
        List<Enemigo> enemigoObjetivos = this.objetivo.hallarObjetivo(posicion, enemigos);
        for (Enemigo enemigo : enemigoObjetivos) 
        {
            enemigo.ralentizar();
        }
    }    



}


