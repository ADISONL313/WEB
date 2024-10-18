package com.cqcskj.studentsadminvisul.mapper;

import com.cqcskj.studentsadminvisul.entity.GraphCount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.cqcskj.studentsadminvisul.entity.Student;
import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select count(*) from students")
    int selectStudents();
    @Select("select * from students where student_id=#{student_id}")
    List<Student> selectForLogin(Long student_id);
    @Select("select id_card from students where id_card=#{id_card}")
    String verifyStudent(String idCard);
    @Insert("insert into students (studentName,college,major,id_card,mobile,password,gender,counselor) value(#{studentName},#{college},#{major},#{id_card},#{mobile},#{password},#{gender},#{counselor})")
    int insertOneStudent(Student student);
    int insertStudents(List<Student> student);
    @Select("select college as name,count(*) as value from students group by college")
    List<GraphCount> collegeCount();
    List<Student> getStudentsTableData(Student student);
    @Delete("delete from students where student_id=#{student_id}")
    int delStudentById(Long id);
    int updateStudentInfo(Student student);
    @Select("select province as name,count(*) as value from students group by province")
    List<GraphCount> mapChinaProvinceCount();
    @Select("SELECT college as name,SUM(CASE WHEN gender = '男' THEN 1 ELSE 0 END) AS value,SUM(CASE WHEN gender = '女' THEN 1 ELSE 0 END) AS value2 FROM students GROUP BY college;")
    List<GraphCount> barForGenderStatistics();
}
