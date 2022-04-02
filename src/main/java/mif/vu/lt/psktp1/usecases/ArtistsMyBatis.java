package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Album;
import mif.vu.lt.psktp1.entities.Song;
import mif.vu.lt.psktp1.mybatis.dao.ArtistMapper;
import mif.vu.lt.psktp1.mybatis.model.Artist;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ArtistsMyBatis {
    @Inject
    ArtistMapper artistMapper;

    @Getter
    private List<Artist> artistsList;

    @Getter @Setter
    private Artist artistsToCreate = new Artist();

    @PostConstruct
    public void init(){this.loadAllArtists();}

    private void loadAllArtists(){
        this.artistsList = artistMapper.selectAll();
    }



    @Transactional
    public String createArtist(){
        artistMapper.insert(artistsToCreate);
        return "/myBatis/artists?faces-redirect=true";
    }
}
