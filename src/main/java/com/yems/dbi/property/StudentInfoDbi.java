package com.yems.dbi.property;

import java.util.Map;

import com.yems.dao.student.StudentInfoDao;
import com.yems.framework.utility.parameters.property.Dbi;

public interface StudentInfoDbi extends Dbi
{
    public Map<Integer, StudentInfoDao> selectStudentListBySchoolId(Integer schoolId);
}
