package com.hnq.study.dao.mapper;

import com.hnq.study.dao.domain.TouTiaoMissingGoods;
import com.hnq.study.dao.domain.TouTiaoMissingGoodsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TouTiaoMissingGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int countByExample(TouTiaoMissingGoodsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int deleteByExample(TouTiaoMissingGoodsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int insert(TouTiaoMissingGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int insertSelective(TouTiaoMissingGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    List<TouTiaoMissingGoods> selectByExampleWithRowbounds(TouTiaoMissingGoodsCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    List<TouTiaoMissingGoods> selectByExample(TouTiaoMissingGoodsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    TouTiaoMissingGoods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int updateByExampleSelective(@Param("record") TouTiaoMissingGoods record, @Param("example") TouTiaoMissingGoodsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int updateByExample(@Param("record") TouTiaoMissingGoods record, @Param("example") TouTiaoMissingGoodsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int updateByPrimaryKeySelective(TouTiaoMissingGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table toutiao_missing_goods
     *
     * @mbggenerated Mon Aug 19 14:28:46 CST 2019
     */
    int updateByPrimaryKey(TouTiaoMissingGoods record);
}