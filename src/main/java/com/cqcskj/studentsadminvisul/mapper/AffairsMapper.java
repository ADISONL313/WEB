package com.cqcskj.studentsadminvisul.mapper;

import com.cqcskj.studentsadminvisul.entity.Affairs;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AffairsMapper {
    @Select("select count(*) from affairs where student_id=#{student_id} and affairsType=#{affairsType}")
    int verifyAffair(@Param("student_id")Long student_id,@Param("affairsType")int affairsType);
    @Insert("insert into affairs (student_id,teacher_id,content,affairsType,audits,reason,sourceRoomOrMajor,majorOrRoomId) values (#{student_id},#{teacher_id},#{content},#{affairsType},#{audits},#{reason},#{sourceRoomOrMajor},#{majorOrRoomId})")
    int addAffairs(Affairs affairs);
    @Select("select affairs.id,affairs.majorOrRoomId,affairs.refuseReason,affairs.student_id,students.studentName,affairs.teacher_id,affairs.sourceRoomOrMajor,affairs.content,affairs.affairsType,affairs.audits,affairs.reason from affairs join students on students.student_id=affairs.student_id where teacher_id=#{teacher_id} and audits!=1 and audits!=2 and audits!=3")
    List<Affairs> selectAffairsByTeacher(String teacher_id);
    int updateApplyState(Affairs affairs);
    List<Affairs> selectAllAffairs(Affairs affairs);
    @Delete("delete from affairs where id=#{id}")
    int delAffairsById(int id);
}
