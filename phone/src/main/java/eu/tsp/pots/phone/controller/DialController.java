package eu.tsp.pots.phone.controller;


import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.entity.Phone;
import eu.tsp.pots.phone.service.DialService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dial")
public class DialController
{
    private DialService dialService;
    private PhoneConfig config;
    public DialController(DialService dialService, PhoneConfig config)
    {
        this.dialService = dialService;
        this.config = config;
    }


    @RequestMapping(method = RequestMethod.POST)
    public void dial(@RequestBody String target) {
        if (config.getState().equals(Phone.State.READY)) {
            System.out.println("Try to call " + target + "!");
            boolean res = dialService.dial(target);
            if (res)
                System.out.println("Connected to " + target + "!");
            else
                System.out.println("Target not reachable!");
        }
        else{
            System.out.println("You have to lift the phone first");
        }
    }

}
