package com.yems.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.yems.dao.order.OrderItemSimpleInfoDao;
import com.yems.dao.student.StudentInfoDao;
import com.yems.dbi.property.YemsDbi;
import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.Utility;
import com.yems.framework.utility.Variable;
import com.yems.framework.utility.parameters.constant.ConstDatetime;
import com.yems.services.property.ServiceControllerImpl;

public class SurplusInfoServiceController extends ServiceControllerImpl<YemsDbi>
{
    private boolean m_isExec = false;

    protected SurplusInfoServiceController(Request request, YemsDbi dbi)
    {
        super(request, dbi);
    }

    @Override
    public boolean doMainValidate()
    {
        if (request().hasParameter("ness") && request().getParameter("ness").equals("1"))
        {
            LOG.info("Manual execute");
            m_isExec = true;
            return true;
        }
        if (Utility.getDate(Utility.adjustDate(Utility.getCurrentDate(), 1), "dd").equals("01"))
        {
            LOG.info("The end day execute");
            m_isExec = true;
            return true;
        }
        return true;
    }

    @Override
    public boolean doMainExecute()
    {
        if (!m_isExec)
        {
            LOG.error("It is not the end day of month: " + Utility.getCurrentDate(ConstDatetime.DATE));
            return true;
        }
        String indexYearMonth = Utility.getCurrentDate(ConstDatetime.DATE_YEAR_MONTH_NONE);
        LOG.info("Surplus info batch execute, index year month: " + indexYearMonth);
        Map<Integer, StudentInfoDao> studentList = dbi().student().selectStudentListBySchoolId(Variable.SCHOOL_ID);
        Map<Integer, OrderItemSimpleInfoDao> orderItemList = dbi().orderItem().selectSimpleOrderItenForIncomeStats(Variable.SCHOOL_ID);
        Map<Integer, SurplusInfoBean> dataMap = new HashMap<>();
        for (Entry<Integer, OrderItemSimpleInfoDao> orderItemInfo : orderItemList.entrySet())
        {
            Integer studentId = orderItemInfo.getValue().studentId();
            if (studentList.containsKey(studentId))
            {
                if (!dataMap.containsKey(studentId))
                {
                    SurplusInfoBean newBean = new SurplusInfoServiceController.SurplusInfoBean(studentList.get(studentId), indexYearMonth);
                    dataMap.put(studentId, newBean);
                }
                SurplusInfoBean bean = dataMap.get(studentId);
                bean.set(orderItemInfo.getValue().orderItemHoursAmount() - orderItemInfo.getValue().orderItemConfirm(), orderItemInfo.getValue().orderItemTransPrice());
                dataMap.put(studentId, bean);
            }
        }
        for (Entry<Integer, SurplusInfoBean> beanItem : dataMap.entrySet())
        {
            Map<String, String> insertData = beanItem.getValue().insertData();
            if (insertData != null)
            {
                dbi().stats().insertSurplus(insertData);
            }
        }
        return true;
    }

    public static class SurplusInfoBean extends MainClass
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
            m_amount += Utility.toFloat(String.format("%.2f", remain * price));
        }

        public Map<String, String> insertData()
        {
            if (m_count <= 0f)
            {
                return null;
            }
            Map<String, String> data = new TreeMap<>();
            data.put("surplus_date", m_idx);
            data.put("student_id", Utility.toString(m_dao.studentId()));
            data.put("school_id", Utility.toString(m_dao.schoolId()));
            data.put("student_name", m_dao.studentName());
            data.put("student_mobile_number", m_dao.studentCoveredMobile());
            data.put("student_grade", m_dao.studentGrade().val());
            data.put("student_surplus_count", String.format("%.1f", m_count));
            data.put("student_surplus_amount", String.format("%.2f", m_amount));
            return data;
        }
    }
}
