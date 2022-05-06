package com.shorna.nutritionistapi.repositories;

import com.shorna.nutritionistapi.models.NutritionistUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NutritionistUserRepository extends MongoRepository<NutritionistUser, String> {
    NutritionistUser getNutritionistUserByEmail(String email);

    Boolean existsByEmail(String email);

}
