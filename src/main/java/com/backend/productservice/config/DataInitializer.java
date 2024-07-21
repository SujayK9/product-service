package com.backend.productservice.config;


import com.backend.productservice.model.Category;
import com.backend.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

//    @Bean
//    public CommandLineRunner initializeData() {
//        return args -> {
//            if (categoryRepository.count() == 0) {
//                categoryRepository.save(new Category("electronic"));
//                categoryRepository.save(new Category("furniture"));
//                categoryRepository.save(new Category("clothing"));
//            }
//        };
//    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("electronic"));
            categoryRepository.save(new Category("furniture"));
            categoryRepository.save(new Category("clothing"));
        }
    }
}

