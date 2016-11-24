package cn.huafei.newattribute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseActivity extends AppCompatActivity {


    protected Toolbar mToolbar;
    protected FrameLayout mFrameLayout;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        String mTitle = getIntent().getStringExtra("title");
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);

        mFrameLayout = (FrameLayout) findViewById(R.id.detail_frame_layout);
        View child = onCreateView();
        if (child != null)
            mFrameLayout.addView(child);
        initData(child);
    }

    public abstract View onCreateView();

    public abstract void initData(View createdView);

}
