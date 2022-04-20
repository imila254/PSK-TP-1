package mif.vu.lt.psktp1.alternatives;

import javax.enterprise.context.Dependent;

@Dependent
public class ArtistMessage implements Message{

    @Override
    public String WriteMessage() {
        return "Album created";
    }

    public ArtistMessage(){
    }
}
