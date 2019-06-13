package com.aimprosoft.kmb.web.conroller;

import com.aimprosoft.kmb.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException;
}
