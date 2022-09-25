package eu.tsp.pots.switch1.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Request implements Serializable
{
    private String source;

    private String destination;

    private int id;

    private String sourceIp;
}
