package com.shorna.nutritionistapi.services;

import com.shorna.nutritionistapi.models.NutritionistUser;
import com.shorna.nutritionistapi.repositories.NutritionistUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class NutritionistUserService {
    private final NutritionistUserRepository nutritionistUserRepository;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public void updateRefreshToken(String id, String refreshToken, Instant expirationTime){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = Update.update("refreshToken", refreshToken)
                .set("expiresOn", expirationTime);
        mongoTemplate.updateFirst(query, update, NutritionistUser.class);
    }
}
