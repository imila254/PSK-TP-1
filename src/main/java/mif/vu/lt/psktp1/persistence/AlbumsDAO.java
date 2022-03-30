package mif.vu.lt.psktp1.persistence;

import mif.vu.lt.psktp1.entities.Album;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AlbumsDAO {

    @Inject
    private EntityManager em;

    public void persist(Album album) {this.em.persist(album);}

    public Album findOne(Integer album_id) {return em.find(Album.class, album_id);}

    public List<Album> findAll() {return em.createNamedQuery("Album.findAll",Album.class).getResultList();} //

    public Album update(Album album) {return  em.merge(album);}
}
