/**
 * @(#)MongoIndexStore.java, 2012-12-22. 
 * 
 * All rights reserved.
 */
package com.word.server.store.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.word.server.conf.ServerConfig;
import com.word.server.data.Word;
import com.word.server.exceptions.WordException;
import com.word.server.store.IIndexStore;

/**
 *
 * @author Mn
 *
 */
public class MongoIndexStore implements IIndexStore {
    
    private DBCollection coll;
    
    public MongoIndexStore() throws WordException {
        String host = ServerConfig.getMongoServer();
        String user = ServerConfig.getMongoUser();
        String password = ServerConfig.getMongoPassword();
        try {
            MongoClient mongoClient = new MongoClient(host);
            DB admin = mongoClient.getDB("admin");
            admin.authenticate(user, password.toCharArray());
            DB db = mongoClient.getDB("crazyword");
            coll = db.getCollection("index");
        } catch (UnknownHostException e) {
            throw new WordException("unknown host!", e,
                    WordException.CODE_UNKNOWN_HOST_EXCEPTION);
        }
    }

    @Override
    public void index(Word word) throws WordException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Long> query(char charactor, int position, int type, int level,
            String owner) throws WordException {
        BasicDBObject query = new BasicDBObject();
        query.append("ch", charactor);
        query.append("pos", position);
        query.append("type", type);
        query.append("level", level);
        if (StringUtils.isNotBlank(owner)) {
            query.append("owner", owner);
        }
        List<Long> result = new ArrayList<Long>();
        DBCursor cursor = coll.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            result.add((long) obj.get("wordId"));
        }
        return result;
    }
}
