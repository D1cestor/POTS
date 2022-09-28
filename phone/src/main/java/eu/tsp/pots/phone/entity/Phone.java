package eu.tsp.pots.phone.entity;

/**
 * This class describes the Phone entity.
 */

public class Phone extends Device
{
    public enum State{SILENT, BUSY, READY};
    private String number;
    private String gateway;
    private State state;
}
