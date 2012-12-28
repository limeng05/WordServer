/**
 * @(#)WordController.java, 2012-12-22. 
 * 
 * All rights reserved.
 */
package com.word.server.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.word.server.data.Word;
import com.word.server.exceptions.WordException;
import com.word.server.store.IIndexStore;
import com.word.server.store.IWordStore;
import com.word.server.web.WebConstant.WORD.GET_PARAM;

/**
 *
 * @author Mn
 *
 */
@Controller
@RequestMapping(WebConstant.WORD.URLMAPPING)
public class WordController extends AbstractController {
    private static final Log LOG = LogFactory.getLog(WordController.class);

    /**
     * query a word by ch, pos, etc.
     * */
    @RequestMapping(params = "method=query")
    public ModelAndView queryWord(
            @RequestParam(value=GET_PARAM.CHARACTER_NAME,
                    required=GET_PARAM.CHARACTER_REQUIRED,
                    defaultValue=GET_PARAM.CHARACTER_DEFAULT) char ch,
            @RequestParam(value=GET_PARAM.POSITION_NAME,
                    required=GET_PARAM.POSITION_REQUIRED,
                    defaultValue=GET_PARAM.POSITION_DEFAULT) int position,
            @RequestParam(value=GET_PARAM.TYPE_NAME,
                    required=GET_PARAM.TYPE_REQUIRED,
                    defaultValue=GET_PARAM.TYPE_DEFAULT) int type,
            @RequestParam(value=GET_PARAM.LEVEL_NAME,
                    required=GET_PARAM.LEVEL_REQUIRED,
                    defaultValue=GET_PARAM.LEVEL_DEFAULT) int level,
            @RequestParam(value=GET_PARAM.OWNER_NAME,
                    required=GET_PARAM.OWNER_REQUIRED,
                    defaultValue=GET_PARAM.OWNER_DEFAULT) String owner,
            HttpServletRequest request, 
            HttpServletResponse response
            ) throws WordException {
        LOG.debug("query word");
        IIndexStore indexStore = getIndexStore();
        List<Long> ids = indexStore.query(ch, position, type, level, owner);
        
        JSONArray array = new JSONArray();
        for (Long id : ids) {
            array.add(id);
        }
        
        return JSONView.toJsonView(array.toString());
    }
    
    /**
     * get word by id.
     * */
    @RequestMapping(params = "method=get")
    public ModelAndView getWord(
            @RequestParam(value=GET_PARAM.ID_NAME,
                    required=GET_PARAM.ID_REQUIRED,
                    defaultValue=GET_PARAM.ID_DEFAULT) long wordId,
            HttpServletRequest request, 
            HttpServletResponse response
            ) throws WordException {
        LOG.debug("get word");
        IWordStore wordStore = getWordStore();
        Word word = wordStore.getWordById(wordId);
        return JSONView.toJsonView(word);
    }
    
    /**
     * query a word by ch, pos, etc.
     * */
    @RequestMapping(params = "method=count")
    public ModelAndView countTotal(
            HttpServletRequest request, 
            HttpServletResponse response
            ) throws WordException {
        LOG.debug("count word");
        IWordStore wordStore = getWordStore();
        long total = wordStore.count();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("total", total);
        return JSONView.toJsonView(model);
    }
    //TODO upload a word, how to get the current wordId? batch load?
}
