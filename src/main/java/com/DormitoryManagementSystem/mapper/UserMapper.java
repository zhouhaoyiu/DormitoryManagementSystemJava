package com.DormitoryManagementSystem.mapper;

import com.DormitoryManagementSystem.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from user where username=#{name}")
    User selectUser(String userName);

    @Select("select * from user where role in (0,1) order by buildId")
    List<User> getAllAdmin();

    @Select("select * from user where role = 2 order by buildId")
    List<User> getAllStudent();

    @Select("select * from user where role = 2 and buildId = #{buildId} order by buildId")
    List<User> getBuildStudent(String buildId);

    @Select("select * from user")
    List<User> selectAllUsers();

    @Select("select role from user where schoolId=#{schoolId}")
    String getRole(Integer schoolId);

    @Select("select trueName from user where schoolId=#{schoolId}")
    String getTrueName(Integer schoolId);

    @Select("select * from user where username=#{userName} or truename =#{trueName}")
    List<User> searchUser(String trueName, String userName);

    @Insert("INSERT into user(schoolId,userName,passWord,phoneNumber,trueName,role,checkTime,buildId) VALUES(#{schoolId},#{userName},#{passWord},#{phoneNumber},#{trueName},#{role},#{checkTime},#{buildId})")
    int addAdmin(@Param("schoolId") String schoolId, @Param("userName") String userName, @Param("passWord") String passWord, @Param("phoneNumber") String phoneNumber, @Param("trueName") String trueName, @Param("role") int role, @Param("checkTime") String checkTime, @Param("buildId") String buildId);

    @Insert("INSERT into user(schoolId,userName,passWord,phoneNumber,trueName,role,checkTime,buildId,roomId,updateTime) VALUES(#{schoolId},#{userName},#{passWord},#{phoneNumber},#{trueName},2,#{checkTime},#{buildId},#{roomId},#{updateTime})")
    int addStu(@Param("schoolId") String schoolId, @Param("userName") String userName, @Param("passWord") String passWord, @Param("phoneNumber") String phoneNumber, @Param("trueName") String trueName,@Param("checkTime") String checkTime, @Param("buildId") String buildId,@Param("roomId")String roomId,@Param("updateTime")String updateTime);

    @Select("select count(id) from user where buildId = #{buildId} and role = 2")
    Integer getStudentsByBuildId(String buildId);
    @Delete("delete from user where schoolId = #{schoolId}")
    boolean deleteAdminBySchoolId(Integer schoolId);
}
