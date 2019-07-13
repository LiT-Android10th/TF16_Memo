package android.lifeistech.com.memo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class CreateActivity extends AppCompatActivity {

    // Realm
    public Realm realm;

    //　変数
    public EditText titleEditText;
    public EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // realmを開く
        realm = Realm.getDefaultInstance();

        // ひもづけ
        titleEditText = (EditText) findViewById(R.id.titileEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);
    }

    // データをRelamに保存する
    public void save(final String title, final String updateDate, final String content) {

        // メモを保存する
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.title = title;
                memo.updateDate = updateDate;
                memo.content = content;
            }
        });
    }

    // EditTextに入力されたデータをもとにMemoを作る
    public void create(View v) {
        // タイトルを取得する
        String title = titleEditText.getText().toString();

        // 日付を取得する
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String updateDate = sdf.format(date);

        // 内容を取得する
        String content = contentEditText.getText().toString();

        // 出力してみる
//        check(title, updateDate, content);

        // 保存する
        save(title, updateDate, content);

        // 画面を終了する
        finish();

    }

    private void check(String title, String updateDate, String content) {

        // Memoクラスのインスタンスを生成する
        Memo memo = new Memo();

        // それぞれの要素を代入する
        memo.title = title;
        memo.updateDate = updateDate;
        memo.content = content;

        // ログに出してみる
        Log.d("Memo", memo.title);
        Log.d("Memo", memo.updateDate);
        Log.d("Memo", memo.content);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // realmを閉じる
        realm.close();
    }
}