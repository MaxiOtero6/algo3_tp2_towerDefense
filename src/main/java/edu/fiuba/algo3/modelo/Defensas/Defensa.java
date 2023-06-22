package edu.fiuba.algo3.modelo.Defensas;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Salud.Destruida;
import edu.fiuba.algo3.modelo.Defensas.Salud.Operativa;
import edu.fiuba.algo3.modelo.Defensas.Salud.SaludDefensa;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.SingleLogger;

public abstract class Defensa {
    
    private int coste;
    protected Posicion posicion;
    protected List<Enemigo> enemigos;
    private SaludDefensa salud;

    public Defensa(int coste)
    {
        this.coste = coste;
        this.salud = new Operativa();
    }

    public void destruir()
    {
        this.salud = new Destruida();
    }

    public void avanzarTurno(LinkedList<Defensa> defensas)
    {
        this.salud.avanzarTurno(defensas, this);
    }

    public abstract void avanzarTurno();

    public abstract void atacar();

    public void gastarCreditos(Jugador jugador)
    {
        jugador.gastarCreditos(this.coste);
    }

    public void setEnemigos(List<Enemigo> enemigos)
    {
        this.enemigos = enemigos;
    }

    public void setearPosicion(Posicion posicion)
    {
        String tipoDefensa = this.getClass().getSimpleName();
        SingleLogger.obtenerLogger().imprimirLog(String.format(
                "El jugador construye %s en la posicion %s", tipoDefensa, posicion.imprimirPosicion() ));
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o.getClass() == this.getClass())
        {
            Defensa defensa = (Defensa)o;
            if (this.posicion == null && defensa.posicion == null) {return true;}
            return (this.posicion.equals(defensa.posicion) && this.salud.getClass() == defensa.salud.getClass());
        }
        return false;
    }
    public Posicion getPosicion(){
        return posicion;
    }
    public SaludDefensa getSalud(){
        return salud;
    }
}
