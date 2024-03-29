package mif.vu.lt.psktp1.mybatis.dao;

import java.util.List;
import mif.vu.lt.psktp1.mybatis.model.Album;
import mif.vu.lt.psktp1.mybatis.model.Artist;
import org.mybatis.cdi.Mapper;

@Mapper
public interface AlbumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ALBUM
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ALBUM
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    int insert(Album record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ALBUM
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    Album selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ALBUM
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    List<Album> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ALBUM
     *
     * @mbg.generated Wed Mar 30 17:09:18 EEST 2022
     */
    int updateByPrimaryKey(Album record);

    Artist getAlbumArtist(Integer id);
}