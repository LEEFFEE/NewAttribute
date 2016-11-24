package cn.huafei.newattribute.okhttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.huafei.newattribute.R;
import cn.huafei.newattribute.bean.DataBean;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/9/3 0003.
 */
public class ImageListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    private OKHttpListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagelist);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_imagelist);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    class OKHttpListAdapter extends RecyclerView.Adapter<OKHttpListAdapter.ViewHolder> {

        private final Context context;
        private final List<DataBean.ItemData> datas;

        public OKHttpListAdapter(Context context, List<DataBean.ItemData> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.item_okhttp_list_image, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setDate(datas.get(position));
        }

        @Override
        public int getItemCount() {
            if (datas != null)
                return datas.size();
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_icon;
            TextView tv_name;
            TextView tv_desc;

            public ViewHolder(View itemView) {
                super(itemView);
                iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            }

            public void setDate(DataBean.ItemData data) {
                tv_name.setText(data.getMovieName());
                tv_desc.setText(data.getVideoTitle());
                //在列表中使用okhttp-utils请求图片
                OkHttpUtils.get().url(data.getCoverImg()).tag(this).build().connTimeOut(20000).readTimeOut(20000).writeTimeOut(20000)
                        .execute(new BitmapCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                //tv_result.setText("onError:" + e.getMessage());
                            }

                            @Override
                            public void onResponse(Bitmap bitmap, int id) {
                                Log.e("TAG", "onResponse：complete");
                                iv_icon.setImageBitmap(bitmap);
                            }
                        });
            }
        }
    }

    class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id) {
            switch (id) {
                case 100:
                    Toast.makeText(getApplicationContext(), "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(getApplicationContext(), "https", Toast.LENGTH_SHORT).show();
                    break;
            }
            //解析数据和显示数据
            if (response != null) {
                //缓存数据
                processData(response);

            }
        }
    }

    private void processData(String json) {
        //解析数据
        DataBean dataBean = parsedJson(json);
        List<DataBean.ItemData> datas =  dataBean.getTrailers();

        if(datas != null && datas.size() >0){
            //有数据
            //显示适配器
            adapter = new OKHttpListAdapter(this,datas);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else{
            //没有数据
            Toast.makeText(ImageListActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * 解析json数据
     *
     * @param response
     * @return
     */
    private DataBean parsedJson(String response) {
        DataBean dataBean = new DataBean();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("trailers");
            if (jsonArray != null && jsonArray.length() > 0) {
                List<DataBean.ItemData> trailers = new ArrayList<>();
                dataBean.setTrailers(trailers);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);

                    if (jsonObjectItem != null) {

                        DataBean.ItemData mediaItem = new DataBean.ItemData();

                        String movieName = jsonObjectItem.optString("movieName");//name
                        mediaItem.setMovieName(movieName);

                        String videoTitle = jsonObjectItem.optString("videoTitle");//desc
                        mediaItem.setVideoTitle(videoTitle);

                        String imageUrl = jsonObjectItem.optString("coverImg");//imageUrl
                        mediaItem.setCoverImg(imageUrl);

                        String hightUrl = jsonObjectItem.optString("hightUrl");//data
                        mediaItem.setHightUrl(hightUrl);

                        //把数据添加到集合
                        trailers.add(mediaItem);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBean;
    }
}