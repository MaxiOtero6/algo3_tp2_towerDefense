package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.vista.BordesDefensas;
import edu.fiuba.algo3.vista.VistaDefensas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;

import java.util.List;


public class CasillaMapaEventHandler implements EventHandler<ActionEvent> {

    private Parcela parcela;
    private Defensa defensa;
    private GridPane root;
    private int coordenadaX;
    private int coordenadaY;
    private Partida partida;
    private BotonSkipEventHandler botonSkipEventHandler;
    private List<CasillaMapaEventHandler> casillas;


    private AudioClip sonidoTorre;
    private AudioClip sonidoTrampa;
    private AudioClip sonidoError;
    private BordesDefensas borde;

    
    public CasillaMapaEventHandler(Parcela parcela, GridPane root, int coordenadaX, int coordenadaY, Partida partida,
                                   AudioClip sonidoTorre, AudioClip sonidoTrampa, AudioClip sonidoError,
                                   BotonSkipEventHandler botonSkipEventHandler, BordesDefensas borde){
        this.parcela = parcela;
        this.defensa = new NoTorre();
        this.sonidoTorre = sonidoTorre;
        this.sonidoTrampa = sonidoTrampa;
        this.sonidoError = sonidoError;
        this.root = root;
        this.coordenadaX = coordenadaX;
        this. coordenadaY = coordenadaY;
        this.partida = partida;
        this.borde = borde;

        this.botonSkipEventHandler = botonSkipEventHandler;
    }
    @Override
    public void handle(ActionEvent actionEvent){
        if(!(defensa instanceof NoTorre)){

            //sound
            if((defensa instanceof TorreBlanca) && parcela instanceof Tierra){
                sonidoTorre.play();
            }
            if(defensa instanceof TrampaArenosa && parcela instanceof Pasarela){
                sonidoTrampa.play();
            }
            try {
                partida.construirDefensa(defensa, coordenadaX, coordenadaY);
                VistaDefensas vistaDefensa = new VistaDefensas(root, defensa);
                vistaDefensa.dibujar();
                botonSkipEventHandler.agregarDefensa(vistaDefensa);
                borde.activarBotones();
                this.restablecerCasillas();
            } catch (NullPointerException e){
                sonidoError.play();
            }

        }
  }
  public void asignarDefensa(Defensa defensa){
        this.defensa = defensa;
  }
  private void restablecerCasillas() {
        for (CasillaMapaEventHandler casilla : casillas) {
          casilla.asignarDefensa(new NoTorre());
      }
  }
  public void asignarCasillas(List<CasillaMapaEventHandler> casillas){
        this.casillas = casillas;
  }
}
