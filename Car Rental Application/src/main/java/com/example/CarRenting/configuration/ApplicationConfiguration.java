package com.example.CarRenting.configuration;

import com.example.CarRenting.model.Administrator;
import com.example.CarRenting.model.Employee;
import com.example.CarRenting.model.Client;
import com.example.CarRenting.persistance.AdministratorRepository;
import com.example.CarRenting.persistance.ClientRepository;
import com.example.CarRenting.persistance.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    private final ClientRepository clientRepository;
    private final AdministratorRepository administratorRepository;
    private final EmployeeRepository employeeRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Employee employee = employeeRepository.findByUsername(username).orElse(null);
                Client client = clientRepository.findByUsername(username).orElse(null);
                Administrator administrator = administratorRepository.findByUsername(username).orElse(null);
                if (employee == null && client == null && administrator != null)
                    return administrator;
                if (employee != null && client == null && administrator == null)
                    return employee;
                if (employee == null && client != null && administrator == null)
                    return client;
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
