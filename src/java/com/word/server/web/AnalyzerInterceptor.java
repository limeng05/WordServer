/**
 * @(#)AnalyzerInterceptor.java, 2012-12-25. 
 * 
 * All rights reserved.
 */
package com.word.server.web;

import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 *
 * @author Mn
 *
 */
public class AnalyzerInterceptor extends HandlerInterceptorAdapter {
    private static Log LOG = LogFactory.getLog(AnalyzerInterceptor.class);

    private static final String ANALYZER_IDENTIFICATION_STRING = "@@ANALYSIS@@";
    
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
        boolean exceptional = ex != null;
        StringBuilder exceptionKey = new StringBuilder(ANALYZER_IDENTIFICATION_STRING);
        exceptionKey.append(" ").append("exceptional=");
        LOG.info(exceptionKey.toString() + exceptional);
        
        printRequestLog(request, response);
    }

    private void printRequestLog(HttpServletRequest request,
            HttpServletResponse response) {
        SimpleDateFormat tempDate = new SimpleDateFormat("HH:mm:ss");
        String currentTime = tempDate.format(new java.util.Date());
        
        // copied from RequestLogger
        StringBuilder buffer = new StringBuilder(currentTime + " \t"
                    + ANALYZER_IDENTIFICATION_STRING);
        buffer.append("\t");
        
        for (Enumeration<?> attrNames = request.getParameterNames(); attrNames
                .hasMoreElements();) {
            String attrName = (String) attrNames.nextElement();
            String value = request.getParameter(attrName);
            buffer.append("\t");
            buffer.append(escape(attrName));
            buffer.append("=");
            buffer.append(escape(value));
        }
//        for (Enumeration<?> attrNames = request.getAttributeNames(); attrNames
//                .hasMoreElements();) {
//            String attrName = (String) attrNames.nextElement();
//            buffer.append("\t[@");
//            buffer.append(escape(attrName));
//            buffer.append("=");
//            buffer.append(escape(request.getAttribute(attrName).toString()));
//            buffer.append("]");
//        }
        LOG.info(buffer.toString());
    }
    
    private static String escape(String text) {
        if (text == null) {
            return "NULL";
        }
        int length = text.length();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char ch = text.charAt(i);
            switch (ch) {
                case '\t':
                    buffer.append("\\t");
                    break;
                case '\n':
                    buffer.append("\\n");
                    break;
                case '\r':
                    buffer.append("\\r");
                    break;
                case '[':
                    buffer.append("\\[");
                    break;
                case ']':
                    buffer.append("\\]");
                    break;
                case '\\':
                    buffer.append("\\\\");
                    break;
                default:
                    buffer.append(ch);
            }
        }
        return buffer.toString();
    }
}
