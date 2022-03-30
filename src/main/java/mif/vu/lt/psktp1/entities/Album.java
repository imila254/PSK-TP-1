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
        @NamedQuery(name = "Album.findAll", query = "select alb from Album as alb ")
})
@Table (name = "ALBUM")
@Getter @Setter
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer album_id;

    @Size(max = 50)
    @Column(name = "ALBUM_NAME", nullable = false, unique = true)
    private String album_name;

    @Column(name = "ALBUM_ARTIST")
    private String album_artist;

    @Column(name = "SONGS_NUMBER")
    private Integer songs_number;

    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Song> songList = new ArrayList<>();

    public Album(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return album_id.equals(album.album_id) && album_name.equals(album.album_name) && album_artist.equals(album.album_artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(album_id, album_name, album_artist);
    }
}
