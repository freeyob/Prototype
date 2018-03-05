package com.geely.im.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by cong on 2018/3/510.
 */
@Database(entities = {Conversation.class}, version = 1)
public abstract class IMDatabase extends RoomDatabase{
    private static volatile IMDatabase INSTANCE;

    public abstract ConversationDao conversationDao();

    public static IMDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (IMDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IMDatabase.class,"IM.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
