package com.cqcskj.studentsadminvisul.mapper;

import com.cqcskj.studentsadminvisul.entity.Dormitory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DormitoryMapper {
    List<Dormitory> getDormitories(Dormitory dormitory);
    @Insert("insert into dormitories (buildingName,room_id,gender) value(#{buildingName},#{room_id},#{gender})")
    int insertDormitory(Dormitory dormitory);
    @Delete("delete from dormitories where id=#{id}")
    int delDormitory(int id);
}
