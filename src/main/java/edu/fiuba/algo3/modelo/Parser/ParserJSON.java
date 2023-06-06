package edu.fiuba.algo3.modelo.Parser;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParserJSON {
    
    public static JsonNode leerJSON(String filePath)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            JsonNode root = mapper.readTree(
                new File(filePath)
                );
            
            return root;
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
