package com.aimprosoft.kmb.web.servlets.action;

import com.aimprosoft.kmb.web.conroller.Controller;

public class PageMapping {

    private Controller controller;
    private String method;
    private String jsp;
    private String redirect;


    public PageMapping(Controller controller, String method, String jsp, String redirect) {
        this.controller = controller;
        this.method = method;
        this.jsp = jsp;
        this.redirect = redirect;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public String getJsp() {
        return jsp;
    }

    public void setJsp(String jsp) {
        this.jsp = jsp;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
