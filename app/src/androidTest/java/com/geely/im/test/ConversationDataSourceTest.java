package com.geely.im.test;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.geely.im.persistence.Conversation;
import com.geely.im.persistence.ConversationDataSourceImpl;
import com.geely.im.persistence.IMDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by cong on 2018/3/510.
 */

public class ConversationDataSourceTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private static final Conversation CONVERSATION = new Conversation();
    private IMDatabase mIMDatabase;
    private ConversationDataSourceImpl mConversationDataSource;

    @Before
    public void initDb() throws Exception {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        mIMDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                IMDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
        mConversationDataSource = new ConversationDataSourceImpl(mIMDatabase.conversationDao());
    }

    @After
    public void closeDb() throws Exception{
        mIMDatabase.close();
    }

    @Test
    public void insertAndGetConversation(){
        mConversationDataSource.insertOrUpdateUser(CONVERSATION);
        mConversationDataSource.getConversation()
                .test()
                .assertValue(conversations ->{
                    return conversations != null
                            && conversations.size() > 0;
        });
    }
}
