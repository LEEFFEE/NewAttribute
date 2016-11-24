package cn.huafei.androidn.notifaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.huafei.androidn.R;

public class NotifactionActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifaction);
        findViewById(R.id.btn_notifaction).setOnClickListener(this);
        findViewById(R.id.btn_notifaction_reply).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.btn_notifaction:
                notifaction();
                break;
            case R.id.btn_notifaction_reply:
                replyNoti();
                break;
            default:
                break;
        }
    }

    private void notifaction() {
        Toast.makeText(NotifactionActivity.this, "hahha", Toast.LENGTH_SHORT).show();
        //1.获取消息管理器
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("标题")
                .setContentText("内容").build();
        //6.利用NotificationManager发送notification
        mNotificationManager.notify(10,notification);
    }

    private void replyNoti() {
        //1.获取消息管理器
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //2.创建一个intent对象，用来处理用户回复之后的操作
        Intent intent = new Intent(this,MsgActivity.class);
        //3.创建一个RemoteInput对象，用来对回复的内容进行加密
        RemoteInput remoteInput=new RemoteInput.Builder("huafei").build();
        //4.创建一个Action对象 指定一个PendingIntent，这个对象可以指定用户点击发送之后，处理那个intent对象
        Notification.Action action = new Notification.Action.Builder(R.mipmap.ic_launcher, "请输入回复内容", PendingIntent.getActivity(this, 1001, intent, 1)).addRemoteInput(remoteInput).build();
        //5.创建一个notification
        Notification notification = new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("标题")
                .setContentText("内容,可回复哦！！！").addAction(action).build();
        //6.利用NotificationManager发送notification
        mNotificationManager.notify(10,notification);
    }
}
