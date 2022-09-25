package eu.tsp.pots.phone.service.impl;

import eu.tsp.pots.phone.config.PhoneConfig;
import eu.tsp.pots.phone.entity.AskResponse;
import eu.tsp.pots.phone.entity.Request;
import eu.tsp.pots.phone.service.DialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("DialService")
public class DialServiceImpl implements DialService
{
    private PhoneConfig config;

    private RestTemplate restTemplate;

    @Autowired
    public DialServiceImpl(PhoneConfig config, RestTemplate restTemplate)
    {
        this.config = config;
        this.restTemplate = restTemplate;
    }


    @Override
    public boolean dial(String target)
    {
        Request request = new Request();
        request.setSource(this.config.getNumber());
        request.setDestination(target);
        request.setSourceIp("http://127.0.0.1:" + config.getPort());
        AskResponse res = restTemplate.postForObject(config.getSwitch1() + "/ask", request, AskResponse.class);
        if (res == null)
            return false;
        return res.getResponse();
    }
}
