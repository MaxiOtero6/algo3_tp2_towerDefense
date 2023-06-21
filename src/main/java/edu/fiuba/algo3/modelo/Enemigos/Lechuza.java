package edu.fiuba.algo3.modelo.Enemigos;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Objetivos.ObjetivoLechuza;
import edu.fiuba.algo3.modelo.Enemigos.Ruta.Ruta;
import edu.fiuba.algo3.modelo.Enemigos.Ruta.RutaH;
import edu.fiuba.algo3.modelo.Enemigos.Ruta.RutaL;
import edu.fiuba.algo3.modelo.Enemigos.Salud.Muerto;
import edu.fiuba.algo3.modelo.Enemigos.Volador.EsVolador;
import edu.fiuba.algo3.modelo.Errores.EnemigoNoRalentizableError;
public class Lechuza extends Enemigo {
    
    private static int lechuzasMuertas = 0;
    private ObjetivoLechuza objetivo = new ObjetivoLechuza();
    private LinkedList<Defensa> defensas;
    private Ruta ruta = new RutaL();

    public Lechuza(Jugador jugador, Camino camino) {
        super(5,0,5,5, jugador, camino);
        this.setVolador(new EsVolador());
    }

    @Override
    public void atacar()
    {
        objetivo.hallarObjetivo(this.defensas).destruir();
        this.salud = new Muerto();
    }

    @Override
    protected void morir() 
    {
        lechuzasMuertas++;
        enemigosMuertos++;
    }

    private void tieneMitadDeVida()
    {
        if (this.energia <= (5/2)) {this.ruta = new RutaH();}
    } 

    @Override
    public void mover()
    {
        this.tieneMitadDeVida();
        this.camino = ruta.generarCamino(this.posicion);
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
