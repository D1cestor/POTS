package eu.tsp.pots.switch1.runner;

import eu.tsp.pots.switch1.config.SwitchConfig;
import eu.tsp.pots.switch1.thread.MessageThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class InitRunner implements ApplicationRunner
{
    @Autowired
    SwitchConfig config;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        new MessageThread(config.getMessagePort()).start();
    }
}
