package mif.vu.lt.psktp1.alternatives;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

@Dependent
@Alternative
public class AltArtistMessage implements Message{
    @Override
    public String WriteMessage() {
        return "Alternative album created";
    }

    public AltArtistMessage(){

    }
}
