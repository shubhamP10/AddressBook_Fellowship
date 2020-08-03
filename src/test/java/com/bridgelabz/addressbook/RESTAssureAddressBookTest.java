package com.bridgelabz.addressbook;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class RESTAssureAddressBookTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4000;
    }

    public Response getPersonList(){
        return RestAssured.get("/AddressBook/list");
    }

    @Test
    public void onCallingList_ReturnPersonList() {
        Response response = getPersonList();
        System.out.println("At First: "+response.asString());
        response.then().body("id",Matchers.hasItems(1,2));
        response.then().body("State", Matchers.hasItems("MH"));
    }

    @Test
    public void givenPersonDetails_OnPost_ShouldReturnAddedPersonDetails() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"Name\":\"nikhil\",\"Phone\":\"9898989898\",\"State\":\"TN\"}")
                .when()
                .post("/AddressBook/create");
        String responseString = response.asString();
        JSONObject jsonObject = new Gson().fromJson(responseString,JSONObject.class);
        int id = (int) jsonObject.get("id");
        response.then().body("id",Matchers.any(Integer.class));
        System.out.println(responseString);
    }
}
// json-server --port 4000 --routes routes.json --watch RESTAssureJSONFile.json