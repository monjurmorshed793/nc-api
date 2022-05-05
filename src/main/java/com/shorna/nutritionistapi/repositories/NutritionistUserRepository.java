package com.shorna.nutritionistapi.repositories;

import com.shorna.nutritionistapi.models.NutritionistUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NutritionistUserRepository extends MongoRepository<NutritionistUser, String> {
    NutritionistUser getNutritionistUserByEmail(String email);
}
