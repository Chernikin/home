package com.aimprosoft.kmb.web.servlets.action;

import com.aimprosoft.kmb.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {

    void handle(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException;
}
