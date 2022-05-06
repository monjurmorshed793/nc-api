package com.shorna.nutritionistapi.configurations.security;

import com.shorna.nutritionistapi.repositories.NutritionistUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NutritionistUserDetailsService implements UserDetailsService {
    private final NutritionistUserRepository nutritionistUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new NutritionistUserDetails(nutritionistUserRepository.getNutritionistUserByEmail(username));
    }


}
