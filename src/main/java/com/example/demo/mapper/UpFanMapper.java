package com.example.demo.mapper;

import com.example.demo.entity.UpFan;
import com.example.demo.entity.UpFanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpFanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table up_fan
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    long countByExample(UpFanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table up_fan
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    int deleteByExample(UpFanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table up_fan
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    int insert(UpFan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table up_fan
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    int insertSelective(UpFan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table up_fan
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    List<UpFan> selectByExample(UpFanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table up_fan
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    int updateByExampleSelective(@Param("record") UpFan record, @Param("example") UpFanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table up_fan
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    int updateByExample(@Param("record") UpFan record, @Param("example") UpFanExample example);
}