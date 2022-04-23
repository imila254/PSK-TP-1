package mif.vu.lt.psktp1.usecases;

import mif.vu.lt.psktp1.interceptors.LoggedInvocation;
import mif.vu.lt.psktp1.services.SongsNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateAlbumSongsNumber implements Serializable {

    @Inject
    SongsNumberGenerator songsNumberGenerator;

    private CompletableFuture<Integer> songNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewSongsNumber(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        songNumberGenerationTask = CompletableFuture.supplyAsync(() -> songsNumberGenerator.generateSongNumber());

        return "/albumDetails.xhtml?faces-redirect=true&albumId=" + requestParameters.get("albumId");
    }

    public boolean isSongNumberGenerationRunning(){
        return songNumberGenerationTask != null && !songNumberGenerationTask.isDone();
    }

    public String getSongNumberGeneratorStatus() throws ExecutionException, InterruptedException {
        if (songNumberGenerationTask == null) {
            return null;
        } else if (isSongNumberGenerationRunning()){
            return "Song Number Generator in progress";
        }
        return "Suggested Song Number: " + songNumberGenerationTask.get();
    }
}
