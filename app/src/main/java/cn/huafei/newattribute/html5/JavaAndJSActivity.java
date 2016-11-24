package cn.huafei.newattribute.html5;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.huafei.newattribute.R;

public class JavaAndJSActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;
    private WebView mWebView;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-09-04 15:55:33 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_and_js);
        findViews();
        initWebView();
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-09-04 15:55:33 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            String number = etNumber.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
                Toast.makeText(JavaAndJSActivity.this, "账号或者密码为空", Toast.LENGTH_SHORT).show();
            } else {
                //登录
                login(number);
            }
        }
    }

    private void login(String number) {
        mWebView.loadUrl("javascript:javaCallJs('" + number + "')");
        setContentView(mWebView);
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
        //        webView.loadUrl("http://10.0.2.2:8080/assets/JavaAndJavaScriptCall.html");
        mWebView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
        //setContentView(mWebView);
    }

    class AndroidAndJSInterface {
        @JavascriptInterface
        public void showToast() {
            Toast.makeText(JavaAndJSActivity.this, "我被js调用了", Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void showToast(String msg) {
            Toast.makeText(JavaAndJSActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
