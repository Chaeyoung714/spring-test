package cholog;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ResponseStaticTest {

    @Test
    void responseIndexPage() {
        /**
         * resources 전체에서 index.html을 찾을 수 있게 한다.
         */
        var response = RestAssured
            .given().log().all()
            .when().get("/")
            .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void responseStaticPage() {
        /**
         * resources/static에서 static.html에 접근 가능하게 한다.
         */
        var response = RestAssured
            .given().log().all()
            .when().get("/static.html")
            .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
