package cn.huafei.newattribute.okhttp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.huafei.newattribute.R;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_get_post;
    private TextView tv_result;
    private Button btn_get_okhttputils;
    private Button btn_downloadfile;
    private ProgressBar mProgressBar;
    private Button btn_uploadfile;
    private ImageView iv_icon;
    private Button btn_image;
    private Button btn_image_list;


    private static final String URL_DOWNLOAD_FILE = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724055620533327_480.mp4";
    private static final String URL_TEXT = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
    private static final String URL_TEXT2 = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    private static final String URL_IMAGE = "http://images.csdn.net/20150817/1.jpg";
    public static final MediaType MEDIATYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        btn_get_post = (Button) findViewById(R.id.btn_get_post);
        btn_get_okhttputils = (Button) findViewById(R.id.btn_get_okhttputils);
        btn_downloadfile = (Button) findViewById(R.id.btn_downloadfile);
        btn_uploadfile = (Button) findViewById(R.id.btn_uploadfile);
        btn_image = (Button) findViewById(R.id.btn_image);
        btn_image_list = (Button) findViewById(R.id.btn_image_list);

        tv_result = (TextView) findViewById(R.id.tv_result);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //设置点击事件
        btn_get_post.setOnClickListener(this);
        btn_get_okhttputils.setOnClickListener(this);
        btn_downloadfile.setOnClickListener(this);
        btn_uploadfile.setOnClickListener(this);
        btn_image.setOnClickListener(this);
        btn_image_list.setOnClickListener(this);

        //okhttp
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_post://使用原生的okhttp请求网络数据，get和post
                tv_result.setText("");
                // getDataFromGet(URL_TEXT);
                getDataFromPost(URL_TEXT, "");//点击事件
                break;
            case R.id.btn_get_okhttputils:
                //getDataGetByOkhttpUtils(URL_TEXT);
                getDataPostByOkhttpUtils(URL_TEXT);
                break;
            case R.id.btn_downloadfile://下载文件
                downloadFile(URL_DOWNLOAD_FILE);
                break;
            case R.id.btn_uploadfile://文件上传
                //                String mBaseUrl = "http://192.168.0.165:8080/FileUpload/FileUploadServlet";
                //                File file = new File(Environment.getExternalStorageDirectory(), "afu.png");
                //                File file2 = new File(Environment.getExternalStorageDirectory(), "test.txt");
                //                Map<String,File> map=new HashMap<>();
                //                map.put("server_afu.png",file);
                //                map.put("server_test.txt",file2);
                //                multiFileUpload(mBaseUrl,map);
                break;
            case R.id.btn_image://请求单张图片
                getImage(URL_IMAGE);
                break;
            case R.id.btn_image_list://请求列表中的图片
                 Intent intent = new Intent(this,ImageListActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getImage(String url) {
        tv_result.setText("");
        OkHttpUtils.get().url(url).tag(this).build().connTimeOut(20000).readTimeOut(20000).writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tv_result.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        iv_icon.setImageBitmap(bitmap);
                    }
                });

    }

    private void multiFileUpload(String url, Map<String, File> files) {
        Map<String, String> params = new HashMap<>();
        params.put("username", "hahaha");
        params.put("password", "123");
        OkHttpUtils.post().url(url).params(params).files("mFile", files).build().execute(new MyStringCallback());
    }

    /**
     * 使用okhttputils的post请求文本
     *
     * @param urlTextUtils
     */
    private void getDataPostByOkhttpUtils(String urlTextUtils) {
        OkHttpUtils.post().url(urlTextUtils).id(10).build().execute(new MyStringCallback());
    }

    /**
     * 使用okhttputils的get请求文本
     *
     * @param urlTextUtils
     */
    private void getDataGetByOkhttpUtils(String urlTextUtils) {
        OkHttpUtils.get().url(urlTextUtils).build().execute(new MyStringCallback());
    }

    /**
     * 使用okhttputils下载大文件
     */
    private void downloadFile(String urlDownloadFile) {
        OkHttpUtils.get().url(URL_DOWNLOAD_FILE).build().execute(new MyFileCallback(Environment.getExternalStorageDirectory().getAbsolutePath(), "okhttpUtilst.mp4"));
    }

    /**
     * okhttp普通GET请求方法
     */
    private void getDataFromGet(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder().url(url).build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    final String content = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_result.setText(content);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * okhttp普通POST请求方法
     */
    private void getDataFromPost(final String url, final String json) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = RequestBody.create(MEDIATYPE_JSON, json);
                Request request = new Request.Builder().url(url).post(requestBody).build();
                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    final String content = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_result.setText(content);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class MyStringCallback extends StringCallback {

        public static final String TAG = "MyStringCallback";

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
            tv_result.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            tv_result.setText("onResponse:" + response);

            switch (id) {
                case 100:
                    Toast.makeText(getApplicationContext(), "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(getApplicationContext(), "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
            mProgressBar.setProgress((int) (100 * progress));
        }
    }

    class MyFileCallback extends FileCallBack {

        public static final String TAG = "MyFileCallback";

        public MyFileCallback(String destFileDir, String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            mProgressBar.setProgress((int) (100 * progress));
            Log.e(TAG, "inProgress :" + (int) (100 * progress));
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "onError :" + e.getMessage());
        }

        @Override
        public void onResponse(File file, int id) {
            Log.e(TAG, "onResponse :" + file.getAbsolutePath());
        }
    }
}
