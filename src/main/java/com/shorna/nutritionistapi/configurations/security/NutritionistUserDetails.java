package com.shorna.nutritionistapi.configurations.security;

import com.shorna.nutritionistapi.models.NutritionistUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NutritionistUserDetails  implements UserDetails {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    NutritionistUser nutritionistUser;

    public NutritionistUserDetails() {
    }

    public NutritionistUserDetails(NutritionistUser nutritionistUser) {
        nutritionistUser = new NutritionistUser(nutritionistUser.id(),
                nutritionistUser.email(),
                nutritionistUser.password(),
                nutritionistUser.dateOfBirth(),
                nutritionistUser.gender(),
                nutritionistUser.roles(),
                nutritionistUser.accountNonExpired(),
                nutritionistUser.accountNonLocked(),
                nutritionistUser.credentialsNonExpired(),
                nutritionistUser.enabled(), null, null, null, null, null, null);
        for(String authority: nutritionistUser.roles()){
            this.authorities.add(new SimpleGrantedAuthority(authority));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return nutritionistUser.password();
    }

    @Override
    public String getUsername() {
        return nutritionistUser.email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return nutritionistUser.accountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return nutritionistUser.accountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return nutritionistUser.credentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return nutritionistUser.enabled();
    }
}
