package cn.huafei.androidn.notifaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.huafei.androidn.R;

/**
 * Created by Administrator on 2016/9/3 0003.
 */
public class MsgActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);
        CharSequence huafei = bundle.getCharSequence("huafei");
        Toast.makeText(MsgActivity.this, huafei, Toast.LENGTH_SHORT).show();

        System.out.print("huafei");

        //成功后写一个通知覆盖之前的通知
        //1.获取消息管理器
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("回复完成").build();
        //2.利用NotificationManager发送notification
        mNotificationManager.notify(10,notification);//id要和发送的id一致才能覆盖
    }
}
