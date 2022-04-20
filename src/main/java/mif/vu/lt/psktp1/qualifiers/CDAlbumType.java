package mif.vu.lt.psktp1.qualifiers;

import javax.enterprise.context.Dependent;

@CD
@Dependent
public class CDAlbumType implements AlbumTypeProcessor{

    @Override
    public String AlbumType() {
        return "Album type is CD";
    }
}
