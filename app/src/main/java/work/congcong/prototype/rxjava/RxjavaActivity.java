package work.congcong.prototype.rxjava;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import work.congcong.prototype.R;

public class RxjavaActivity extends Activity {
    ConnectableObservable mConnectableObservable;
    ObservableEmitter mObservableEmitter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        findViewById(R.id.emitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mObservableEmitter.onNext(new Click());
            }
        });
        initHotObservable();
    }

    private void initHotObservable(){
        Observable<Click> observable = Observable.create(new ObservableOnSubscribe<Click>() {
            @Override
            public void subscribe(ObservableEmitter<Click> emitter) throws Exception {
                mObservableEmitter = emitter;
            }
        });
//        mConnectableObservable  = observable.publish();
//        mConnectableObservable.connect();
        observable.subscribe(new Observer<Click>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Click click) {
                Toast.makeText(RxjavaActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
    }

}
