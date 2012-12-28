/**
 * @(#)AbstractController.java, 2012-12-23. 
 * 
 * All rights reserved.
 */
package com.word.server.web;

import com.word.server.exceptions.WordException;
import com.word.server.store.IIndexStore;
import com.word.server.store.IWordStore;
import com.word.server.store.mongo.MongoIndexStore;
import com.word.server.store.mongo.MongoWordStore;

/**
 *
 * @author Mn
 *
 */
public abstract class AbstractController {
    
    private static volatile IWordStore wordStore;
    private static volatile IIndexStore indexStore;

    protected IIndexStore getIndexStore() throws WordException {
        if (indexStore == null) {
            synchronized(IIndexStore.class) {
                if (indexStore == null) {
                    indexStore = new MongoIndexStore();
                }
            }
        }
        return indexStore;
    }
    
    protected IWordStore getWordStore() throws WordException {
        if (wordStore == null) {
            synchronized(IWordStore.class) {
                if (wordStore == null) {
                    wordStore = new MongoWordStore();
                }
            }
        }
        return wordStore;
    }
}
