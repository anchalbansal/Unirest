package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import Files.payload;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Main {
    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        //POST Request
       String response = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.addPlace()).post("maps/api/place/add/json")
                .then().assertThat()
                .statusCode(200)
               .body("scope", equalTo("APP"))
               .header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
        System.out.println(response);
       // JsonPath js = ReuseableMethods.stringToJson(response);
        //String  placeID  = js.getString("place_id");
       // System.out.println(placeID);

        //PUT Request
        String newAddress = "70 Winter Africa";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                   //     "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //GET Request

        String getRequestResponse = given().log().all().queryParam("key","qaclick123")
        //        .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().extract().response().asString();

       // JsonPath js1 = ReuseableMethods.stringToJson(getRequestResponse);
      //  String actualAddress = js1.getString("address");
       // System.out.println(actualAddress);
       // Assert.assertEquals(actualAddress,newAddress);

        //POST request by sending JSON file
        String response1 = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\anchal.bansal\\addPlace.json")))).post("maps/api/place/add/json")
                .then().assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
        System.out.println(response);
       // JsonPath js2 = ReuseableMethods.stringToJson(response1);
       // String  placeID1  = js2.getString("place_id");
       // System.out.println(placeID);
    }
}