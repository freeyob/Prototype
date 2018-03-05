package com.geely.im.persistence;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by cong on 2018/3/510.
 */

public interface ConversationDataSource {
    Flowable<List<Conversation>> getConversation();
    void insertOrUpdateUser(Conversation conversation);
}
