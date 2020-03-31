package com.example.demo.mapper;

import com.example.demo.entity.Demo;
import com.example.demo.entity.DemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    long countByExample(DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int deleteByExample(DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int deleteByPrimaryKey(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int insert(Demo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int insertSelective(Demo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    List<Demo> selectByExample(DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    Demo selectByPrimaryKey(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int updateByExample(@Param("record") Demo record, @Param("example") DemoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int updateByPrimaryKeySelective(Demo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo
     *
     * @mbg.generated Sun Mar 29 20:26:29 CST 2020
     */
    int updateByPrimaryKey(Demo record);
}