package com.aimprosoft.kmb.web.conrollers;

import com.aimprosoft.kmb.exceptions.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException, ServletException, IOException;
}
