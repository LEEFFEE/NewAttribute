package cn.huafei.newattribute.toolbar;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import cn.huafei.newattribute.BaseActivity;
import cn.huafei.newattribute.R;

public class ToolbarActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    public View onCreateView() {
        View view = View.inflate(this, R.layout.activity_toobar, null);
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.toolbar_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mToggle.syncState();
        //mDrawerLayout.setDrawerListener(mToggle);
        mDrawerLayout.addDrawerListener(mToggle);
        return view;
    }

    @Override
    public void initData(View createdView) {

    }
}
