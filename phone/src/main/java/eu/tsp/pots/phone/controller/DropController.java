package eu.tsp.pots.phone.controller;

import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.entity.AskResponse;
import eu.tsp.pots.phone.entity.DropResponse;
import eu.tsp.pots.phone.entity.Phone;
import eu.tsp.pots.phone.entity.Request;
import eu.tsp.pots.phone.service.DialService;
import eu.tsp.pots.phone.service.DropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("drop")
public class DropController
{
    private PhoneConfig config;

    private DropService dropService;

    @Autowired
    public DropController(PhoneConfig config, DropService dropService)
    {
        this.config = config;
        this.dropService = dropService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public void drop (@RequestBody String target)
    {
        dropService.drop(target);
    }

    @RequestMapping(method = RequestMethod.GET)
    public DropResponse drop()
    {
        System.out.println("connexion with " + config.getNumber() + " closed");
        DropResponse response = new DropResponse();
        response.setResponse(true);
        config.setState(Phone.State.READY);
        return response;
    }
}
