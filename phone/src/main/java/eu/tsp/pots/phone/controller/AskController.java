package eu.tsp.pots.phone.controller;

import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.entity.AskResponse;
import eu.tsp.pots.phone.entity.Phone;
import eu.tsp.pots.phone.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public AskResponse ask(@RequestBody Request request)
    {
        boolean ans = config.getState() == Phone.State.READY;
        AskResponse response = new AskResponse();
        response.setSource(request.getDestination());
        response.setDestination(request.getSource());
        response.setResponse(ans);
        System.out.println("Get a call from " + request.getSource());
        return response;
    }
}
