package com.yems.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.yems.dao.order.OrderItemSimpleInfoDao;
import com.yems.dao.student.StudentInfoDao;
import com.yems.dbi.property.YemsDbi;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.Utility;
import com.yems.framework.utility.parameters.constant.ConstDatetime;
import com.yems.services.property.ServiceControllerImpl;

public class SurplusInfoServiceController extends ServiceControllerImpl<YemsDbi>
{
    private boolean m_isExecDate = false;

    protected SurplusInfoServiceController(Request request, YemsDbi dbi)
    {
        super(request, dbi);
    }

    @Override
    public boolean doMainValidate()
    {
        if (Utility.getDate(Utility.adjustDate(Utility.getCurrentDate(), 1), "dd") == "01")
        {
            m_isExecDate = true;
        }
        // TODO For test, complete coding it will be deleted
        m_isExecDate = true;
        return true;
    }

    @Override
    public boolean doMainExecute()
    {
        if (!m_isExecDate)
        {
            LOG.info("This day is not the end day of month");
            return true;
        }
        String indexYearMonth = Utility.getCurrentDate(ConstDatetime.DATE_YEAR_MONTH_NONE);
        int schoolId = 1;

        LOG.info("Surplus info batch execute, index year month: " + indexYearMonth);

        Map<Integer, StudentInfoDao> studentList = dbi().student().selectStudentListBySchoolId(schoolId);
        Map<Integer, OrderItemSimpleInfoDao> orderItemList = dbi().orderItem().selectSimpleOrderItenForIncomeStats(schoolId);
        Map<Integer, SurplusInfoBean> dataMap = new HashMap<>();
        for (Entry<Integer, OrderItemSimpleInfoDao> orderItemInfo : orderItemList.entrySet())
        {
            Integer studentId = orderItemInfo.getValue().studentId();
            if (studentList.containsKey(studentId))
            {
                LOG.info("student_id: " + studentId + "\t" + studentList.get(studentId).studentName());
                if (!dataMap.containsKey(studentId)) {
                    SurplusInfoBean bean = new SurplusInfoServiceController.SurplusInfoBean(studentList.get(studentId), indexYearMonth);
                    dataMap.put(studentId, bean);
                }
            }
        }

        return true;
    }

    public static class SurplusInfoBean
    {
        private StudentInfoDao m_dao;
        private String m_idx;
        private Float m_count = 0f;
        private Float m_amount = 0f;

        private SurplusInfoBean(StudentInfoDao dao, String idx)
        {
            m_dao = dao;
            m_idx = idx;
        }

        public void set(Float remain, Float price)
        {
            m_count += remain;
            m_amount += remain * price;
        }
        
        public Map<String,String> insertData()
        {
            Map<String,String> data = new TreeMap<>();
            
            
            return data;
        }
    }
}
