package it.polimi.ingsw.parser;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonToJava {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(new CardEx("ciao",2, "ciao"));
        System.out.println(jsonString);

        Gson gson2 = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader("/cardex.json"));
            CardEx obj = gson2.fromJson(br, CardEx.class);
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}