package cn.huafei.androidm.scroll;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import java.util.ArrayList;

import cn.huafei.androidm.R;
import cn.huafei.androidm.recycleview.RecyclerAdapter;
import cn.huafei.androidm.recycleview.RecyclerHolder;
import cn.huafei.androidm.recycleview.RecyclerItemDecoration;

public class ScrollActivity extends Activity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scroll);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        mCollapsingToolbarLayout.setTitle("Hello World");
        //展开的颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.GREEN);
        //折叠的颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.YELLOW);

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            datas.add("我是条目" + i);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(new RecyclerAdapter<String>(this, datas, R.layout.item_recycleview) {
            @Override
            public void convert(RecyclerHolder holder, String data, int position) {
                holder.setText(R.id.item_text1,data);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }
}
