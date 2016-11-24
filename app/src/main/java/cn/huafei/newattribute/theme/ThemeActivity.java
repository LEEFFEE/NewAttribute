package cn.huafei.newattribute.theme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.huafei.newattribute.R;

public class ThemeActivity extends AppCompatActivity {

    private int mTheme;
    private Toolbar mToolbar;
    private String mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTheme = getIntent().getIntExtra("theme", -1);
        if (mTheme != -1) {
            setTheme(mTheme);
        }
        setContentView(R.layout.activity_theme);
        mToolbar = (Toolbar) findViewById(R.id.theme_toolbar);
        mTitle = getIntent().getStringExtra("title");
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
    }

    public void redTheme(View v) {
        finish();
        overridePendingTransition(0, 0);//没有动画
        Intent intent = new Intent(this, ThemeActivity.class);
        intent.putExtra("theme", R.style.redTheme);
        intent.putExtra("title", mTitle);
        startActivity(intent);
    }

    public void blueTheme(View v) {
        finish();
        overridePendingTransition(0, 0);//没有动画
        Intent intent = new Intent(this, ThemeActivity.class);
        intent.putExtra("theme", R.style.blueTheme);
        intent.putExtra("title", mTitle);
        startActivity(intent);
    }
}
