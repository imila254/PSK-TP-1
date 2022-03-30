package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Artist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ArtistsDAO {

    @Inject
    private EntityManager em;

    public List<Artist> loadAll() {return em.createNamedQuery("Artist.findAll", Artist.class).getResultList();}

    public void setEm(EntityManager em) {this.em = em;}

    public void persist(Artist artist) {this.em.persist(artist);}

    public Artist findOne(Integer artist_id) {return em.find(Artist.class, artist_id);}
}
