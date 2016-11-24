package cn.huafei.newattribute.html5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.huafei.newattribute.R;

public class JsCallJavaCallPhoneActivity extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_java_call_phone);
        //        webView = new WebView(this);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        //设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);

        //支持双击-前提是页面要支持才显示
        //        webSettings.setUseWideViewPort(true);

        //支持缩放按钮-前提是页面要支持才显示
        webSettings.setBuiltInZoomControls(true);

        //设置客户端-不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());

        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");

        //加载本地资源
        webView.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
        //        webView.loadUrl("http://10.0.2.2:8080/assets/JsCallJavaCallPhone.html");
    }

    class AndroidAndJSInterface {
        /**
         * 该方法将被js调用,用于加载数据
         */
        @JavascriptInterface
        public void showcontacts() {
            Log.d("JsCallJavaCallPhone", "showcontacts: hhhhhhhhhhhhhhh");
            // 下面的代码建议在子线程中调用
            String json = "[{\"name\":\"fei\", \"phone\":\"18600012345\"}]";
            // 调用JS中的方法
            webView.loadUrl("javascript:show('" + json + "')");
        }

        /**
         * 拨打电话
         *
         * @param phone
         */
        @JavascriptInterface
        public void call(String phone) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(JsCallJavaCallPhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    }
}
