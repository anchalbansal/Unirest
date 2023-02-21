package simpleexample;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class Getsimp {
    @Test
    public void shouldReturnStatusOkay() {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.get("https://rahulshettyacademy.com/")
                .header("accept", "application/json")
                .queryString("key","qaclick123")
                .asJson();

        assertEquals(200, jsonResponse.getStatus());
        System.out.println(jsonResponse);
    }
}
