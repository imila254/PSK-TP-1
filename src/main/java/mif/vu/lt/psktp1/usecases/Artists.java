package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Artist;
import mif.vu.lt.psktp1.interceptors.LoggedInvocation;
import mif.vu.lt.psktp1.persistence.ArtistsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Artists {

    @Inject
    private ArtistsDAO artistsDAO;

    @Getter @Setter
    private Artist artistToCreate = new Artist();

    @Getter
    private List<Artist> allArtists;

    @PostConstruct
    public void init() { loadAllArtists();}

    @Transactional
    @LoggedInvocation
    public void createArtist(){ this.artistsDAO.persist(artistToCreate);}

    private void loadAllArtists(){
        this.allArtists = artistsDAO.loadAll();
    }


}
