package com.geely.im.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by cong on 2018/3/510.
 */
@Entity(tableName = "im_thread")
public class Conversation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "sessionId")
    private String sessionId;

    @ColumnInfo(name = "sumCount")
    private String sumCount;

    @ColumnInfo(name = "contactId")
    private  String contactId;

    @ColumnInfo(name = "contactName")
    private String contactName;

    @ColumnInfo(name = "snippet")
    private String snippet;

    @ColumnInfo(name = "unreadCount")
    private Integer unreadCount = 0;

    @ColumnInfo(name = "sendStatus")
    private Integer sendStatus = 0;

    @ColumnInfo(name = "boxType")
    private Integer boxType = 0;

    @ColumnInfo(name = "dateTime")
    private String dateTime;

    @ColumnInfo(name = "messageType")
    private Integer messageType = 0;

    @ColumnInfo(name = "messageCount")
    private Integer messageCount = 0;

    @ColumnInfo(name = "top")
    private Integer top = 0;

    @ColumnInfo(name = "chatBg")
    private String chatBg;

    @ColumnInfo(name = "notice")
    private String notice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSumCount() {
        return sumCount;
    }

    public void setSumCount(String sumCount) {
        this.sumCount = sumCount;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getBoxType() {
        return boxType;
    }

    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public String getChatBg() {
        return chatBg;
    }

    public void setChatBg(String chatBg) {
        this.chatBg = chatBg;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
