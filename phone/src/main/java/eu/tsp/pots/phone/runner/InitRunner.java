package eu.tsp.pots.phone.runner;

import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.thread.CallThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class InitRunner implements ApplicationRunner{
    @Autowired
    PhoneConfig config;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        new CallThread(config.getSwitchPort()).start();
    }
}
