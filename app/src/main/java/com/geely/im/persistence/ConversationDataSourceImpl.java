package com.geely.im.persistence;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by cong on 2018/3/510.
 */

public class ConversationDataSourceImpl implements ConversationDataSource{
    private final ConversationDao mConversationDao;

    public ConversationDataSourceImpl(ConversationDao conversationDao) {
        this.mConversationDao = conversationDao;
    }

    @Override
    public Flowable<List<Conversation>> getConversation() {
        return mConversationDao.getConversation();
    }

    @Override
    public void insertOrUpdateUser(Conversation conversation) {
        mConversationDao.insertConversation(conversation);
    }
}
