package edu.fiuba.algo3.modelo.Defensas.Torres;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class Torre extends Defensa{

    private int rango;
    private int danio;
    private int progresoConstruccion;
    private Estado estado;


    public Torre(int coste, int rango, int danio, int progresoConstruccion)
    {
        super(coste);
        this.rango = rango;
        this.danio = danio;
        this.progresoConstruccion = progresoConstruccion;
        this.estado = new EstadoDesactivado();
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

    public Enemigo hallarEnemigoMasCercano(List<Enemigo> enemigos)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> enemigo.esVisible())
            .filter(enemigo -> enemigo.estaVivo())
            .filter(enemigo -> enemigo.calcDistancia(this.posicion) <= this.rango)
            .min(Comparator.comparingDouble(enemigo -> enemigo.calcDistancia(this.posicion)))
            .orElse(new NoEnemigo());

        return enemigoMasCercano;
    }

    public void atacar()
    {
        Enemigo enemigoMasCercano = this.hallarEnemigoMasCercano(this.enemigos);
        enemigoMasCercano.recibirDanio(this.danio);
    }
}