package cn.huafei.androidn.directory;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.huafei.androidn.R;

public class DirActivity extends AppCompatActivity {

    public static final String TAG = "DirActivity";
    private StorageManager mStorageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dir);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: hahah");
            }
        }).start();
        //Lambda表达式需要在gradle中设置useJack(true)
//        new Thread(()->{
//            System.out.print("");
//            Log.i(TAG, "run: Lambda表达式输出");
//        }).start();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void click(View view) {
        mStorageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
        StorageVolume primaryStorageVolume = mStorageManager.getPrimaryStorageVolume();//getPrimaryStorageVolume:API24
        Intent intent = primaryStorageVolume.createAccessIntent(Environment.DIRECTORY_DOWNLOADS);//createAccessIntent:API24
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 0) {
                Toast.makeText(DirActivity.this, "拒绝访问", Toast.LENGTH_SHORT).show();
            } else if (resultCode == -1) {
                Toast.makeText(DirActivity.this, "同意访问", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
