package com.hotelreservation.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest {
    @Test
    public void updateBookingTest() {
        //Request yap
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .body(bookingObject("Ayse", "Test", 300, false))
                .put("/booking/" + createBookingId());

        //Assertion/Test
        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Ayse", firstname);
        Assertions.assertEquals("Test", lastname);
        Assertions.assertEquals(300, totalPrice);
        Assertions.assertEquals(false, response.jsonPath().getJsonObject("depositpaid"));
    }
}
