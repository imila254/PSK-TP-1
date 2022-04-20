package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.mybatis.model.Artist;
import mif.vu.lt.psktp1.mybatis.model.Album;
import mif.vu.lt.psktp1.mybatis.dao.AlbumMapper;
import mif.vu.lt.psktp1.mybatis.dao.ArtistMapper;
import mif.vu.lt.psktp1.qualifiers.AlbumTypeProcessor;
import mif.vu.lt.psktp1.qualifiers.Vinyl;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class ArtistAlbumsMyBatis {

    @Inject
    ArtistMapper artistMapper;

    @Inject
    AlbumMapper albumMapper;

    @Inject @Vinyl
    AlbumTypeProcessor albumTypeProcessorVinyl;

    @Getter @Setter
    private Artist artist;

    @Getter
    private List<Album> albumList;

    @Getter @Setter
    private Album albumToCreate = new Album();

    @PostConstruct
    public void init(){
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));

        this.artist = artistMapper.selectByPrimaryKey(artistId);

    }

    public void loadAllAlbums(){this.albumList = albumMapper.selectAll();}

    @Transactional
    public void createAlbum(){
        albumToCreate.setAlbumArtist(this.artist.getArtistName());
        System.out.println(albumTypeProcessorVinyl.AlbumType());
    }

}
