package eu.tsp.pots.phone.entity;


public class Phone extends Device
{
    public enum State{SILENT, BUSY, READY};
    private String number;
    private String gateway;
    private State state;
}
