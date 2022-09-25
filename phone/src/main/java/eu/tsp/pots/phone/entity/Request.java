package eu.tsp.pots.phone.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Request implements Serializable
{
    private String source;

    private String destination;

    private String sourceIp;

    private int id;
}
