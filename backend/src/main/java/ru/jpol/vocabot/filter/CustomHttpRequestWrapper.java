package ru.jpol.vocabot.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper for HttpServletRequest, used to configure request parameters
 */
public class CustomHttpRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String[]> params = new HashMap<>();

    public CustomHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(final String name) {
        if (params.get(name) != null) {
            return params.get(name)[0];
        }

        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> allParameters = new HashMap<>();
        allParameters.putAll(super.getParameterMap());
        allParameters.putAll(params);

        return Collections.unmodifiableMap(allParameters);
    }

    @Override
    public String[] getParameterValues(final String name) {
        return getParameterMap().get(name);
    }

    public void addParameter(String name, String value) {
        params.put(name, new String[]{value});
    }


}
