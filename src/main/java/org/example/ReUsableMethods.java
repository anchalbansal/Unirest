package org.example;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    public static JsonPath stringToJson(String response){
        JsonPath js1 = new JsonPath(response);
        return js1;

    }
}
