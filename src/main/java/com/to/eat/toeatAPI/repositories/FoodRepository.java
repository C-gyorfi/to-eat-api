package com.to.eat.toeatAPI.repositories;

import com.to.eat.toeatAPI.model.Food;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

@EnableScan
public interface FoodRepository extends CrudRepository<Food, String> {
    Optional<Food> findById(String id);
}