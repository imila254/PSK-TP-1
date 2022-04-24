package mif.vu.lt.psktp1.rest;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Album;
import mif.vu.lt.psktp1.entities.Artist;
import mif.vu.lt.psktp1.persistence.AlbumsDAO;
import mif.vu.lt.psktp1.rest.contracts.AlbumDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import java.net.URI;

@ApplicationScoped
@Path("/albums")
public class AlbumsController {

    @Inject
    @Setter @Getter
    private AlbumsDAO albumsDAO;

    @Getter @Setter
    private Artist artist;

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/albums/1
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id){
        Album album = albumsDAO.findOne(id);

        if (album == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumName(album.getAlbum_name());
        albumDto.setSongsNumber(album.getSongs_number());
        albumDto.setArtistName(album.getArtist().getArtist_name());

        return Response.ok(albumDto).build();
    }

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/albums/1
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer albumId, AlbumDto albumDto){
        try {
            Album existingAlbum = albumsDAO.findOne(albumId);
            if (existingAlbum == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingAlbum.setAlbum_name(albumDto.getAlbumName());
            existingAlbum.setSongs_number(albumDto.getSongsNumber());
            existingAlbum.setAlbum_artist(albumDto.getArtistName());
            albumsDAO.update(existingAlbum);
            return Response.ok().build();
        } catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
