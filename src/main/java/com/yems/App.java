package com.yems;

import com.yems.framework.utility.MainClass;
import com.yems.services.ServiceFactory;
import com.yems.services.property.ServicePages;

public class App extends MainClass
{
    public void start(String[] args)
    {
        ServiceFactory fac = new ServiceFactory(args);
        for (ServicePages pages : fac.getServiceList())
        {
            LOG.info("Service start: " + pages);
            if (!fac.getController(pages))
            {
                LOG.error("Service failed");
            }
            LOG.info("Service end");
        }
    }

    public static void main(String[] args)
    {
        new App().start(args);
    }
}
