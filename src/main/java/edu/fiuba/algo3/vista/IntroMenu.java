package edu.fiuba.algo3.vista;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Meta;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Rocoso;
import edu.fiuba.algo3.modelo.Parcelas.Tierra;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.controlador.BotonOkEventHandler;
import edu.fiuba.algo3.controlador.NombreEventHandler;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Enemigos.*;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
// import javafx.scene.media.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.scene.layout.BorderStroke;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class IntroMenu {

    private int turno = 0;
    private TextField textoNombre;
    private Button okButton;
    private Button iniButton;
    private Label validationLabel;
    private Label labelVida;
    private Label labelCreditos;
    private String nombre;
    private Partida partida;
    private Jugador jugador;
    private String imagenTorrePlateada = (new File("src/main/resources/image/torrePlateada.png")).toURI().toString();
    private String imagenTorreBlanca = (new File("src/main/resources/image/torreBlanca.png")).toURI().toString();
    private String imagenTrampaArenosa = (new File("src/main/resources/image/trampaArenosa.png")).toURI().toString();
    private String imagenHormiga = (new File("src/main/resources/image/hormiga.png")).toURI().toString();
    private String imagenArania = (new File("src/main/resources/image/arania.png")).toURI().toString();
    private String imagenTopo = (new File("src/main/resources/image/topo.png")).toURI().toString();
    private String imagenTopoEscondido = (new File("src/main/resources/image/topo_escondido.png")).toURI().toString();
    private String imagenLechuza = (new File("src/main/resources/image/lechuza.png")).toURI().toString();
    private Alert alert;

    private Button botonInicial;
    private List<VistaDefensas> listaVistaDefensas;
    List<Parcela> pasarelas;
    List<List<Parcela>> mapa;
    GridPane root;

    

    public void crearUI(Stage stagePrincipal) {
        listaVistaDefensas = new ArrayList<>();
        alert = new Alert(AlertType.INFORMATION);
        partida = new Partida();
        jugador = partida.obtenerJugador();
        labelVida = new Label(jugador.obtenerVidaRestante() + "/20");
        labelCreditos = new Label("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        String css = "-fx-prompt-text-fill: black;";
        ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/logo.png")).toURI().toString());
        ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/image.png")).toURI().toString());
        backgroundImageView.fitWidthProperty().bind(stagePrincipal.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stagePrincipal.heightProperty());

        textoNombre = new TextField();
        
        textoNombre.setPromptText("Ingrese nombre: ");
        textoNombre.setMaxWidth(200);
        textoNombre.setStyle("-fx-control-inner-background: #FFC864;");
        textoNombre.setStyle(textoNombre.getStyle() + css);
        

        okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #FFA500;");

        iniButton = new Button("Iniciar Partida");
        iniButton.setStyle("-fx-background-color: #FFA500;");

        validationLabel = new Label();

        iniButton.setOnAction(event -> {
            
            IniciarPartida(stagePrincipal);
        });  

        botonInicial = new Button("Siguiente");
        botonInicial.setStyle("-fx-background-color: #FFA500;");
        botonInicial.setFont(new Font(15));
        botonInicial.setPrefWidth(botonInicial.getPrefWidth() + 100);
        botonInicial.setPrefHeight(botonInicial.getPrefHeight() + 50);

        Scene nuevaEscena = new Scene(new VBox(textoNombre, okButton), 800, 600);
        stagePrincipal.setScene(nuevaEscena);


        StackPane pantallaInicial = new StackPane();

        VBox inicio = new VBox();
        inicio.setAlignment(Pos.CENTER);
        inicio.setSpacing(10);

        NombreEventHandler nombreEventHandler = new NombreEventHandler(okButton);
        textoNombre.setOnKeyPressed(nombreEventHandler);

        okButton.setOnAction(event -> {
            validarNombre(inicio);
        });

        inicio.getChildren().addAll(logoAlgoDefense, botonInicial);

        botonInicial.setOnAction(event -> {
            inicio.getChildren().remove(botonInicial);
            inicio.getChildren().addAll(textoNombre, okButton);
        });


        pantallaInicial.getChildren().addAll(backgroundImageView, inicio);

        Scene escenaInicial = new Scene(pantallaInicial, 800, 600);
        stagePrincipal.setScene(escenaInicial);
    }

    private void validarNombre(VBox inicio) {
        String inputText = textoNombre.getText();
        Label valLabel = new Label();
        inicio.getChildren().removeAll(valLabel, validationLabel);
        if (inputText.length() >= 6) {
            nombre = inputText;
            valLabel.setText("Nombre elegido: " + nombre);
            valLabel.setStyle("-fx-text-fill: #a86f13;");
            inicio.getChildren().removeAll(valLabel, textoNombre, okButton);
            inicio.getChildren().addAll(valLabel, iniButton);
        } else {
            validationLabel.setText("Ingrese un nombre de al menos 6 caracteres");
            validationLabel.setStyle("-fx-text-fill: #FF0000;");
            inicio.getChildren().add(validationLabel);
        }
    }


    private void ejecutarBotonSkipTurno(Stage stagePrincipal) {
        //label2.setText(jugador.obtenerVidaRestante() + "/20");
        // Code for botonSkipTurno
        //soundClip.play();

        if (turno == 13){
            turno = 0;
         }
        partida.avanzarTurno(turno);
        turno++;
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : root.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                Image image = imageView.getImage();
                String imageUrl = image.getUrl();
                if (imageUrl.equals(imagenHormiga) ||
                    imageUrl.equals(imagenArania) ||
                    imageUrl.equals(imagenTopo) ||
                    imageUrl.equals(imagenLechuza)||
                    imageUrl.equals(imagenTopoEscondido)) {
                    nodesToRemove.add(node);
                }
            }
        }
        root.getChildren().removeAll(nodesToRemove);
        for (int i = 0; i < partida.obtenerEnemigos().size(); i++) {
            Enemigo enemigoActual = partida.obtenerEnemigos().get(i);
            int coordenadaX = enemigoActual.obtenerPosicion().obtenerCoordenadaX();
            int coordenadaY = enemigoActual.obtenerPosicion().obtenerCoordenadaY();
            if (enemigoActual instanceof Hormiga) {
                root.add(new ImageView(imagenHormiga), coordenadaX, coordenadaY);
            } else if (enemigoActual instanceof Arania) {
                root.add(new ImageView(imagenArania), coordenadaX, coordenadaY);
            } else if (enemigoActual instanceof Topo) {
                Topo topo = (Topo) enemigoActual;
                if (topo.esSubterraneo()){
                    root.add(new ImageView(imagenTopoEscondido), coordenadaX, coordenadaY);
                }
                else{
                    root.add(new ImageView(imagenTopo), coordenadaX, coordenadaY);
                }
                
            } else if (enemigoActual instanceof Lechuza) {
                root.add(new ImageView(imagenLechuza), coordenadaX, coordenadaY);
            }
        }
        labelVida.setText(jugador.obtenerVidaRestante() + "/20");
        labelCreditos.setText("Creditos Restantes: " + jugador.obtenerCreditosRestantes());
        //ACTUALIZAR DEFENSAS
        for (VistaDefensas vista: listaVistaDefensas) {
            vista.update();
        }

        //ALERTA DE PERDIDA
        if(jugador.obtenerVidaRestante() <= 0  && jugador.obtenerVidaRestante() < 0){

            // alert.setGraphic(new ImageView(imagenArania));
            
            // DialogPane dialogPane = alert.getDialogPane();
            // dialogPane.setStyle("-fx-background-color: orange;");
            // alert.setTitle("Termino la partida");
            // alert.setHeaderText("Perdiste!");
            // alert.setContentText("Te quedaste sin puntos de vida!");
            // alert.showAndWait();
            StackPane pantallaFinalPerdida = new StackPane();
            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/perdiste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenPerder.png")).toURI().toString());
            backgroundImageView.fitWidthProperty().bind(stagePrincipal.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stagePrincipal.heightProperty());

            pantallaFinalPerdida.getChildren().addAll(backgroundImageView, logoAlgoDefense);

            Scene escenaInicial = new Scene(pantallaFinalPerdida, 800, 600);
            stagePrincipal.setScene(escenaInicial);
        }
        //ALERTA DE VICTORIA
        if(partida.obtenerEnemigos().size() == 0 && jugador.obtenerVidaRestante() > 0){
            // alert.setGraphic(new ImageView(imagenTorrePlateada));
            
            // DialogPane dialogPane = alert.getDialogPane();
            // dialogPane.setStyle("-fx-background-color: lightblue;");
            // alert.setTitle("Termino la partida");
            // alert.setHeaderText("Ganaste!");
            // alert.setContentText("Eliminaste a todos los enemigos!");
            // alert.showAndWait();
            StackPane pantallaFinalGanar = new StackPane();
            ImageView logoAlgoDefense = new ImageView((new File("src/main/resources/image/ganaste.png")).toURI().toString());
            ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/imagenGanar.png")).toURI().toString());
            backgroundImageView.fitWidthProperty().bind(stagePrincipal.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stagePrincipal.heightProperty());

            pantallaFinalGanar.getChildren().addAll(backgroundImageView, logoAlgoDefense);

            Scene escenaInicial = new Scene(pantallaFinalGanar, 800, 600);
            stagePrincipal.setScene(escenaInicial);
        }
    }
        HBox enemigosEnParcela = new HBox();

    private void IniciarPartida(Stage stagePrincipal) {

        //SECCION PARA MOSTRAR ENEMIGOS EN LA PARCELA ACTUAL
        Color bordeAzul = Color.BLANCHEDALMOND;
        double borderWidth = 2.0; 

        enemigosEnParcela.setSpacing(10);
        enemigosEnParcela.setPadding(new Insets(10));
        enemigosEnParcela.setAlignment(Pos.CENTER);
        enemigosEnParcela.setPrefHeight(75);

        BackgroundFill backgroundFillEnemigos = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background backgroundEnemigos = new Background(backgroundFillEnemigos);
        enemigosEnParcela.setBackground(backgroundEnemigos);

        enemigosEnParcela.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeAzul, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        validationLabel.setText("Partida iniciada");

        //Mapa getter
        mapa = partida.obtenerMapa();

        
        ContenedorTorre torreAux = new ContenedorTorre();
        
        int SIZE = 15;
        int length = SIZE;
        int width = SIZE;
        
        root = new GridPane();
        root.setPadding(new Insets(10));

       

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                int coordenadaX = x;
                int coordenaday = y;

                Button casillaMapa = new Button();
                casillaMapa.setPrefHeight(50);
                casillaMapa.setPrefWidth(50);
                casillaMapa.setAlignment(Pos.CENTER);

                Parcela parcelaActual = mapa.get(y).get(x);
                
                
                if(parcelaActual instanceof Tierra) {casillaMapa.setStyle("-fx-background-color: #699922;");}
                if(parcelaActual instanceof Largada) {casillaMapa.setStyle("-fx-background-color: #599ed4;");}
                if(parcelaActual instanceof Meta) {casillaMapa.setStyle("-fx-background-color: #FFA500;");}
                if(parcelaActual instanceof Rocoso) {casillaMapa.setStyle("-fx-background-color: #38393b;");}
                if(parcelaActual instanceof Pasarela) {casillaMapa.setStyle("-fx-background-color: #d1b680;");}

                

                casillaMapa.setOnAction(event -> {
                    if(!torreAux.puseDefensa){
                        Defensa torreActual = torreAux.getTorre();

                        try {
                            partida.construirDefensa(torreActual, coordenadaX, coordenaday);
                            VistaDefensas vistaDefensa = new VistaDefensas(root, torreActual);
                            vistaDefensa.dibujar();
                            torreAux.ponerTorre();
                            listaVistaDefensas.add(vistaDefensa);
                            ejecutarBotonSkipTurno(stagePrincipal);
                            activarBotones();
                        } catch (NullPointerException e){
                            //System.out.println("TESTTESTTESTTEST");
                            AudioClip soundClip = new AudioClip(new File("src/main/resources/sound/doorwuz.wav").toURI().toString());
                            //AudioClip errorSound = new AudioClip(getClass().getResource("/sound/error.mp3").toExternalForm());
                            soundClip.play();
                        }

                    }

                });

                casillaMapa.setOnMouseEntered(event -> mostrarEnemigos(enemigosEnParcela, coordenadaX, coordenaday));
                casillaMapa.setOnMouseExited(event -> sacarEnemigos(enemigosEnParcela));
 
                root.add(casillaMapa, x, y);
                
            }
        }
 
        //TORRE PLATEADA DE EJEMPLO
        //root.add(new ImageView(imagenTorrePlateada),4,7);

        Jugador jugador = partida.obtenerJugador();
        VBox datosUsuario = new VBox();
        datosUsuario.setSpacing(10);
        datosUsuario.setPadding(new Insets(10));
        

        Label label1 = new Label("Nombre: " + nombre);
        labelVida = new Label(jugador.obtenerVidaRestante() + "/20");

        datosUsuario.getChildren().addAll(label1, labelVida, labelCreditos);

        Button botonPlateada = new Button();
        botonPlateada.setGraphic(new ImageView(imagenTorrePlateada));
        botonPlateada.setText("Torre Plateada");
        botonPlateada.setOnAction(event -> {
            if(jugador.obtenerCreditosRestantes() >= 20){
                activarBordesTorres();
                TorrePlateada torreCreada = new TorrePlateada();
                torreAux.setTorre(torreCreada);
            }
        });

        Button botonBlanca = new Button();
        botonBlanca.setGraphic(new ImageView(imagenTorreBlanca));
        botonBlanca.setText("Torre Blanca");
        botonBlanca.setOnAction(event -> {
            if(jugador.obtenerCreditosRestantes() >= 10){
                activarBordesTorres();
                TorreBlanca torreCreada = new TorreBlanca();
                torreAux.setTorre(torreCreada);
            }    
        });

        Button botonTrampa = new Button();
        botonTrampa.setGraphic(new ImageView(imagenTrampaArenosa));
        botonTrampa.setText("Trampa Arenosa");
        botonTrampa.setOnAction(event -> {
            if(jugador.obtenerCreditosRestantes() >= 25){
                activarBordesTrampaArena();
                TrampaArenosa trampaCreada = new TrampaArenosa();
                torreAux.setTorre(trampaCreada);
            }
        
        });

        Button botonSkipTurno = new Button();
        //AudioClip soundClip = new AudioClip(new File("src/main/resources/sound/Hormiga.mp3").toURI().toString());
        botonSkipTurno.setText("Skip Turno");
        botonSkipTurno.setOnAction(event -> {
            ejecutarBotonSkipTurno(stagePrincipal);
        });

        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, new CornerRadii(8), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        datosUsuario.setBackground(background);

        datosUsuario.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeAzul, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(6), new javafx.scene.layout.BorderWidths(borderWidth))));

        VBox seccionBotones = new VBox();
        seccionBotones.setSpacing(10);
        
        seccionBotones.getChildren().addAll(datosUsuario, botonPlateada, botonBlanca, botonTrampa, botonSkipTurno);
        seccionBotones.setPadding(new Insets(10));

        HBox seccionMapa = new HBox();
        seccionMapa.getChildren().addAll(root, seccionBotones);

        VBox seccionTotal = new VBox();
        seccionTotal.getChildren().addAll(enemigosEnParcela, seccionMapa);

        ImageView backgroundImageView = new ImageView((new File("src/main/resources/image/image.png")).toURI().toString());
        backgroundImageView.fitWidthProperty().bind(seccionTotal.widthProperty());
        backgroundImageView.fitHeightProperty().bind(seccionTotal.heightProperty());

        StackPane fondoMapa = new StackPane();
        fondoMapa.getChildren().addAll(backgroundImageView, seccionTotal);

        Scene scene = new Scene(fondoMapa);    
        stagePrincipal.setTitle("Mapa");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    class ContenedorTorre {
    private Defensa defensa;
    public boolean puseDefensa = false;

    public Defensa getTorre() {
        return defensa;
    }

    public void setTorre(Defensa defensa) {
        this.defensa = defensa;
        puseDefensa = false;
    }

    public void ponerTorre() {
        puseDefensa = true;
    }

    public boolean puseTorre() {
        return puseDefensa;
    }
}

    private void activarBordesTorres(){

        Color bordeRojo = Color.RED;
        Color borderVerde = Color.GREENYELLOW;
        double borderWidth = 2.0; 
        
        for(int y = 0; y < 15; y++){
            for(int x = 0; x < 15; x++){

                Parcela parcelaActual = mapa.get(y).get(x);
                if(!(parcelaActual instanceof Tierra)){
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            
                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeRojo, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                        break;
                        }           
                    }
                } else {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            
                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(borderVerde, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                        break;
                        }           
                    }
                }
            }
        }   
    }

    private void activarBordesTrampaArena(){

        Color bordeRojo = Color.RED;
        Color borderVerde = Color.GREENYELLOW;
        double borderWidth = 2.0; 
        
        for(int y = 0; y < 15; y++){
            for(int x = 0; x < 15; x++){

                Parcela parcelaActual = mapa.get(y).get(x);
                if(!(parcelaActual instanceof Pasarela)){
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            
                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(bordeRojo, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                        break;
                        }           
                    }
                } else {
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            
                            boton.setOnMouseEntered(event -> boton.setBorder(new javafx.scene.layout.Border(
                                    new javafx.scene.layout.BorderStroke(borderVerde, javafx.scene.layout.BorderStrokeStyle.SOLID,
                                            new CornerRadii(2), new javafx.scene.layout.BorderWidths(borderWidth)))));

                            boton.setOnMouseExited(event -> boton.setBorder(null));

                        break;
                        }           
                    }
                }
            }
        }   
    }

    private void activarBotones(){
        for(int y = 0; y < 15; y++){
            for(int x = 0; x < 15; x++){
                int coordenadaX = x;
                int coordenadaY = y;

                Parcela parcelaActual = mapa.get(y).get(x);
                if((parcelaActual instanceof Parcela)){
                    for (Node botonActual : root.getChildren()) {
                        if (GridPane.getRowIndex(botonActual) == y && GridPane.getColumnIndex(botonActual) == x) {
                            Button boton = (Button)botonActual;
                            boton.setBorder(null);
                            boton.setOnMouseEntered(event -> mostrarEnemigos(enemigosEnParcela, coordenadaX, coordenadaY));
                            boton.setOnMouseExited(event -> sacarEnemigos(enemigosEnParcela));

                        break;
                        }           
                    }
                }   
            }               
        }  
    }

    private void mostrarEnemigos(HBox enemigosDeParcela, int x, int y){
        Parcela parcelaActual = mapa.get(y).get(x);
        LinkedList<Enemigo> enemigosEnLaParcela = parcelaActual.devolverEnemigos();
        for(Enemigo enemigo : enemigosEnLaParcela){
            if(enemigo instanceof Arania) {enemigosDeParcela.getChildren().add(new ImageView(imagenArania));}
            if(enemigo instanceof Hormiga) {enemigosDeParcela.getChildren().add(new ImageView(imagenHormiga));}
            if(enemigo instanceof Lechuza) {enemigosDeParcela.getChildren().add(new ImageView(imagenLechuza));}
            if(enemigo instanceof Topo) {
                Topo topoAux = (Topo)enemigo;
                if(topoAux.esSubterraneo()){
                    enemigosDeParcela.getChildren().add(new ImageView(imagenTopoEscondido));
                } else {
                    enemigosDeParcela.getChildren().add(new ImageView(imagenTopo));
                }   
            }
        }
    }
    private void sacarEnemigos(HBox enemigosDeParcela){
        enemigosDeParcela.getChildren().clear();
    }
}



