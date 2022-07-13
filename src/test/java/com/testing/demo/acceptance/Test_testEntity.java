package com.testing.demo.acceptance;


import com.testing.demo.dto.TestDto;
import com.testing.demo.persistence.entity.TestEntity;
import com.testing.demo.persistence.repository.TestRepo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;




import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test_testEntity {
    @LocalServerPort
    private int port;
    @MockBean
    private TestRepo testRepository;
    @BeforeEach
    public  void setUp() {
        RestAssured.port = port;
    }
    @Test
    void postTestShouldReturn200() {
        when(testRepository.save(Mockito.any(TestEntity.class))).thenReturn(new TestEntity(1,"panados"));
        Response response = given().contentType(ContentType.JSON)
                .body(new TestDto("panados"))
                .when()
                .post("/test")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.body().asString());

        verify(testRepository, times(1)).save(any(TestEntity.class));


        Assertions.assertEquals("panados", response.jsonPath().getString("name"));
    }

    @Test
    void postTestShouldReturn409() {
        Response response = given().contentType(ContentType.JSON)
                .body(new TestDto("pao"))
                .when()
                .post("/test")
                .then()
                .extract().response();
        Assertions.assertEquals(409, response.statusCode());
        System.out.println(response.body().asString());
        Assertions.assertEquals("/test", response.jsonPath().getString("path"));
        Assertions.assertEquals("POST", response.jsonPath().getString("httpMethod"));
    }
}
