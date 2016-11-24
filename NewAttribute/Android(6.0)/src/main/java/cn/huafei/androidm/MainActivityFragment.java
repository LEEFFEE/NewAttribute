package cn.huafei.androidm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.huafei.androidm.databind.DataBindActivity;
import cn.huafei.androidm.recycleview.RecyclerAdapter;
import cn.huafei.androidm.recycleview.RecyclerHolder;
import cn.huafei.androidm.recycleview.RecyclerItemDecoration;
import cn.huafei.androidm.scroll.ScrollActivity;
import cn.huafei.androidm.tablayout.TabLayoutActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView mRecyclerView;

    ArrayList<MapClass> mMapClasses;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initData();
        initAdapter();

    }

    private void initData() {
        mMapClasses = new ArrayList<>();
        mMapClasses.add(new MapClass("1.数据绑定", DataBindActivity.class));
        mMapClasses.add(new MapClass("2.滚动+折叠布局", ScrollActivity.class));
        mMapClasses.add(new MapClass("3.TabLayout", TabLayoutActivity.class));
    }

    private void initAdapter() {
        MyAdapter adapter = new MyAdapter(mMapClasses);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                Intent intent=new Intent(getActivity(),mMapClasses.get(position).clazz);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //1.添加默认分割线：高度为2px，颜色为灰色
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//       // 2.添加自定义分割线：可自定义分割线drawable
//        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(
//                getContext(), LinearLayoutManager.VERTICAL, R.drawable.divider));
        //3.添加自定义分割线：可自定义分割线高度和颜色
//        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(
//                getContext(), LinearLayoutManager.VERTICAL, 2, Color.RED));

    }

    class MyAdapter extends RecyclerAdapter<MapClass> {

        public MyAdapter(List<MapClass> mDatas) {
            super(getContext(), mDatas, R.layout.item_recycleview);
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
