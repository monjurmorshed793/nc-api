package com.shorna.nutritionistapi.models;

import com.shorna.nutritionistapi.enums.GenderType;
import com.shorna.nutritionistapi.enums.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Document("NutritionistUser")
public record NutritionistUser(
    @Id String id,
    @NotNull @Email String email,
    @NotNull String password,
    @NotNull LocalDate dateOfBirth,
    @NotNull GenderType gender,
    @NotNull List<String> roles,
    @NotNull Boolean accountNonExpired,
    @NotNull Boolean accountNonLocked,
    @NotNull Boolean credentialsNonExpired,
    @NotNull Boolean enabled
        ){}
