package eu.tsp.pots.switch1.service;

import eu.tsp.pots.switch1.entity.AskResponse;
import eu.tsp.pots.switch1.entity.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;


public interface AskService
{
    public AskResponse forward(Request request) throws IOException;
}
