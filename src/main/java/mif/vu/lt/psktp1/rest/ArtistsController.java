package mif.vu.lt.psktp1.rest;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Album;
import mif.vu.lt.psktp1.entities.Artist;
import mif.vu.lt.psktp1.persistence.AlbumsDAO;
import mif.vu.lt.psktp1.persistence.ArtistsDAO;
import mif.vu.lt.psktp1.rest.contracts.AlbumDto;
import mif.vu.lt.psktp1.rest.contracts.ArtistDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@ApplicationScoped
@Path("/artists")
public class ArtistsController {

    @Inject
    @Setter @Getter
    private ArtistsDAO artistsDAO;

    @Inject
    private EntityManager em;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id){

        Artist artist = artistsDAO.findOne(id);

        if (artist == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ArtistDto artistDto = new ArtistDto();
        artistDto.setArtistName(artist.getArtist_name());


        return Response.ok(artistDto).build();
    }

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/artists
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ArtistDto artistDto){

        try{
            if (artistDto == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            Artist artistToCreate = new Artist();
            artistToCreate.setArtist_name(artistDto.getArtistName());
            artistsDAO.persist(artistToCreate);

            URI location = UriBuilder.fromResource(ArtistsController.class)
                    .path("/{id}")
                    .resolveTemplate("id", artistToCreate.getArtist_id())
                    .build();

            return Response.created(location).build();
        } catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }

    }


}
