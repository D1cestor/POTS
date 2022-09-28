package eu.tsp.pots.phone.controller;

import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.entity.AskResponse;
import eu.tsp.pots.phone.entity.Phone;
import eu.tsp.pots.phone.entity.Request;
import eu.tsp.pots.phone.runner.InitRunner;
import eu.tsp.pots.phone.thread.CallThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.SocketException;

@RestController
@RequestMapping("ask")
public class AskController
{
    private PhoneConfig config;

    @Autowired
    public AskController(PhoneConfig config)
    {
        this.config = config;
    }


    @RequestMapping(method = RequestMethod.POST)
    public AskResponse ask(@RequestBody Request request) throws SocketException {
        boolean ans = config.getState() == Phone.State.READY;
        AskResponse response = new AskResponse();
        response.setSource(request.getDestination());
        response.setDestination(request.getSource());
        response.setResponse(ans);
        response.setSourcePort(config.getPort());
        CallThread ct = new CallThread(config.getPort());
        ct.run();
        System.out.println("Get a call from " + request.getSource()+ " new thread");
        return response;
    }
}
