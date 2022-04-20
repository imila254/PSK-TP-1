package mif.vu.lt.psktp1.qualifiers;

import javax.enterprise.context.Dependent;

@Vinyl
@Dependent
public class VinylAlbumType implements AlbumTypeProcessor{

    @Override
    public String AlbumType() {
        return "Album type is vinyl";
    }
}
