package cn.huafei.newattribute.shadow_clip;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import cn.huafei.newattribute.BaseActivity;
import cn.huafei.newattribute.R;

public class ShadowClipActivity extends BaseActivity {

    private View mView;


    @Override
    public View onCreateView() {
        View view = View.inflate(this, R.layout.activity_shadow_clip, null);
        mView = view.findViewById(R.id.ivHead);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData(View createdView) {
        mView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getHeight() - 20, view.getWidth() - 20);
            }
        });
        mView.setClipToOutline(true);
    }
}
