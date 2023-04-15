package com.hotelreservation.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests extends BaseTest{
    @Test
    public void getAllBookingTest() {
        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }

    @Test
    public void getBookings_with_firstname_filter_test(){
        // yeni bir rezervasyon oluşturmak
        int bookingId = createBookingId();
        // çağrımıza query parametresi ekleme
        spec.queryParam("firstname","Fatih");
        spec.queryParam("lastname","Ozdemir");

        //çağrı gerçekleştir
        Response response = given(spec)
                .when()
                .get("/booking");
        //Assertion/test yaz
        response
                .then()
                .statusCode(200);

        List<Integer> list = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(list.contains(bookingId));
    }
}
