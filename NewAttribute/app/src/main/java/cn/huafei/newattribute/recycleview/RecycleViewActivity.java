package cn.huafei.newattribute.recycleview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import cn.huafei.newattribute.BaseActivity;
import cn.huafei.newattribute.R;

public class RecycleViewActivity extends BaseActivity {
    private int[] mListIcons = new int[]{R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4,
            R.mipmap.g5, R.mipmap.g6, R.mipmap.g7, R.mipmap.g8, R.mipmap.g9, R.mipmap.g10, R
            .mipmap.g11, R.mipmap.g12, R.mipmap.g13, R.mipmap.g14, R.mipmap.g15, R.mipmap
            .g16, R.mipmap.g17, R.mipmap.g18, R.mipmap.g19, R.mipmap.g20, R.mipmap.g21, R
            .mipmap.g22, R.mipmap.g23, R.mipmap.g24, R.mipmap.g25, R.mipmap.g26, R.mipmap
            .g27, R.mipmap.g28, R.mipmap.g29};

    private int[] mStraggeredIcons = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R
            .mipmap.p4, R.mipmap.p5, R.mipmap.p6, R.mipmap.p7, R.mipmap.p8, R.mipmap.p9, R
            .mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p13, R.mipmap.p14, R.mipmap
            .p15, R.mipmap.p16, R.mipmap.p17, R.mipmap.p18, R.mipmap.p19, R.mipmap.p20, R
            .mipmap.p21, R.mipmap.p22, R.mipmap.p23, R.mipmap.p24, R.mipmap.p25, R.mipmap
            .p26, R.mipmap.p27, R.mipmap.p28, R.mipmap.p29, R.mipmap.p30, R.mipmap.p31, R
            .mipmap.p32, R.mipmap.p33, R.mipmap.p34, R.mipmap.p35, R.mipmap.p36, R.mipmap
            .p37, R.mipmap.p38, R.mipmap.p39, R.mipmap.p40, R.mipmap.p41, R.mipmap.p42, R
            .mipmap.p43, R.mipmap.p44};
    private RecyclerView mRecyclerView;
    private ArrayList<DataBean> mList;
    private ArrayList<DataBean> mStraggerDatas;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView() {
        View view = View.inflate(this, R.layout.activity_recycle_view, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        return view;
    }

    @Override
    public void initData(View createdView) {
        //list或者grid的集合
        mList = new ArrayList<>();
        for (int i = 0; i < mListIcons.length; i++) {
            mList.add(new DataBean("我是item" + i, mListIcons[i]));
        }

        //瀑布流集合
        mStraggerDatas = new ArrayList<>();
        for (int i = 0; i < mStraggeredIcons.length; i++) {
            mStraggerDatas.add(new DataBean("我是item" + i, mStraggeredIcons[i]));
        }
        setListAdapter(false, true, R.layout.item_list_vertical);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mList.add(0, new DataBean("我是刷新出来的数据", R.mipmap.ic_launcher));
                        mStraggerDatas.add(0, new DataBean("我是刷新出来的数据", R.mipmap.ic_launcher));
                        //得到adapter.然后刷新
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        //停止刷新操作
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }).start();

            }
        });
    }

    /**
     * 设置List数据及布局
     *
     * @param reverseLayout 反向布局，默认不反向（也就是默认从下往上滑）
     * @param vertical      vertical=false表示垂直方式，默认为true
     * @param layoutId      layoutId条目的布局
     */
    public void setListAdapter(boolean reverseLayout, boolean vertical, int layoutId) {
        RecyclerAdapter adapter = new RecyclerAdapter(this, mList, layoutId);
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(vertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        mManager.setReverseLayout(reverseLayout);
        mRecyclerView.setLayoutManager(mManager);


    }

    /**
     * 设置Grid数据及布局
     *
     * @param reverseLayout 反向布局，默认不反向（也就是默认从下往上滑）
     * @param vertical      vertical=false表示垂直方式，默认为true
     * @param layoutId      layoutId条目的布局
     * @param spanCount     The number of columns in the grid
     */
    public void setGridAdapter(boolean reverseLayout, boolean vertical, int layoutId, int spanCount) {
        RecyclerAdapter adapter = new RecyclerAdapter(this, mList, layoutId);
        mRecyclerView.setAdapter(adapter);

        GridLayoutManager mManager = new GridLayoutManager(this, spanCount);
        mManager.setOrientation(vertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        mManager.setReverseLayout(reverseLayout);
        mRecyclerView.setLayoutManager(mManager);
    }

    /**
     * 设置Stagger数据及布局
     *
     * @param reverseLayout 反向布局，默认不反向（也就是默认从下往上滑）
     * @param vertical      vertical=false表示垂直方式，默认为true
     * @param layoutId      layoutId条目的布局
     * @param spanCount     If orientation is vertical, spanCount is number of columns. If
     *                      orientation is horizontal, spanCount is number of rows.
     */
    public void setStaggerAdapter(boolean reverseLayout, boolean vertical, int layoutId, int spanCount) {
        RecyclerAdapter adapter = new RecyclerAdapter(this, mStraggerDatas, layoutId);
        mRecyclerView.setAdapter(adapter);

        int orientation = vertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL;
        StaggeredGridLayoutManager mManager = new StaggeredGridLayoutManager(spanCount, orientation);
        mManager.setReverseLayout(reverseLayout);
        mRecyclerView.setLayoutManager(mManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.layout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //List 列表
            case R.id.action_list_vertical:
                setListAdapter(false, true, R.layout.item_list_vertical);
                break;
            case R.id.action_list_vertical_reverse:
                setListAdapter(true, true, R.layout.item_list_vertical);
                break;
            case R.id.action_list_horizontal:
                setListAdapter(false, false, R.layout.item_list_horizontal);
                break;
            case R.id.action_list_horizontal_reverse:
                setListAdapter(true, false, R.layout.item_list_horizontal);
                //Grid 网格
                break;
            case R.id.action_grid_vertical:
                setGridAdapter(false, true, R.layout.item_grid_vertical, 3);
                break;
            case R.id.action_grid_vertical_reverse:
                setGridAdapter(true, true, R.layout.item_grid_vertical, 3);
                break;
            case R.id.action_grid_horizontal:
                setGridAdapter(false, false, R.layout.item_grid_horizontal, 3);
                break;
            case R.id.action_grid_horizontal_reverse:
                setGridAdapter(false, false, R.layout.item_grid_horizontal, 3);
                break;
            //瀑布流
            case R.id.action_stragger_vertical:
                setStaggerAdapter(false, true, R.layout.item_stragger_vertical, 2);
                break;
            case R.id.action_stragger_vertical_reverse:
                setStaggerAdapter(true, true, R.layout.item_stragger_vertical, 2);
                break;
            case R.id.action_stragger_horizontal:
                setStaggerAdapter(false, false, R.layout.item_stragger_horizontal, 3);
                break;
            case R.id.action_stragger_horizontal_reverse:
                setStaggerAdapter(true, false, R.layout.item_stragger_horizontal, 3);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class DataBean {
        public String content;
        public int iconId;

        public DataBean(String content, int iconId) {
            this.content = content;
            this.iconId = iconId;
        }
    }
}
