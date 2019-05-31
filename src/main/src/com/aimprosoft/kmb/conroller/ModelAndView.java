package com.aimprosoft.kmb.conroller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    private String viewName;

    private Map<String, Object> modelData = new HashMap<>();

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public void addMapping(String key, Object value) {
        modelData.put(key, value);
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, Object> getModelData() {
        return Collections.unmodifiableMap(modelData);
    }

}
