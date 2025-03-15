// package com.milind.config;

// import java.security.AuthProvider;
// import java.util.Collections;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.reactive.CorsConfigurationSource;

// import java.util.*;
// import jakarta.servlet.http.HttpServletRequest;

// @Configuration
// @EnableWebSecurity

// public class AppConfig {

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//         http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//         .authorizeHttpRequests(Authorize -> Authorize
//         .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER","ADMIN")
//         .requestMatchers("/api/**").authenticated()
//         .anyRequest().permitAll()
//         ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//         .csrf(csrf-> csrf.disable())
//         .cors(cors-> cors.configurationSource(corsConfigurationSource()));
//         return null;
//     }

//     private CorsConfiguration corsConfigurationSource() {
//         return new CorsConfigurationSource() {

//             @Override
//             public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                 CorsConfiguration cfg = new CorsConfiguration();
//                 cfg.setAllowedOrigins(Arrays.asList(
//                     "https://milind-food.vercel.app/",
//                     "http://localhost:3000"
//                 ));
//                 cfg.setAllowedMethods(Collections.singletonList("*"));
//                 cfg.setAllowCredentials(true);
//                 cfg.setAllowedHeaders(Collections.singletonList("*"));
//                 cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                 cfg.setMaxAge(3600L);
//             return cfg;
//             }

//         };
//         // ; above
//     }
//     @Bean
//     PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }

// }



// package com.milind.config;

// import java.util.Arrays;
// import java.util.Collections;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class AppConfig {

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()));
//         return http.build(); // Return the built security chain
//     }

//     private CorsConfigurationSource corsConfigurationSource() {
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration cfg = new CorsConfiguration();
//         cfg.setAllowedOrigins(Arrays.asList(
//             // "https://milind-food.vercel.app/",
//             "http://localhost:5173"
//         ));
//         cfg.setAllowedMethods(Collections.singletonList("*"));
//         cfg.setAllowCredentials(true);
//         cfg.setAllowedHeaders(Collections.singletonList("*"));
//         cfg.setExposedHeaders(Arrays.asList("Authorization"));
//         cfg.setMaxAge(3600L);
//         source.registerCorsConfiguration("/**", cfg);
//         return source; // Return the proper CorsConfigurationSource
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }





// package com.milind.config;

// import java.util.Arrays;
// import java.util.Collections;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class AppConfig {

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource())); // ✅ Uses a @Bean now
//         return http.build();
//     }

//     @Bean // ✅ Add @Bean annotation so Spring recognizes it globally
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration cfg = new CorsConfiguration();
//         cfg.setAllowedOrigins(Arrays.asList(
//             "http://localhost:5173"
//             // "https://milind-food.vercel.app/" // Uncomment for production
//         ));
//         cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         cfg.setAllowCredentials(true);
//         cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//         cfg.setExposedHeaders(Arrays.asList("Authorization"));
//         cfg.setMaxAge(3600L);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", cfg);
//         return source;
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }






// package com.milind.config;

// import java.util.Arrays;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class AppConfig {

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/admin/**").hasAnyAuthority("RESTAURANT_OWNER", "ADMIN") // ✅ Use hasAnyAuthority()
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class) // Ensure JwtTokenValidator exists
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()));
//         return http.build();
//     }

//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration cfg = new CorsConfiguration();
//         cfg.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Can be fetched dynamically
//         cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         cfg.setAllowCredentials(true);
//         cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//         cfg.setExposedHeaders(Arrays.asList("Authorization"));
//         cfg.setMaxAge(3600L);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", cfg);
//         return source;
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }






// package com.milind.config;

// import java.util.Arrays;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class AppConfig {

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/signup").permitAll()  // ✅ Allow unauthenticated access to signup
//                 .requestMatchers("/api/admin/**").hasAnyAuthority("RESTAURANT_OWNER", "ADMIN") 
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class) // Ensure JwtTokenValidator exists
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        
//         return http.build();
//     }

//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration cfg = new CorsConfiguration();
//         cfg.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // ✅ Adjust if needed
//         cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         cfg.setAllowCredentials(true);
//         cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//         cfg.setExposedHeaders(Arrays.asList("Authorization"));
//         cfg.setMaxAge(3600L);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", cfg);
//         return source;
//     }

//     @Bean
//     PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }



package com.milind.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/signup", "/auth/signin").permitAll()  // Allow both auth endpoints
                .requestMatchers("/api/admin/**").hasAnyAuthority("RESTAURANT_OWNER", "ADMIN") 
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            )
            .addFilterBefore(jwtTokenValidator(), BasicAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        
        return http.build();
    }

    @Bean
    public JwtTokenValidator jwtTokenValidator() {
        return new JwtTokenValidator();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(Arrays.asList("http://localhost:5173")); 
        cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cfg.setAllowCredentials(true);
        cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        cfg.setExposedHeaders(Arrays.asList("Authorization"));
        cfg.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}