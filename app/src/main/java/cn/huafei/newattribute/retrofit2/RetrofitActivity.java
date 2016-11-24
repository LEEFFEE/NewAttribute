package cn.huafei.newattribute.retrofit2;

import android.view.View;
import android.widget.TextView;

import cn.huafei.newattribute.BaseActivity;
import cn.huafei.newattribute.R;
import cn.huafei.newattribute.bean.Classify;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    public View onCreateView() {
        View view = View.inflate(this, R.layout.activity_retrofit, null);
        mTextView = (TextView) view.findViewById(R.id.retrofit_tv);
        return view;
    }

    @Override
    public void initData(View createdView) {
        //demo1();
        int a=9;
    }

    private void demo1() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.tngou.net/").addConverterFactory(GsonConverterFactory.create())
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler())
                .build();
        ServicesApi api = retrofit.create(ServicesApi.class);
        Call<Classify> apiClassify = api.getClassify();
        //1.同步请求
        // apiClassify.execute();
        //2.异步请求
        apiClassify.enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                //2.1取出响应正文
//                Classify body = response.body();
//                int size = body.getTngou().size();
//                String description = body.getTngou().get(0).getDescription();
//                mTextView.setText(size+"@@"+description);
                //2.2取出响应头
//                Headers headers = response.headers();
//                Set<String> names = headers.names();
//                Iterator<String> iterator = names.iterator();
//                String next = iterator.next();
//                List<String> values = headers.values(next);
//                mTextView.setText(next+"**"+values);

                //2.3获取请求url
//                String s = call.request().url().toString();
//                mTextView.setText(s);
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {

            }
        });
    }

}
