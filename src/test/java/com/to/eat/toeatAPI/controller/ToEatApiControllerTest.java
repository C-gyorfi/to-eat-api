package com.to.eat.toeatAPI.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToEatApiControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

//    @BeforeEach
//    void SetupDb() {
//        FOODEntity entity = FoodEntity.builder().id('1').name("Bread").build();
//
//        foodRepository.save(entity);
//    }
//
//    @Test
//    void TestCanGetAFood() {
//        ResponseEntity<FoodResponse> response = testRestTemplate.exchange("/food/1", HttpMethod.GET, null, FoodResponse.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Bread", response.getBody().getName());
//    }
}
