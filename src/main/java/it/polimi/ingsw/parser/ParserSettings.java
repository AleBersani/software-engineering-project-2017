package it.polimi.ingsw.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParserSettings {
    private BufferedReader br = null;
    private FileReader fr = null;
    private static String PATH = System.getProperty("user.dir") + "/resources/json/";


    public JsonObject extractJsonObject(String jsonName) throws IOException {
        String path = PATH + jsonName;
        open(path);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        close();
        return object;
    }


    private void open(String path) throws IOException{
        fr = new FileReader(path);
        br = new BufferedReader(fr);
    }
    private void close() throws IOException {
        br.close();
        fr.close();
    }

}
