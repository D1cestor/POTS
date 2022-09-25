package eu.tsp.pots.phone.entity;

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
