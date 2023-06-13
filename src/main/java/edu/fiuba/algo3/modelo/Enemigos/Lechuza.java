package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.Objetivos.ObjetivoLechuza;
import edu.fiuba.algo3.modelo.Enemigos.Volador.EsVolador;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoH;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoL;

public class Lechuza extends Enemigo {
    
    private static int lechuzasMuertas = 0;

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
        ObjetivoLechuza.hallarObjetivo().destruir();
    }

    @Override
    protected void morir() 
    {
        lechuzasMuertas++;
        enemigosMuertos++;
    }

    @Override
    public void mover()
    {
        if (this.tieneMitadDeVida()) {this.camino = new Camino(CreadorCaminoH.crearCaminoH(this.posicion));}
        super.mover();
    }
}
