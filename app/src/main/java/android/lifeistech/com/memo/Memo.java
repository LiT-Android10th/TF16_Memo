package android.lifeistech.com.memo;

import io.realm.RealmObject;

public class Memo extends RealmObject {

    // タイトル
    public String title;
    // 日付
    public String updateDate;
    //　内容
    public String content;

}
