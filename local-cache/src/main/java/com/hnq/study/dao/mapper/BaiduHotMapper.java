package com.hnq.study.dao.mapper;

import com.hnq.study.dao.domain.BaiduHot;
import com.hnq.study.dao.domain.BaiduHotCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BaiduHotMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int countByExample(BaiduHotCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int deleteByExample(BaiduHotCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int insert(BaiduHot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int insertSelective(BaiduHot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    List<BaiduHot> selectByExampleWithBLOBsWithRowbounds(BaiduHotCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    List<BaiduHot> selectByExampleWithBLOBs(BaiduHotCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    List<BaiduHot> selectByExampleWithRowbounds(BaiduHotCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    List<BaiduHot> selectByExample(BaiduHotCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    BaiduHot selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BaiduHot record, @Param("example") BaiduHotCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") BaiduHot record, @Param("example") BaiduHotCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BaiduHot record, @Param("example") BaiduHotCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BaiduHot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(BaiduHot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table baidu_hot
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BaiduHot record);
}