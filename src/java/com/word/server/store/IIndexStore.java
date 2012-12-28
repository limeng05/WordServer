/**
 * @(#)IIndexStore.java, 2012-12-22. 
 * 
 * All rights reserved.
 */
package com.word.server.store;

import java.util.List;

import com.word.server.data.Word;
import com.word.server.exceptions.WordException;

/**
 *
 * @author Mn
 *
 */
public interface IIndexStore {

    public void index(Word word) throws WordException;
    
    public List<Long> query(char charactor, int position, int type, int level,
            String owner) throws WordException;
}
