package cn.huafei.androidn.vr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

import cn.huafei.androidn.R;

public class VrVideoActivity extends AppCompatActivity {

    private VrVideoView mVrVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_video);
        mVrVideoView = (VrVideoView) findViewById(R.id.vr_video_view);
        try {
            mVrVideoView.loadVideoFromAsset("coongo.mp4");
            mVrVideoView.setEventListener(new VrVideoEventListener(){
                @Override
                public void onLoadSuccess() {
                    super.onLoadSuccess();
                    Log.i("VrVideoActivity", "onLoadSuccess: ");
                }

                @Override
                public void onClick() {
                    super.onClick();
                }

                @Override
                public void onDisplayModeChanged(int newDisplayMode) {
                    super.onDisplayModeChanged(newDisplayMode);
                }

                @Override
                public void onLoadError(String errorMessage) {
                    super.onLoadError(errorMessage);
                }

                @Override
                public void onNewFrame() {
                    super.onNewFrame();
                }

                @Override
                public void onCompletion() {
                    mVrVideoView.seekTo(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        VrVideoView.Options options = new VrVideoView.Options();
        options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;

    }
}
