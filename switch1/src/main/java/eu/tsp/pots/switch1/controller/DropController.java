package eu.tsp.pots.switch1.controller;


import eu.tsp.pots.switch1.entity.AskResponse;
import eu.tsp.pots.switch1.entity.DropResponse;
import eu.tsp.pots.switch1.entity.Request;
import eu.tsp.pots.switch1.service.DropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("drop")
public class DropController
{
    private DropService dropService;

    @Autowired
    public DropController(DropService dropService)
    {
        this.dropService = dropService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public DropResponse drop(@RequestBody Request request, HttpServletRequest hRequest)
    {
        try
        {
            return dropService.forward(request);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
