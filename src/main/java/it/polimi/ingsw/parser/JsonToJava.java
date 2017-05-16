package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonToJava {
    public static void main(String[] args) throws IOException {
        try(Reader reader = new InputStreamReader
                (JsonToJava.class.getResourceAsStream("/Users/camilladottino/LolloTheMagnificent/src/main/java/it/polimi/ingsw/parser/cardex.json"))){
            Gson gson = new GsonBuilder().create();
            CardEx p = gson.fromJson(reader, CardEx.class);
            System.out.println(p);
        }
    }

}