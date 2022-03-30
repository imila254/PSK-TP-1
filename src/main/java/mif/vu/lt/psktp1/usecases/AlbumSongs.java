package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Album;
import mif.vu.lt.psktp1.entities.Artist;
import mif.vu.lt.psktp1.entities.Song;
import mif.vu.lt.psktp1.persistence.AlbumsDAO;
import mif.vu.lt.psktp1.persistence.ArtistsDAO;
import mif.vu.lt.psktp1.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class AlbumSongs implements Serializable {

    @Inject
    private SongsDAO songsDAO;

    @Inject
    private AlbumsDAO albumsDAO;

    @Getter @Setter
    private Album album;

    @Getter @Setter
    private Song songToCreate = new Song();

    @Getter @Setter
    private List<Artist> artistList;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer albumId = Integer.parseInt(requestParameters.get("albumId"));
        this.album = albumsDAO.findOne(albumId);
    }

    @Transactional
    public void createSong(){
        songToCreate.setAlbum(this.album);
        songToCreate.setArtist_id(this.album.getArtist().getArtist_id());
        songsDAO.persist(songToCreate);
    }
}
