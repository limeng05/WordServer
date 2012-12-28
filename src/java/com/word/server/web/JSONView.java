/**
 * @(#)JSONView.java, 2012-12-222.
 *
 * All rights reserved.
 */
package com.word.server.web;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.word.server.data.IJsonData;

/**
 * Construct a json view based on given object.
 *
 * @author Mn
 */
public class JSONView implements View {
    public static final String VIEW_NAME = "jsonView";
    public static final String MODEL_NAME = "jsonStr";

    /**
     * @return text/html instead of text/json because some browser such
     * as firefox will take over json, which is disgusting
     */
    @Override
    public String getContentType() {
        return WebConstant.CONTENT_TYPE_HTML;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String jsonAsString = (String) model.get(MODEL_NAME);
        response.setContentType(getContentType());
        byte[] bytes = jsonAsString.getBytes("UTF-8");
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);

        outputStream.flush();
        outputStream.close();
    }

    /**
     * Construct a json view based on the json string
     * @param json
     * @return
     */
    public static final ModelAndView toJsonView(String json) {
        return new ModelAndView(VIEW_NAME, MODEL_NAME, json);
    }

    /**
     * Construct a json view based on the given <code>map</code>.
     * @param map data model where key is the json key and value is the
     * json value
     * @return
     */
    public static final ModelAndView toJsonView(Map<String, Object> map) {
        JSONObject json = JSONObject.fromObject(map);
        return new ModelAndView(VIEW_NAME, MODEL_NAME, json.toString());
    }
    
    /**
     * Construct a json view based on the given <code>data</code>.
     * @param data IJsonData 
     * @return
     */
    public static final ModelAndView toJsonView(IJsonData data) {
        return new ModelAndView(VIEW_NAME, MODEL_NAME, data.toJson().toString());
    }
}
