package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Jugador {
    private Creditos creditos;
    private int vida;
    private String nombre;
    private static final Logger logger = LogManager.getLogger(Jugador.class);

    public Jugador()
    {
        this.creditos = new Creditos();
        this.vida = 20;
    }

    public Jugador(int vida, int creditos)
    {
        this.creditos = new Creditos(creditos);
        this.vida = vida;
    }

    public void agregarCreditos(int creditos)
    {
        this.creditos.agregarCreditos(creditos);
    }

    public void gastarCreditos(int creditosAGastar){
        creditos.gastarCreditos(creditosAGastar);
    }

    public void recibirDanio(int danio, String enemigo)
    {
        this.vida -= danio;
        PerderPartidaError.comprobarPerderJuego(this.vida);
        SingleLogger.obtenerLogger().imprimirLog(String.format(
                "%s llega a la meta, produce %d de daño al jugador", enemigo, danio));
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Jugador)
        {
            Jugador jugador = (Jugador)o;
            return (this.creditos.equals(jugador.creditos) && this.vida == jugador.vida);
        }
        return false;
    }

    public int obtenerVidaRestante(){
        return this.vida;
    }

    public int obtenerCreditosRestantes(){
        return this.creditos.obtenerCreditosRestantes();
    }
}
