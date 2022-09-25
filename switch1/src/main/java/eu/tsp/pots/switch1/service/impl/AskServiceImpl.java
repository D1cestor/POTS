package eu.tsp.pots.switch1.service.impl;

import eu.tsp.pots.switch1.config.SwitchConfig;
import eu.tsp.pots.switch1.entity.AskResponse;
import eu.tsp.pots.switch1.entity.Request;
import eu.tsp.pots.switch1.service.AskService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service("AskService")
public class AskServiceImpl implements AskService
{
    private SwitchConfig config;
    private RestTemplate restTemplate;

    @Autowired
    public AskServiceImpl(SwitchConfig config, RestTemplate restTemplate)
    {
        this.config = config;
        this.restTemplate = restTemplate;
    }
    @Override
    public AskResponse forward(Request request) throws IOException
    {
        var sourceIp = request.getSourceIp();
        var dest = request.getDestination();
        boolean flag = false;
        var phones = config.getPhones();
        if (phones.containsKey(dest))
        {
            flag = true;
            dest = phones.get(dest);
        }
        if (!flag) dest = config.getSwitches().get(0); // go to the default gateway
        request.setSourceIp("http://127.0.0.1:" + config.getPort());
        AskResponse res = restTemplate.postForObject(dest + "/ask", request, AskResponse.class);
        if (res == null)
        {
            res = (AskResponse) request;
            res.setResponse(false);
        }
        if (res.getResponse())
        {
            var map = config.getMap();
            map.put(request.getSource(), dest);
            System.out.println("table updates: " + request.getSource() + " -> " + dest);
            map.put(request.getDestination(), sourceIp);
            System.out.println("table updates:" +  request.getDestination() + "-> " + sourceIp);
        }
        return res;
    }
}
