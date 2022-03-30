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
        @NamedQuery(name = "Artist.findAll", query = "select art from Artist as art ")
})
@Table(name = "ARTIST")
@Getter @Setter
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer artist_id;

    @Size(max = 50)
    @Column(name = "ARTIST_NAME", nullable = false, unique = true)
    private String artist_name;

    @OneToMany(mappedBy = "artist")
    private List<Album> albumList = new ArrayList<>();

    @ManyToMany(mappedBy = "artistList")
    private List<Song> songList = new ArrayList<>();

    public Artist(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return artist_id.equals(artist.artist_id) && artist_name.equals(artist.artist_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist_id, artist_name);
    }
}
