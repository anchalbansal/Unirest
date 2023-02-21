package simpleexample;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
public class Postsimp {
    @Test
    public void givenRequestBodyWhenCreatedThenCorrect() {

        HttpResponse<JsonNode> jsonResponse
                = Unirest.post("http://www.mocky.io/v2/5a9ce7663100006800ab515d")
                .body("{\"name\":\"Sam Baeldung\", \"city\":\"viena\"}")
                .asJson();

        assertEquals(201, jsonResponse.getStatus());
        System.out.println(jsonResponse);
    }
}
