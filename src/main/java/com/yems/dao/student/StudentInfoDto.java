package com.yems.dao.student;

import java.sql.ResultSet;

import com.yems.framework.connect.property.DtoImpl;
import com.yems.framework.utility.parameters.constant.ConstGender;
import com.yems.framework.utility.parameters.property.Dto;

public class StudentInfoDto extends DtoImpl implements Dto<StudentInfoDao>
{
    public StudentInfoDto(ResultSet res)
    {
        super(res);
    }

    @Override
    public StudentInfoDao dao()
    {
        return new StudentInfoDao.ContainerBuilder() //
                .studentId(num("student_id")) //
                .schoolId(num("school_id")) //
                .studentName(get("student_name")) //
                .studentMobileNumber(get("student_mobile_number")) //
                .studentEntranceYear(num("student_entrance_year")) //
                .studentGender(type("student_gender", ConstGender.class)) //
                .studentSchoolName(get("student_school_name")) //
                .assignMemberId(num("assign_member_id")) //
                .assignDate(date("assign_date")) //
                .operatedBy(num("operated_by")) //
                .insertDate(date("insert_date")) //
                .build();
    }

    public static StudentInfoDao getResult(ResultSet res)
    {
        return new StudentInfoDto(res).dao();
    }
}
