package eu.tsp.pots.phone.service.impl;

import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.entity.DropResponse;
import eu.tsp.pots.phone.entity.Request;
import eu.tsp.pots.phone.service.DropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("DropService")
public class DropServiceImpl implements DropService
{
    private PhoneConfig config;

    private RestTemplate restTemplate;

    @Autowired
    public DropServiceImpl(PhoneConfig config, RestTemplate restTemplate)
    {
        this.config = config;
        this.restTemplate = restTemplate;
    }


    @Override
    public boolean drop(String target) {
        Request request = new Request();
        request.setSource(this.config.getNumber());
        request.setDestination(target);
        request.setSourceIp("http://127.0.0.1:" + config.getPort());
        DropResponse res = restTemplate.postForObject(config.getSwitch1() + "/drop", request, DropResponse.class);
        if (res == null)
            return false;
        return res.getResponse();
    }



}
