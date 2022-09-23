package eu.tsp.pots.phone.entity;



public class Phone extends Device
{
    enum State{SILENT, BUSY, READY};
    private String number;
    private String gateway;
    private State state;
}
