package com.cqcskj.studentsadminvisul.mapper;

import com.cqcskj.studentsadminvisul.entity.Major;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MajorMapper {
    @Insert("insert into major (majorName,college) value(#{majorName},#{college})")
    int insertMajor(Major major);
    String majorVerify(String majorName);
    @Select("select * from major where college=#{college}")
    List<Major> selectMajors(String college);
    List<Major> selectMajorByNameAdnCollege(@Param("college") String college,@Param("majorName") String majorName);
    @Delete("delete from major where id=#{id}")
    int deleteMajorById(int id);
    @Update("update major set majorName=#{majorName} where id=#{id}")
    int updateMajor(@Param("majorName")String majorName,@Param("id")int id);

    @Select(" SELECT (SELECT COUNT(*) FROM students where major=#{majorId})+(SELECT COUNT(*) FROM classes where major_id=#{majorId}) as num;")
    int delMajorVerify(int id);
}
