package eu.tsp.pots.switch1.service.impl;

import eu.tsp.pots.switch1.config.SwitchConfig;
import eu.tsp.pots.switch1.entity.DropResponse;
import eu.tsp.pots.switch1.entity.Request;
import eu.tsp.pots.switch1.service.DropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service("DropService")
public class DropServiceImpl implements DropService
{
    private SwitchConfig config;
    private RestTemplate restTemplate;

    @Autowired
    public DropServiceImpl(SwitchConfig config, RestTemplate restTemplate)
    {
        this.config = config;
        this.restTemplate = restTemplate;
    }
    @Override
    public DropResponse forward(Request request) throws IOException
    {
        var sourceIp = request.getSourceIp();
        var src = request.getSource();
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
        System.out.println("URI :  " + sourceIp);
        DropResponse res = restTemplate.getForObject(dest + "/drop", DropResponse.class) ;
        DropResponse resSRC = restTemplate.getForObject( sourceIp + "/drop", DropResponse.class) ;
        if (res == null  || resSRC == null  )
        {
            res = (DropResponse) request;
            res.setResponse(false);
        }
        if (res.getResponse() && resSRC.getResponse())
        {
         var map = config.getMap();
                     map.remove(request.getSource());
                     System.out.println("table updates: " + request.getSource() + " -> " + dest +  " removed") ;
                     map.remove(request.getDestination());
                     System.out.println("table updates:" +  request.getDestination() + "-> " + sourceIp + " removed") ;
            }
        return res;
    }
}
