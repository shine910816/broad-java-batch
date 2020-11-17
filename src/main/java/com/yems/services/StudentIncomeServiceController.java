package com.yems.services;

import com.yems.dbi.property.YemsDbi;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.Utility;
import com.yems.services.property.ServiceControllerImpl;

public class StudentIncomeServiceController extends ServiceControllerImpl<YemsDbi>
{
    private boolean m_isExecDate = false;

    protected StudentIncomeServiceController(Request request, YemsDbi dbi)
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

        LOG.info("Student income run");

        

        return true;
    }
}
