package com.aimprosoft.kmb.conroller;

import com.aimprosoft.kmb.web.servlets.action.Action;
import com.aimprosoft.kmb.web.servlets.page.department.ManageDepartmentsPageServlet;

import java.util.HashMap;
import java.util.Map;

public class RequestResolver {


    private Map<String, Action> mapping = new HashMap<>();

    {

        //mapping.put("/manage-departments-page", new ManageDepartmentsPageServlet());


        }

    public  Action get(String pageUrl){
        return mapping.get(pageUrl);
    }
}
