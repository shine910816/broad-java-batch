package com.yems.services;

import java.util.Map.Entry;

import com.yems.dao.student.StudentInfoDao;
import com.yems.dbi.property.YemsDbi;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.Variable;
import com.yems.services.property.ServiceControllerImpl;

public class TestController extends ServiceControllerImpl<YemsDbi>
{
    protected TestController(Request request, YemsDbi dbi)
    {
        super(request, dbi);
    }

    @Override
    public boolean doMainValidate()
    {
        return true;
    }

    @Override
    public boolean doMainExecute()
    {
        for (Entry<Integer, StudentInfoDao> item : dbi().student().selectStudentListBySchoolId(Variable.SCHOOL_ID).entrySet())
        {
            LOG.info("student_id: " + item.getKey());
            LOG.info(item.getValue().studentName() + "\t" + //
                    item.getValue().studentCoveredMobile() + "\t" + //
                    item.getValue().studentGrade().gradeName());
        }
        return true;
    }
}
