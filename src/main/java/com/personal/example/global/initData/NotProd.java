package com.personal.example.global.initData;
import com.personal.example.domain.data.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev"})
public class NotProd {
    @Bean
    CommandLineRunner initData(DataService dataService) {
        return args -> {
            dataService.create("내용 1");
            dataService.create("내용 2");
            dataService.create("내용 3");
            dataService.create("내용 4");
            dataService.create("내용 5");
        };
    }
}