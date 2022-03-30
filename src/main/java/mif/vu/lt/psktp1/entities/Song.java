package mif.vu.lt.psktp1.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Song.findAll", query = "select s from Song as s ")
})
@Table(name = "SONG")
@Getter @Setter
public class Song implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer song_id;

//    @Column(name = "ALBUM")
//    private Integer album_id;

    @Column(name = "ARTIST")
    private Integer artist_id;

    @Size(max = 50)
    @Column(name = "SONG_NAME", nullable = false, unique = true)
    private String song_name;

    @ManyToMany
    @JoinTable(name = "ARTISTS_SONGS")
    @JoinColumn(name = "ARTIST_ID")
    private List<Artist> artistList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

    public Song(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(song_id, song.song_id) && Objects.equals(artist_id, song.artist_id) && Objects.equals(song_name, song.song_name) && album.equals(song.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(song_id, artist_id, song_name, album);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Song song = (Song) o;
//        return song_id.equals(song.song_id) && album_id.equals(song.album_id) && artist_id.equals(song.artist_id) && song_name.equals(song.song_name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(song_id, album_id, artist_id, song_name);
//    }
}
