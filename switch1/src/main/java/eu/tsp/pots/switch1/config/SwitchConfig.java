package eu.tsp.pots.switch1.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "config")
public class SwitchConfig
{
    private HashMap<String, String> phones;
    private ArrayList<String> switches;
    private HashMap<String, String> map;
    private int messagePort;
    @Value("${server.port}")
    private int port;

    @PostConstruct
    public void init()
    {
        map = new HashMap<>();
    }
}
