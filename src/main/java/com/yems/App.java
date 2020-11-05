package com.yems;

import java.util.Map;
import java.util.Map.Entry;

import com.yems.dao.student.StudentInfoDao;
import com.yems.dbi.YemsDbiImpl;
import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Request;

public class App extends MainClass
{
    public static void main(String[] args)
    {
        new App().start(args);
    }

    public void start(String[] args)
    {
        Request request = new Request(args);
        YemsDbiImpl dbi = new YemsDbiImpl(request);
        Map<Integer, StudentInfoDao> list = dbi.student().selectStudentListBySchoolId(1);
        for (Entry<Integer, StudentInfoDao> item : list.entrySet())
        {
            StudentInfoDao dao = item.getValue();
            LOG.info(dao.studentName() + "\t" + dao.studentCoveredMobile() + "\t" + dao.studentGrade().gradeName());
        }
    }
}
