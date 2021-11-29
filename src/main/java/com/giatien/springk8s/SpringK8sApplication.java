package com.giatien.springk8s;

import com.giatien.springk8s.common.audit.AuditorAwareImpl;
import com.giatien.springk8s.model.Fruit;
import com.giatien.springk8s.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableSwagger2
public class SpringK8sApplication {

    @Autowired
    private FruitRepository fruitRepository;

    private final Logger logger = LoggerFactory.getLogger(SpringK8sApplication.class);

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }




    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        List<Fruit> allFruit = this.fruitRepository.findAll();
        logger.info("Number of fruit: " + allFruit.size());

        Fruit fruit = new Fruit();
        fruit.setName("Apple");
        fruit.setSeason("Spring");
        logger.info("Saving new fruit...");
        this.fruitRepository.save(fruit);
        allFruit = this.fruitRepository.findAll();
        logger.info("Number of fruit: " + allFruit.size());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringK8sApplication.class, args);
    }

}
