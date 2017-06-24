package it.polimi.ingsw.client.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParserSettingsClient {
    private static final String PATH = System.getProperty("user.dir") + "/resources/client/cli/json/";

    private BufferedReader br = null;
    private FileReader fr = null;

    public JsonArray extractJsonArray(String jsonName) throws IOException {
        JsonParser parser = new JsonParser();
        String path = PATH + jsonName;
        open(path);
        JsonArray object = parser.parse(br).getAsJsonArray();
        close();
        return object;
    }

    public JsonObject extractJsonObject(String jsonName) throws IOException {
        JsonParser parser = new JsonParser();
        String path = PATH + jsonName;
        open(path);
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
