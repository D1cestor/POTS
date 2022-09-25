package eu.tsp.pots.phone.controller;


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
    public DialController(DialService dialService)
    {
        this.dialService = dialService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public void dial(@RequestBody String target)
    {
        boolean res = dialService.dial(target);
        if (res)
            System.out.println("Connected to " + target + "!");
        else
            System.out.println("Target not reachable!");
    }
}
