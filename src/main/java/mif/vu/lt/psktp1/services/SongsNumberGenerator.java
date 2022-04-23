package mif.vu.lt.psktp1.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class SongsNumberGenerator implements Serializable {

    public Integer generateSongNumber(){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
        }
        Integer generatedSongNumber = new Random().nextInt(25);
        return generatedSongNumber;
    }

}
