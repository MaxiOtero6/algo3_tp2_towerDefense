package edu.fiuba.algo3.modelo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SingleLogger{
    private Logger logger;
    private static SingleLogger singleLogger;

    private SingleLogger(Logger logger){
        this.logger = logger;
    }
    public static void inicializar(Logger logger){
        if (singleLogger == null){
            singleLogger = new SingleLogger(logger);
        }
    }
    public static SingleLogger obtenerLogger(){
        return singleLogger;
    }
    public void imprimirLog(String mensaje){
        logger.info(mensaje);
    }
}
