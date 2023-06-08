package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.CreditosInsuficientesError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Jugador {
    private Creditos creditos;
    private int vida;
    private String nombre;
    private static Jugador jugador;
    private static final Logger logger = LogManager.getLogger(Jugador.class);

    private Jugador()
    {
        this.creditos = new Creditos();
        this.vida = 20;
    }

    public int obtenerCreditos()
    {
        return this.creditos.obtenerCreditos();
    }

    public int obtenerVida()
    {   
        return this.vida;
    }

    public void agregarCreditos(int creditos)
    {
        this.creditos.agregarCreditos(creditos);
    }

    public void gastarCreditos(int creditosAGastar){
        creditos.gastarCreditos(creditosAGastar);
    }

    public void recibirDanio(int danio)
    {
        this.vida -= danio;
        if (vida <= 0){
            throw new PerderPartidaError();
        }
    }

    public void restaurarVida(){
        logger.info("Se restauro la vida del jugador");
        this.vida = 20;
    }

    public static Jugador obtenerJugador()
    {
        if (jugador == null)
        {
            jugador = new Jugador();
        }
        return jugador;
    }

}
