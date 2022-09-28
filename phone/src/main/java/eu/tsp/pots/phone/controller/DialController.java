package eu.tsp.pots.phone.controller;


import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.runner.InitRunner;
import eu.tsp.pots.phone.service.DialService;
import eu.tsp.pots.phone.thread.CallThread;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.SocketException;

@RestController
@RequestMapping("dial")
public class DialController
{
    private DialService dialService;
    public DialController(DialService dialService)
    {
        this.dialService = dialService;
    }

    private PhoneConfig config;
    @RequestMapping(method = RequestMethod.POST)
    public void dial(@RequestBody String target) throws SocketException {
        boolean res = dialService.dial(target);

        if (res){
            CallThread ct = new CallThread(config.getPort());
            ct.run();
            System.out.println("Connected to " + target + "! And new thread started");
        }
        else{
            System.out.println("Target not reachable!");
        }
    }
}
