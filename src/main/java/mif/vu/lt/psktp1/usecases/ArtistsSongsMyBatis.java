package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import mif.vu.lt.psktp1.mybatis.dao.ArtistMapper;
import mif.vu.lt.psktp1.mybatis.dao.SongMapper;
import mif.vu.lt.psktp1.mybatis.model.Artist;
import mif.vu.lt.psktp1.mybatis.model.Song;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ArtistsSongsMyBatis {

    @Inject
    ArtistMapper artistMapper;

    @Inject
    SongMapper songMapper;


    @Getter
    private List<Song> songList;

    @Getter
    private List<Artist> artistList;

    @PostConstruct
    public void init(){this.loadAllSongArtists();}

    private void loadAllArtistSongs(){
        this.songList = artistMapper.selectSongsForArtist(1);
    }

    private void loadAllSongArtists(){this.artistList = songMapper.selectArtistsForSong(1);}

}
