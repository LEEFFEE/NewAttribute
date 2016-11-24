package cn.huafei.newattribute.html5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JsCallJavaVideoActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_js_call_java_video);
        initWebView();
    }

    private void initWebView() {
        mWebView = new WebView(this);
        WebSettings webSettings = mWebView.getSettings();
        //设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);

        //支持双击-前提是页面要支持才显示
        //        webSettings.setUseWideViewPort(true);
        //支持缩放按钮-前提是页面要支持才显示
        webSettings.setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        //设置支持js调用java
        mWebView.addJavascriptInterface(new AndroidAndJSInterface(), "android");
        //加载网络资源
        mWebView.loadUrl("file:///android_asset/RealNetJSCallJavaActivity.htm");
        setContentView(mWebView);
    }
    class AndroidAndJSInterface {
        @JavascriptInterface
        public void playVideo(int id,String videoUrl,String title){
            Log.d("JsCallJavaVideoActivity", "playVideo: "+id+",title"+title);
            //调起系统所有播放器
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(videoUrl),"video/*");
            startActivity(intent);
        }
    }
}
