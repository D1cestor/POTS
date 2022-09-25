package eu.tsp.pots.phone;

import eu.tsp.pots.phone.config.PhoneConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhoneApplicationTests {

	@Autowired
	private PhoneConfig config;

	@Test
	void contextLoads() {
		System.out.println(config.getNumber());
		System.out.println(config.getSwitch1());
	}

}
