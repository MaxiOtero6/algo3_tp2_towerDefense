package edu.fiuba.algo3.modelo.Defensas.Trampas;

import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Objetivos.ObjetivoTrampa;
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
        this.atacar();
        this.gastarTrampa();
    }

    public void gastarTrampa()
    {
        this.turnosUtiles -= 1;
        if (this.turnosUtiles <= 0) {this.destruir();}
    }

    @Override
    public void atacar() 
    {
        List<Enemigo> enemigoObjetivos = this.objetivo.hallarObjetivo(posicion, enemigos, this);
        for (Enemigo enemigo : enemigoObjetivos) 
        {
            enemigo.ralentizar();
        }
    }    

    @Override
    public int incrementarDistanciaV()
    {
        return 10000;
    }

}


