package eu.tsp.pots.switch1.service;


import eu.tsp.pots.switch1.entity.DropResponse;
import eu.tsp.pots.switch1.entity.Request;

import java.io.IOException;


public interface DropService
{
    public DropResponse forward(Request request) throws IOException;
}
