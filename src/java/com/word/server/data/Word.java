/**
 * @(#)Word.java, 2012-12-22. All rights reserved.
 */
package com.word.server.data;

import net.sf.json.JSONObject;

/**
 * @author Mn
 */
public class Word implements IJsonData {

    /**
     * primary key
     */
    private long id;

    /**
     * the result
     */
    private String word;

    /**
     * String of first character of the pinyin
     */
    private String pinyin;

    /**
     * hint for the result
     */
    private String description;

    /**
     * difficult level
     */
    private int level;

    /**
     * english:0 history:1 ....
     */
    private int type;
    
    /**
     * owner of the word
     * */
    private String owner;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word
     *            the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * @param pinyin
     *            the pinyin to set
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("word", word);
        json.put("pinyin", pinyin);
        json.put("desc", description);
        json.put("type", type);
        json.put("level", level);
        json.put("owner", owner);
        return json;
    }
}
