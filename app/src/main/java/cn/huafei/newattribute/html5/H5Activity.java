package cn.huafei.newattribute.html5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.huafei.newattribute.R;

public class H5Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        findViews();
    }

    private Button btnJavaAndJs;
    private Button btnJsCallJava;
    private Button btnJsCallPhone;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-09-04 15:28:18 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        btnJavaAndJs = (Button) findViewById(R.id.btn_java_and_js);
        btnJsCallJava = (Button) findViewById(R.id.btn_js_call_java);
        btnJsCallPhone = (Button) findViewById(R.id.btn_js_call_phone);

        btnJavaAndJs.setOnClickListener(this);
        btnJsCallJava.setOnClickListener(this);
        btnJsCallPhone.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-09-04 15:28:18 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnJavaAndJs) {
            // Handle clicks for btnJavaAndJs
            startActivity(new Intent(this,JavaAndJSActivity.class));
        } else if (v == btnJsCallJava) {
            // Handle clicks for btnJsCallJava
            startActivity(new Intent(this,JsCallJavaVideoActivity.class));
        } else if (v == btnJsCallPhone) {
            // Handle clicks for btnJsCallPhone
            startActivity(new Intent(this,JsCallJavaCallPhoneActivity.class));
        }
    }


}
