/**
 * @(#)MongoWordStore.java, 2012-12-22. All rights reserved.
 */
package com.word.server.store.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.word.server.conf.ServerConfig;
import com.word.server.data.Word;
import com.word.server.data.mongo.MongoWord;
import com.word.server.exceptions.WordException;
import com.word.server.store.IWordStore;

/**
 * @author Mn
 */
public class MongoWordStore implements IWordStore {
    
    private DBCollection coll;
    
    public MongoWordStore() throws WordException {
        String host = ServerConfig.getMongoServer();
        String user = ServerConfig.getMongoUser();
        String password = ServerConfig.getMongoPassword();
        try {
            MongoClient mongoClient = new MongoClient(host);
            DB db = mongoClient.getDB("crazyword");
            db.authenticate(user, password.toCharArray());
            coll = db.getCollection("words");
        } catch (UnknownHostException e) {
            throw new WordException("unknown host!", e,
                    WordException.CODE_UNKNOWN_HOST_EXCEPTION);
        }
    }

    @Override
    public void insert(Word word) throws WordException {

    }

    @Override
    public Word getWordById(long id) throws WordException {
        BasicDBObject query = new BasicDBObject("_id", id);
        DBObject result = coll.findOne(query);
        if (result == null) {
            throw new WordException("Word not found for id=" + id,
                    WordException.CODE_WORD_NOT_EXSITS);
        }
        MongoWord word = new MongoWord();
        word.fromDBObject(result);
        return word;
    }

    @Override
    public long count() {
        DBCursor cursor = coll.find();
        int total = cursor.count();
        return total;
    }

}
