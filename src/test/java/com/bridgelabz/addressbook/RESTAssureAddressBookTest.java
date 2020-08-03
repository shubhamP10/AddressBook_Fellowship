package com.bridgelabz.addressbook;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
                .body("{\"firstName\": \"Sunil\", \"lastName\": \"Kemp\", \"address\": \"Mudhol\", " +
                        "\"city\": \"GGG\", \"state\": \"KA\", \"zipCode\": \"457885\"," +
                        "\"phone\": \"9999999999\"}")
                .when()
                .post("/AddressBook/create");
        String responseString = response.asString();
        response.then().body("id",Matchers.any(Integer.class));
        System.out.println(responseString);
    }

    @Test
    public void givenAddressBook_OnUpdate_ShouldReturnUpdatedAddressBook() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"firstName\": \"Aniket\", \"lastName\": \"Dakhare\", \"address\": \"Nagpur\", " +
                        "\"city\": \"Gondia\", \"state\": \"Bihar\", \"zipCode\": \"457885\"," +
                        "\"phone\": \"9999999999\"}")
                .when()
                .put("/AddressBook/update/3");
        response.then().body("id", Matchers.any(Integer.class));
        response.then().body("firstName", Matchers.is("Aniket"));
    }

    @Test
    public void givenAddressBookId_OnDelete_ShouldReturnSuccessStatus() {
        Response response = RestAssured.delete("/AddressBook/delete/6");
        int statusCode = response.getStatusCode();
        MatcherAssert.assertThat(statusCode, CoreMatchers.is(200));
        response = getPersonList();
        System.out.println("AT END: " + response.asString());
        response.then().body("id", Matchers.not(Integer.class));
    }
}


// json-server --port 4000 --routes routes.json --watch RESTAssureJSONFile.json