/**
 * @(#)MongoWord.java, 2012-12-22. All rights reserved.
 */
package com.word.server.data.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.word.server.data.Word;

/**
 * @author Mn
 */
public class MongoWord extends Word {
    
    public void fromDBObject(DBObject object) {
        this.setId((long) object.get("_id"));
        this.setWord((String) object.get("word"));
        this.setPinyin((String) object.get("pinyin"));
        this.setDescription((String) object.get("description"));
        this.setLevel((int) object.get("level"));
        this.setType((int) object.get("type"));
        this.setOwner((String) object.get("owner"));
    }

    public DBObject toDBObject() {
        DBObject obj = new BasicDBObject("_id", this.getId()).
        append("word", this.getWord()).
        append("pinyin", this.getPinyin()).
        append("description", this.getDescription()).
        append("level", this.getLevel()).
        append("type", this.getType()).
        append("owner", this.getOwner());
        return obj;
    }
}
