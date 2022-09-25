package eu.tsp.pots.switch1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AskResponse extends Request
{
    private boolean response;

    public boolean getResponse()
    {
        return this.response;
    }
}
