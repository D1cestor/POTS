package eu.tsp.pots.switch1.controller;


import eu.tsp.pots.switch1.entity.AskResponse;
import eu.tsp.pots.switch1.entity.Request;
import eu.tsp.pots.switch1.service.AskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("ask")
public class AskController
{
    private AskService askService;

    @Autowired
    public AskController(AskService askService)
    {
        this.askService = askService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public AskResponse ask(@RequestBody Request request,  HttpServletRequest hRequest)
    {
        try
        {
            return askService.forward(request);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
