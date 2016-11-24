package cn.huafei.androidn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.huafei.androidn.directory.DirActivity;
import cn.huafei.androidn.notifaction.NotifactionActivity;
import cn.huafei.androidn.recycleview.RecyclerAdapter;
import cn.huafei.androidn.recycleview.RecyclerHolder;
import cn.huafei.androidn.recycleview.RecyclerItemDecoration;
import cn.huafei.androidn.vr.VrVideoActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<MapClass> mMapClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        initAdapter();
    }

    private void initData() {
        mMapClasses = new ArrayList<>();
        mMapClasses.add(new MapClass("1.通知可以回复", NotifactionActivity.class));
        mMapClasses.add(new MapClass("2.访问存储目录", DirActivity.class));
        mMapClasses.add(new MapClass("3.VR视频播放", VrVideoActivity.class));//arm机器
    }

    private void initAdapter() {
        MyAdapter adapter = new MyAdapter(mMapClasses);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), mMapClasses.get(position).clazz);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //1.添加默认分割线：高度为2px，颜色为灰色
         mRecyclerView.addItemDecoration(new RecyclerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        //       // 2.添加自定义分割线：可自定义分割线drawable
//                mRecyclerView.addItemDecoration(new RecyclerItemDecoration(
//                        this, LinearLayoutManager.HORIZONTAL, R.drawable.divider));
        //   3.添加自定义分割线：可自定义分割线高度和颜色
       //mRecyclerView.addItemDecoration(new RecyclerItemDecoration(
             //   this, LinearLayoutManager.HORIZONTAL, 10, Color.RED));

    }

    class MyAdapter extends RecyclerAdapter<MapClass> {

        public MyAdapter(List<MapClass> mDatas) {
            super(getApplicationContext(), mDatas, R.layout.item_recycleview);
        }

        @Override
        public void convert(RecyclerHolder holder, MapClass data, int position) {
            holder.setText(R.id.item_text1, data.name);
        }
    }


    class MapClass {
        String name;
        Class clazz;

        public MapClass(String name, Class clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
