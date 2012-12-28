/**
 * @(#)IWordStore.java, 2012-12-22. All rights reserved.
 */
package com.word.server.store;

import com.word.server.data.Word;
import com.word.server.exceptions.WordException;

/**
 * @author Mn
 */
public interface IWordStore {

    public void insert(Word word) throws WordException;
    
    public Word getWordById(long id) throws WordException;
    
    public long count() throws WordException;
}
