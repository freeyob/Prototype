package work.congcong.prototype;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.geely.im.persistence.Conversation;
import com.geely.im.persistence.ConversationDataSource;
import com.geely.im.persistence.ConversationDataSourceImpl;
import com.geely.im.persistence.IMDatabase;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RoomActivity extends Activity {
    private int conut = 0;
    ConversationDataSource mDataSource;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        mDataSource = new ConversationDataSourceImpl(IMDatabase.getInstance(RoomActivity.this).conversationDao());
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conversation conversation = new Conversation();
                conut++;
                conversation.setUnreadCount(conut);
                mDisposable.add(Completable.fromAction(() -> {mDataSource.insertOrUpdateUser(conversation);}).subscribeOn(Schedulers.io()).subscribe());
                mDisposable.add(mDataSource.getConversation().observeOn(AndroidSchedulers.mainThread()).map(conversations -> {
                    if(conversations !=null && conversations.size() > 0){
                        ((TextView)findViewById(R.id.content)).setText(conversations.get(conversations.size() - 1).getUnreadCount() + "-"
                                + conversations.get(conversations.size() - 1).getId());
                    }
                    return conversations.size();
                }).subscribeOn(Schedulers.io()).subscribe());
            }
        });
    }
}
