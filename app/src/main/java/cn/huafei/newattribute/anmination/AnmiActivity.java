package cn.huafei.newattribute.anmination;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

import cn.huafei.newattribute.BaseActivity;
import cn.huafei.newattribute.R;

public class AnmiActivity extends BaseActivity {

    //    1. view 操作的视图
//    2. centerX 动画开始的中心点X
//    3. centerY 动画开始的中心点Y
//    4. startRadius 动画开始半径
//    5. startRadius 动画结束半径
    @Override
    public View onCreateView() {
        View view = View.inflate(this, R.layout.activity_anmi, null);
        return view;
    }

    @Override
    public void initData(View createdView) {
        View triangle = findViewById(R.id.triangle);
        Drawable drawable = triangle.getBackground();
        if (drawable instanceof Animatable){
            ((Animatable) drawable).start();
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void click(View v){
        int centerX = v.getWidth() / 2;
        int centerY = v.getHeight() / 2;
        float startRadius = 0;
        float endRadius = v.getWidth();
        Animator animator = ViewAnimationUtils.createCircularReveal(v, centerX,
                centerY, startRadius, endRadius);
        animator.setDuration(3000);
        animator.start();
    }
}
