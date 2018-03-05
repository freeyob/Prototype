package com.geely.im.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by cong on 2018/3/510.
 */

@Dao
public interface ConversationDao {
    @Query("SELECT * FROM im_thread")
    Flowable<List<Conversation>> getConversation();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertConversation(Conversation conversation);
}
