package cn.huafei.androidl.fragment;


import android.view.View;

import cn.huafei.androidl.R;


public class SupportFragment extends BaseFragment{
	protected View initView(){
		return View.inflate(getActivity(), R.layout.fragment_support,null);
	}
	@Override
	public String getUrl() {
		return "file:///android_asset/support.html";
	}
}
