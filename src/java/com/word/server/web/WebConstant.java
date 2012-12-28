/**
 * @(#)WebConstant.java, 2012-12-22. 
 * 
 * All rights reserved.
 */
package com.word.server.web;

import org.springframework.web.bind.annotation.ValueConstants;

/**
 *
 * @author Mn
 *
 */
public class WebConstant {
    
    // cookie names
    public static final String S_INFO_COOKIE = "S_INFO";

    // content type of post form
    public static final String MULTIPART_FORM_TYPE = "content-type=multipart/form-data";
    public static final String URLENCODED_FORM_TYPE = "content-type=application/x-www-form-urlencoded";

    public static final String CONTENT_TYPE_JSON = "text/json; charset=UTF-8";
    public static final String CONTENT_TYPE_HTML = "text/html; charset=UTF-8";
    public static final String CONTENT_TYPE_BINARY = "application/octet-stream";

    // Header names
    public static final String REFERER_HEADER = "Referer";
    public static final String USER_AGENT_HEADER = "User-Agent";
    public static final String RANGE_HEADER = "Range";
    public static final String CONTENT_RANGE_HEADER = "Content-Range";
    public static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";

    public class TEST {
        public static final String URLMAPPING = "/api/test";
        
        public class PUT_PARAM {
        }
        
        public class GET_PARAM {
        }
    }
    
    public class WORD {
        public static final String URLMAPPING = "/api/word";
        
        public class PUT_PARAM {
        }
        
        public class GET_PARAM {
            /**
             * CHARACTER: the first character of the word
             */
            public static final String CHARACTER_NAME = "ch";
            public static final boolean CHARACTER_REQUIRED = true;
            public static final String CHARACTER_DEFAULT = ValueConstants.DEFAULT_NONE;
            
            
            /**
             * POSITION: position of the character in the whole word
             */
            public static final String POSITION_NAME = "pos";
            public static final boolean POSITION_REQUIRED = true;
            public static final String POSITION_DEFAULT = ValueConstants.DEFAULT_NONE;
            
            /**
             * TYPE: type of the whole word
             */
            public static final String TYPE_NAME = "type";
            public static final boolean TYPE_REQUIRED = false;
            public static final String TYPE_DEFAULT = "0";
            
            /**
             * LEVEL: difficult level of the whole word
             */
            public static final String LEVEL_NAME = "level";
            public static final boolean LEVEL_REQUIRED = false;
            public static final String LEVEL_DEFAULT = "1";
            
            /**
             * OWNER: difficult level of the whole word
             */
            public static final String OWNER_NAME = "owner";
            public static final boolean OWNER_REQUIRED = false;
            public static final String OWNER_DEFAULT = "";
            
            /**
             * ID: wordId
             */
            public static final String ID_NAME = "id";
            public static final boolean ID_REQUIRED = true;
            public static final String ID_DEFAULT = ValueConstants.DEFAULT_NONE;
        }
    }
}
