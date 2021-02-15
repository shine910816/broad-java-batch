package com.yems.services;

import java.util.List;

import com.yems.dbi.YemsDbiImpl;
import com.yems.dbi.property.YemsDbi;
import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.Utility;
import com.yems.services.property.ServiceController;
import com.yems.services.property.ServicePages;

public class ServiceFactory extends MainClass
{
    private Request m_request;
    private YemsDbi m_dbi;

    public ServiceFactory(String[] args)
    {
        m_request = new Request(args);
        m_dbi = new YemsDbiImpl(m_request);
    }

    public Request request()
    {
        return m_request;
    }

    public List<ServicePages> getServiceList()
    {
        return Utility.getEnums(m_request.getParameter("exec"), ServicePages.class);
    }

    public boolean getController(ServicePages pages)
    {
        ServiceController controller = null;
        switch (pages)
        {
            case SURPLUS_INFO:
                controller = new SurplusInfoServiceController(m_request, m_dbi);
                break;
            case CHANGE_ENDING:
                controller = new ChangeToEndingController(m_request, m_dbi);
                break;
            case TEST:
                controller = new TestController(m_request, m_dbi);
                break;
            default:
                LOG.error("Not found controller: " + pages);
                return false;
        }
        if (!controller.doMainValidate())
        {
            LOG.error("Do main validation failed");
            return false;
        }
        if (!controller.doMainExecute())
        {
            LOG.error("Do main execution failed");
            return false;
        }
        return true;
    }
}
