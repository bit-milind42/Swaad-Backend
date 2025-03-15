// package com.milind;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @SpringBootApplication
// @ComponentScan(basePackages = {"com.milind", "com.controller"})
// @EntityScan("com.milind.model")
// @EnableJpaRepositories({"com.milind.repository"})
// // @SpringBootApplication
// public class OnlineFoodCateringApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(OnlineFoodCateringApplication.class, args);
// 	}
// }





// package com.milind;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @SpringBootApplication
// @ComponentScan(basePackages = {"com.milind", "com.controller","com.service", "com.repository"})
// @EntityScan("com.milind.model")
// @EnableJpaRepositories("com.milind.repository")
// public class OnlineFoodCateringApplication {
//     public static void main(String[] args) {
//         SpringApplication.run(OnlineFoodCateringApplication.class, args);
//     }
// }



package com.milind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.milind", "com.controller", "com.service", "com.repository"})
@EntityScan("com.milind.model")
@EnableJpaRepositories("com.repository")  // Changed from com.milind.repository to match your actual repository package
public class OnlineFoodCateringApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineFoodCateringApplication.class, args);
    }
}