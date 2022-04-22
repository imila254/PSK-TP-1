package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Album;
import mif.vu.lt.psktp1.entities.Artist;
import mif.vu.lt.psktp1.interceptors.LoggedInvocation;
import mif.vu.lt.psktp1.persistence.AlbumsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateAlbumDetails implements Serializable {

    private Album album;

    @Inject
    private AlbumsDAO albumsDAO;

    @Inject
    private EntityManager em;


    @PostConstruct
    private void init(){
        System.out.println("------------UpdateAlbumDetails INIT CALLED------------");
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer albumId = Integer.parseInt(requestParameters.get("albumId"));
        this.album = albumsDAO.findOne(albumId);
    }

    @Transactional
    @LoggedInvocation
    public String updateAlbumSongsNumber(){
        try{
            albumsDAO.update(this.album);

        } catch (OptimisticLockException e){
            System.out.println(this.album.getAlbum_id());
            return "/albumDetails.xhtml?faces-redirect=true&albumId=" + this.album.getAlbum_id() + "&error=optimistic-lock-exception";
            //return "/albumDetails.xhtml?faces-redirect=true&error=optimistic-lock-exception";
        }
        return "albums.xhtml?artistId=" + this.album.getArtist().getArtist_id() + "&faces-redirect=true";
    }
}
