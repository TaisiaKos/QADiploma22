package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataGenerator {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String returnResponse(DataHelper.CardNumberAPI card, String path, int apiStatus) {
        Response response = given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post(path)
                .then()
                .statusCode(apiStatus)
                .extract().response();
        return response.path("message");
    }

    @Test
    @DisplayName("Successful transaction pay by card - 200!")
    void shouldApprovedPay200() {
        var card = DataHelper.getApprovedCardAPI();
        String path = "/api/v1/pay";
        int apiStatus = 200;
        returnResponse(card, path, apiStatus);

      //  var actual = SQLHelper.getPaymentGate();
     //   var expected = "APPROVED";
     //   assertEquals(expected, actual);
    }

    @Test
    @DisplayName("No successful transaction pay by card - 500")
    void shouldDeclinedPay500() {
        var card = DataHelper.getDeclinedCardAPI();
        String path = "/api/v1/pay";
        int apiStatus = 500;
        returnResponse(card, path, apiStatus);

     //   var actual = SQLHelper.getPaymentGate();
     //   var expected = "DECLINED";
     //   assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Successful transaction credit by card - 200!")
    void shouldApprovedCredit200() {
        var card = DataHelper.getApprovedCardAPI();
        String path = "/api/v1/credit";
        int apiStatus = 200;
        returnResponse(card, path, apiStatus);

    //    var actual = SQLHelper.getCreditGate();
    //    var expected = "APPROVED";
    //    assertEquals(expected, actual);
    }

    @Test
    @DisplayName("No successful transaction credit by card - 500")
    void shouldDeclinedCredit500() {
        var card = DataHelper.getDeclinedCardAPI();
        String path = "/api/v1/credit";
        int apiStatus = 500;
        returnResponse(card, path, apiStatus);

    //    var actual = SQLHelper.getCreditGate();
   //     var expected = "DECLINED";
    //    assertEquals(expected, actual);
    }
}
