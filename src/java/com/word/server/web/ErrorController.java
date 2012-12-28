/**
 * @(#)ErrorController.java, 2012-12-21. 
 * 
 * All rights reserved.
 */
package com.word.server.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import com.word.server.exceptions.WordException;

/**
 *
 * @author Mn
 *
 */
@Controller
public class ErrorController {
    
    private static Log LOG = LogFactory.getLog(ErrorController.class);

    private static final String RESPONSE_CODE = "RES-CODE";
    
    @RequestMapping("/error")
    public ModelAndView handle(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        WordException exception = getException(request);
        LOG.error(exception.getMessage(), exception);

        // return the error message and error code in json
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("error", Integer.toString(exception.getExceptionType()));
        model.put("message", exception.getMessage());

        // set the error code in attribute for analysis
        request.setAttribute("error", exception.getExceptionType());

        response.addHeader(RESPONSE_CODE,
                Integer.toString(exception.getExceptionType()));
        response.setStatus(500);
        return JSONView.toJsonView(model);
    }
    
    /**
     * What exceptions, analyze it from request
     *
     * @param request
     * @return
     */
    private WordException getException(HttpServletRequest request) {
        Throwable ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (ex instanceof NestedServletException) {
            NestedServletException nse = (NestedServletException) ex;
            if (nse.getCause() instanceof WordException) {
                return (WordException) nse.getCause();
            }
        }
        Throwable checkEx = checkErrorCode(request);
        if (checkEx != null) {
            ex = checkEx;
        }

        if (ex == null) {
            return new WordException("unknown exception found",
                    WordException.CODE_UNKNOWN_EXCEPTION);
        } else if (!WordException.class.isInstance(ex)) {
            return new WordException(ex.getClass().getName(), ex,
                    WordException.CODE_UNKNOWN_EXCEPTION);
        }
        return (WordException) ex;
    }
    
    private Throwable checkErrorCode(HttpServletRequest request) {
        int errorCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");

        if (errorCode == 404) {
            return new WordException("bad uri:"
                    + request.getAttribute("javax.servlet.error.request_uri"),
                    WordException.CODE_UNKNOWN_URI);
        }
        if (errorCode == 405) {
            return new WordException("Unsupported request method:"
                    + request.getMethod(), WordException.CODE_UNKNOWN_URI);
        }
        return null;
    }
}
