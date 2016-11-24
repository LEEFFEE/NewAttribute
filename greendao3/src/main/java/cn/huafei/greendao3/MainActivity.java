package cn.huafei.greendao3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.huafei.greendao3.bean.User;
import cn.huafei.greendao3.gen.UserDao;
import cn.huafei.greendao3.manager.GreenDaoManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDao mUserDao;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.greendao_insert).setOnClickListener(this);
        findViewById(R.id.greendao_delete).setOnClickListener(this);
        findViewById(R.id.greendao_update).setOnClickListener(this);
        findViewById(R.id.greendao_query).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.content_tv);
        mUserDao = GreenDaoManager.getInstance().getDaoSession().getUserDao();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.greendao_insert:
                mUserDao.insert(new User(1L, "关羽0", "云长0"));
                mUserDao.insert(new User(2L, "关羽1", "云长1"));
                mUserDao.insert(new User(3L, "关羽2", "云长2"));
                mUserDao.insert(new User(4L, "关羽3", "云长3"));
                Toast.makeText(this, "成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.greendao_delete:
                mUserDao.deleteByKey(1L);
                Toast.makeText(this, "成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.greendao_update:
                mUserDao.update(new User(1L, "关羽11", "云长22"));
                Toast.makeText(this, "成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.greendao_query:
                //User user = mUserDao.loadByRowId(1l);
                List<User> users = mUserDao.loadAll();
                mTextView.setText(users.toString());
                Toast.makeText(this, "成功！", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
