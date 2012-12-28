/**
 * @(#)Index.java, 2012-12-22. All rights reserved.
 */
package com.word.server.data;

import net.sf.json.JSONObject;

/**
 * @author Mn
 */
public class Index implements IJsonData {
    
    private String owner;
    
    private char character;

    private int position;

    private int level;

    private int type;

    private long wordId;

    
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

    /**
     * @return the character
     */
    public char getCharacter() {
        return character;
    }

    /**
     * @param character
     *            the character to set
     */
    public void setCharacter(char character) {
        this.character = character;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(int position) {
        this.position = position;
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
     * @return the wordId
     */
    public long getWordId() {
        return wordId;
    }

    /**
     * @param wordId
     *            the wordId to set
     */
    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", wordId);
        json.put("ch", character);
        json.put("pos", position);
        json.put("type", type);
        json.put("level", level);
        json.put("owner", owner);
        return json;
    }
}
