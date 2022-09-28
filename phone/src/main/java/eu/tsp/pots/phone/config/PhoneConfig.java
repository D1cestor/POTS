package eu.tsp.pots.phone.config;


import eu.tsp.pots.phone.entity.Phone;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "config")
public class PhoneConfig
{
    private String number;
    private String switch1;
    private Phone.State state = Phone.State.SILENT;
    @Value("${server.port}")
    private int port;
}
