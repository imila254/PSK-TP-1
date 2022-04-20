package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.alternatives.Message;
import mif.vu.lt.psktp1.entities.Album;
import mif.vu.lt.psktp1.entities.Artist;
import mif.vu.lt.psktp1.persistence.AlbumsDAO;
import mif.vu.lt.psktp1.persistence.ArtistsDAO;
import mif.vu.lt.psktp1.qualifiers.AlbumTypeProcessor;
import mif.vu.lt.psktp1.qualifiers.CD;
import mif.vu.lt.psktp1.qualifiers.CDAlbumType;
import mif.vu.lt.psktp1.qualifiers.Vinyl;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class ArtistAlbums implements Serializable {

    @Inject
    private AlbumsDAO albumsDAO;

    @Inject
    private ArtistsDAO artistsDAO;

    @Inject
    private Message message;

    @Inject @CD
    AlbumTypeProcessor albumTypeProcessor;

    @Inject @Any
    private CDAlbumType cdAlbumType;

    @Getter @Setter
    private Artist artist;

    @Getter @Setter
    private Album albumToCreate = new Album();

    @PostConstruct
    public void init(){
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistsDAO.findOne(artistId);
    }

    @Transactional
    public void createAlbum(){
        albumToCreate.setArtist(this.artist);
        albumToCreate.setAlbum_artist(this.artist.getArtist_name());
        albumsDAO.persist(albumToCreate);
        System.out.println(message.WriteMessage());
        System.out.println(albumTypeProcessor.AlbumType());
        System.out.println(cdAlbumType.AlbumType());
    }

}
