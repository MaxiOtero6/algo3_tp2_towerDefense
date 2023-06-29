package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.Defensas.Torres.TorrePlateada;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vista.BordesDefensas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;

import java.util.List;

public class BotonPlateadaEventHandler implements EventHandler<ActionEvent> {

    private BordesDefensas bordes;
    private Jugador jugador;
    private List<CasillaMapaEventHandler> casillas;
    private AudioClip sonidoError;
    private AudioClip sonidoClick;
    public BotonPlateadaEventHandler(BordesDefensas bordes, Jugador jugador, List<CasillaMapaEventHandler> casillas,
                                     AudioClip sonidoClick, AudioClip sonidoError){
        this.bordes = bordes;
        this.jugador = jugador;
        this.casillas = casillas;
        this.sonidoClick = sonidoClick;
        this.sonidoError = sonidoError;
    }
    @Override
    public void handle(ActionEvent actionEvent){
        if (jugador.obtenerCreditosRestantes() >= 20) {
            sonidoClick.play();
            bordes.activarBordesTorres();
            for (CasillaMapaEventHandler casilla : casillas) {
                casilla.asignarDefensa(new TorrePlateada());
            }
        }else{
            sonidoError.play();
        }
    }
}
