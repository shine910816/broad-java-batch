package com.yems.dbi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import com.yems.dao.student.StudentInfoDao;
import com.yems.dao.student.StudentInfoDto;
import com.yems.dbi.property.StudentInfoDbi;
import com.yems.framework.connect.mysql.MysqlDbi;
import com.yems.framework.utility.Request;

public class StudentInfoDbiImpl extends MysqlDbi implements StudentInfoDbi
{
    public StudentInfoDbiImpl(Request request)
    {
        super(request);
    }

    @Override
    public Map<Integer, StudentInfoDao> selectStudentListBySchoolId(Integer schoolId)
    {
        LOG.info("Select student list by school id: " + schoolId);

        String sql = "SELECT student_id, school_id, student_name," + //
                " student_mobile_number, student_entrance_year, student_gender, student_school_name," + //
                " assign_member_id, assign_date, operated_by, insert_date" + //
                " FROM student_info WHERE del_flg = 0 AND school_id = " + schoolId;
        ResultSet result = slave().query(sql);
        Map<Integer, StudentInfoDao> output = new TreeMap<>();
        try
        {
            while (result.next())
            {
                StudentInfoDao dao = StudentInfoDto.getResult(result);
                output.put(dao.studentId(), dao);
            }
        }
        catch (SQLException e)
        {
            LOG.error("Select student list by school id failed: " + e.getMessage());
        }
        return output;
    }
}
