package eu.tsp.pots.phone.controller;

import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lift")
public class LiftController
{
    private PhoneConfig config;

    @Autowired
    public LiftController(PhoneConfig config)
    {
        this.config = config;
    }


    @RequestMapping(method = RequestMethod.GET)
    public void lift (@RequestBody Request request)
    {
    }
}
