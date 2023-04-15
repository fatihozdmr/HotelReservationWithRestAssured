package com.hotelreservation.tests;

import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingResponse;
import com.hotelreservation.models.Bookingdates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class CreateBookingTests extends BaseTest {

    @Test
    public void createBookingTest() {
        Response response = createBooking();

        Assertions.assertEquals("Fatih", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Ozdemir", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
    }

    @Test
    public void createBookingWithPojo() {
        Bookingdates bookingdates = new Bookingdates("2023-03-25", "2023-03-28");
        Booking booking = new Booking("Udemy", "Course", 500, true,bookingdates,"non smoking room");
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse + " Booking Response kaydedildi");

        Assertions.assertEquals("Udemy",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Course",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("non smoking room",bookingResponse.getBooking().getAdditionalneeds());
    }
}
