package cn.huafei.androidm.databind;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.huafei.androidm.R;
import cn.huafei.androidm.bean.User;
import cn.huafei.androidm.databinding.ActivityDataBindBinding;

public class DataBindActivity extends AppCompatActivity {

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        mUser = new User("zhangsan","30");
        mUser.setPhone("1587964235");
        mUser.setAddress("shengzhen");
        binding.setUser(mUser);
        binding.setGender("男");

    }
    public void click(View view){
        Toast.makeText(DataBindActivity.this, mUser.toString(), Toast.LENGTH_SHORT).show();
    }
}
