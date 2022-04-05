package mif.vu.lt.psktp1.mybatis.dao;

import java.util.List;

import mif.vu.lt.psktp1.mybatis.model.Album;
import mif.vu.lt.psktp1.mybatis.model.Artist;
import mif.vu.lt.psktp1.mybatis.model.Song;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ArtistMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    int insert(Artist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    Artist selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    List<Artist> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    int updateByPrimaryKey(Artist record);

    List<Album> selectAlbumsForArtist(Integer id);

    List<Song> selectSongsForArtist(Integer id);
}