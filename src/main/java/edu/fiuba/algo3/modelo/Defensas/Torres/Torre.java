package edu.fiuba.algo3.modelo.Defensas.Torres;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Objetivos.ObjetivoTorre;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class Torre extends Defensa{

    private int rango;
    private int danio;
    private int progresoConstruccion;
    private Estado estado;
    private ObjetivoTorre objetivo;


    public Torre(int coste, int rango, int danio, int progresoConstruccion)
    {
        super(coste);
        this.rango = rango;
        this.danio = danio;
        this.progresoConstruccion = progresoConstruccion;
        this.estado = new EstadoDesactivado();
        this.objetivo = new ObjetivoTorre();
    }

    public void avanzarTurno(){
        this.estado.avanzarTurno(this);
    }

    public boolean progresarConstruccion(){
        this.progresoConstruccion -= 1;
        return this.chequearProgreso();
    }

    public boolean chequearProgreso(){
        return this.progresoConstruccion == 0;
    }

    public void cambiarEstado(){
        this.estado = new EstadoActivado();
    }

    public void atacar()
    {
        objetivo.hallarObjetivo(this.posicion, this.enemigos, this.rango).recibirDanio(this.danio);
    }
}