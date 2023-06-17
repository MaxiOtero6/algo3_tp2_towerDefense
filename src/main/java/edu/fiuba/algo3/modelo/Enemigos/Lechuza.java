package edu.fiuba.algo3.modelo.Enemigos;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigos.Objetivos.ObjetivoLechuza;
import edu.fiuba.algo3.modelo.Enemigos.Volador.EsVolador;
import edu.fiuba.algo3.modelo.Errores.EnemigoNoRalentizableError;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoH;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoL;

public class Lechuza extends Enemigo {
    
    private static int lechuzasMuertas = 0;
    private ObjetivoLechuza objetivo = new ObjetivoLechuza();
    private LinkedList<Defensa> defensas;

    public Lechuza(Jugador jugador, Camino camino) {
        super(5,0,5,5, jugador, camino);
        this.setVolador(new EsVolador());
    }

    public Lechuza(Jugador jugador) {
        super(5,0,5,5, jugador, new Camino(CreadorCaminoL.crearCaminoL()));
        this.setVolador(new EsVolador());
    }

    @Override
    public void atacar()
    {
        objetivo.hallarObjetivo(this.defensas).destruir();
    }

    @Override
    protected void morir() 
    {
        lechuzasMuertas++;
        enemigosMuertos++;
    }

    private boolean tieneMitadDeVida()
    {
        return (this.energia <= (5/2));
    } 

    @Override
    public void mover()
    {
        if (this.tieneMitadDeVida()) {this.camino = new Camino(CreadorCaminoH.crearCaminoH(this.posicion));}
        super.mover();
    }

    @Override
    public void ralentizar()
    {
        throw new EnemigoNoRalentizableError();
    }

    public void setDefensas(LinkedList<Defensa> defensas)
    {
        this.defensas = defensas;
    }
}
