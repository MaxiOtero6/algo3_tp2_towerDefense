package edu.fiuba.algo3.modelo.Defensas.Torres;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Objetivos.ObjetivoTorre;

public class TorreBlanca extends Defensa{

    private int rango;
    private int danio;
    private int progresoConstruccion;
    private Estado estado;
    private ObjetivoTorre objetivo;


    public TorreBlanca(int coste, int rango, int danio, int progresoConstruccion)
    {
        super(coste);
        this.rango = rango;
        this.danio = danio;
        this.progresoConstruccion = progresoConstruccion + 1;
        this.estado = new EstadoDesactivado();
        this.objetivo = new ObjetivoTorre();
    }

    public TorreBlanca(){
        this(10,3,1,1);
    }

    public void avanzarTurno(){
        this.estado.avanzarTurno(this);
    }

    public void progresarConstruccion(){
        this.progresoConstruccion -= 1;
        if(this.chequearProgreso()) {activarTorre();}
    }

    private boolean chequearProgreso(){
        return this.progresoConstruccion == 0;
    }

    private void activarTorre(){
        this.estado = new EstadoActivado();
    }

    public void atacar()
    {
        String tipoTorre = this.getClass().getSimpleName();
        objetivo.hallarObjetivo(this.posicion, this.enemigos, this.rango, this).recibirDanio(this.danio, tipoTorre);
    }
    public Estado getEstado(){
        return estado;
    }
}