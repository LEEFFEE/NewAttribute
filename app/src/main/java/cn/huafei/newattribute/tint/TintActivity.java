package cn.huafei.newattribute.tint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.TextView;

import cn.huafei.newattribute.BaseActivity;
import cn.huafei.newattribute.R;

public class TintActivity extends BaseActivity {

    private TextView mV1;
    private TextView mV2;
    private TextView mV3;
    private TextView mV4;
    private TextView mV5;
    private TextView mV6;

    @Override
    public View onCreateView() {
        View view = View.inflate(this, R.layout.activity_tint, null);
        mV1 = (TextView) view.findViewById(R.id.v1);
        mV2 = (TextView) view.findViewById(R.id.v2);
        mV3 = (TextView) view.findViewById(R.id.v3);
        mV4 = (TextView) view.findViewById(R.id.v4);
        mV5 = (TextView) view.findViewById(R.id.v5);
        mV6 = (TextView) view.findViewById(R.id.v6);
        return view;
    }

    @Override
    public void initData(View createdView) {

    }

    public void click(View v) {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
        // bm.getPixel(x,y);//获取某个点的像数值
        Palette.from(bm).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette p) {
                mV1.setText("1.Vibrant 鲜艳的");
                mV1.setBackgroundColor(p.getVibrantColor(Color.WHITE));

                mV2.setText("2.Vibrant dark鲜艳的暗色");
                mV2.setBackgroundColor(p.getDarkVibrantColor(Color.WHITE));

                mV3.setText("3.Vibrant light鲜艳的亮色");
                mV3.setBackgroundColor(p.getLightVibrantColor(Color.WHITE));

                mV4.setText("4.Muted 柔和的");
                mV4.setBackgroundColor(p.getMutedColor(Color.WHITE));

                mV5.setText("5.Muted dark柔和的暗色");
                mV5.setBackgroundColor(p.getDarkMutedColor(Color.WHITE));

                mV6.setText("6.Muted light柔和的亮色");
                mV6.setBackgroundColor(p.getLightMutedColor(Color.WHITE));
            }
        });
    }


}
