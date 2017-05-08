package cn.huafei.newattribute.vlayout;

import android.content.Intent;
import android.view.View;

import cn.huafei.newattribute.BaseActivity;
import cn.huafei.newattribute.R;

public class VLayoutListActivity extends BaseActivity {
    @Override
    public View onCreateView() {
        View view = View.inflate(this, R.layout.activity_vlayout_list, null);
        return view;
    }

    @Override
    public void initData(View createdView) {
        findViewById(R.id.v_layout_simple_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VLayoutSimpleActivity.class));
            }
        });
        findViewById(R.id.v_layout_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VLayoutActivity.class));
            }
        });
    }
}
