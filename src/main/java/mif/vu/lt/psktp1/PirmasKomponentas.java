package mif.vu.lt.psktp1;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped //@RequestScoped
public class PirmasKomponentas implements Serializable {

    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString();

    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}