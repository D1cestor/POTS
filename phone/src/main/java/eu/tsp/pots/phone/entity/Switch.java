package eu.tsp.pots.phone.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;


@Component
public class Switch extends Device
{
    private ArrayList<String> phones;
    private ArrayList<String> switches;
    private HashMap<String, String> map;
}
