package testuni;
import Files.payload;
import io.restassured.path.json.JsonPath;
import kong.unirest.*;
import kong.unirest.json.JSONObject;
import org.example.ReUsableMethods;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class UnirestImplementation {

    private static final Config baseURL = Unirest.config().defaultBaseUrl("https://rahulshettyacademy.com");

    @Test(priority = 1)
    public void requestPOST(ITestContext context) throws UnirestException {
        HttpResponse<JsonNode> responsePOST = Unirest
                .post(baseURL.getDefaultBaseUrl().concat("/maps/api/place/add/json"))
                .queryString("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.addPlace())
                .asJson();

        String rs1 = responsePOST.getBody().toString();
        System.out.println(rs1);
        assertEquals(200,responsePOST.getStatus());
        JsonPath js = ReUsableMethods.stringToJson(rs1);
        String placeID = js.getString("place_id");
        System.out.println(placeID);
        context.setAttribute("placeID",placeID);
    }

    @Test(priority = 2)
    public void requestPUT(ITestContext context) throws UnirestException {
        String newAddress = "70 Winter Africa2";
        HttpResponse<JsonNode> responsePUT = Unirest
                .put(baseURL.getDefaultBaseUrl().concat("/maps/api/place/update/json"))
                .queryString("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+context.getAttribute("placeID")+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .asJson();
        assertEquals(200,responsePUT.getStatus());
        JSONObject jb1 = responsePUT.getBody().getObject();
        String msg = jb1.getString("msg");
        assertThat(msg,equalTo("Address successfully updated"));
        context.setAttribute("newAddress",newAddress);
    }

    @Test(priority = 3)
    public void requestGET(ITestContext context) throws UnirestException {

        HttpResponse<JsonNode> responseGET = Unirest
                .get(baseURL.getDefaultBaseUrl().concat("/maps/api/place/get/json"))
                .queryString("key", "qaclick123")
                .queryString("place_id", context.getAttribute("placeID"))
                .asJson();
        assertEquals(200, responseGET.getStatus());
        JSONObject jb2 = responseGET.getBody().getObject();
        String actualAddress = jb2.getString("address");
        System.out.println(actualAddress);
        assertEquals(actualAddress, context.getAttribute("newAddress"));
    }
        @Test(priority = 4)
        public void requestDELETE( ) throws UnirestException {

            HttpResponse<JsonNode> responseDELETE = Unirest
                    .delete(baseURL.getDefaultBaseUrl())
                    .asJson();
            int ds1 = responseDELETE.getStatus();
            System.out.println(ds1);

        }
}

