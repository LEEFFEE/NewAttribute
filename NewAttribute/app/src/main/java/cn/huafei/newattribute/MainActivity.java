package cn.huafei.newattribute;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cn.huafei.newattribute.anmination.AnmiActivity;
import cn.huafei.newattribute.html5.H5Activity;
import cn.huafei.newattribute.okhttp.OkhttpActivity;
import cn.huafei.newattribute.recycleview.RecycleViewActivity;
import cn.huafei.newattribute.retrofit2.RetrofitActivity;
import cn.huafei.newattribute.rxandroid.RxandroidActivity;
import cn.huafei.newattribute.shadow_clip.ShadowClipActivity;
import cn.huafei.newattribute.theme.ThemeActivity;
import cn.huafei.newattribute.tint.TintActivity;
import cn.huafei.newattribute.toolbar.ToolbarActivity;
import cn.huafei.newattribute.ws.WebServiceActivity;

public class MainActivity extends ListActivity {

    static ArrayList<NameAndClass> datas;

    static {
        datas = new ArrayList<NameAndClass>();
        datas.add(new NameAndClass("主题", ThemeActivity.class));
        datas.add(new NameAndClass("ToolBar设置给Actionbar", ToolbarActivity.class));
        datas.add(new NameAndClass("阴影和剪裁", ShadowClipActivity.class));
        datas.add(new NameAndClass("tint属性及Palette调色板", TintActivity.class));
        datas.add(new NameAndClass("全新的动画", AnmiActivity.class));
        datas.add(new NameAndClass("RecyclerView+CardView+SwipeRefreshLayout", RecycleViewActivity.class));
        datas.add(new NameAndClass("OKHttp+OKHttpUtils的使用", OkhttpActivity.class));
        datas.add(new NameAndClass("RxAndroid的使用", RxandroidActivity.class));
        datas.add(new NameAndClass("HTML5与Android互调(WebView)", H5Activity.class));
        datas.add(new NameAndClass("WebService调用", WebServiceActivity.class));
        datas.add(new NameAndClass("Retrofit2使用", RetrofitActivity.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        NameAndClass item = (NameAndClass) l.getItemAtPosition(position);
        Intent intent = new Intent(this, item.clazz);
        intent.putExtra("title", item.title);
        startActivity(intent);
    }

    static class NameAndClass {
        String title;
        Class<?> clazz;

        public NameAndClass(String title, Class clazz) {
            this.title = title;
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}