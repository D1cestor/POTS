package eu.tsp.pots.phone;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PhoneApplication implements ApplicationRunner
{

    public static void main(String[] args)
    {
        SpringApplication.run(PhoneApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
