package com.github.wheroj.cloneevent;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.juyuejk.core.annotation.Injector;
import com.juyuejk.core.base.BFActivity;

public abstract class BaseActivity extends BFActivity {
	protected ViewHeadBar viewHeadBar;
	protected LinearLayout llContainer;
	private boolean hasTitle = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.hasTitle = getHasTitle();
		super.onCreate(savedInstanceState);
		this.gainView();
		try {
			if (hasTitle) {
				View container = View.inflate(this, getContainerLayoutId(), null);
				LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				llContainer.addView(container, layoutParams);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Injector.inject(thisContext, thisContext);
			init();
			initData();
		}
	}

	protected abstract int getContainerLayoutId();

	/**
	 * 初始化界面
	 */
	protected abstract void init();

	/**
	 * 点击ViewHead导航栏右边
	 */
	protected void onClickRight() {
	}

	/**
	 * 点击ViewHead导航栏左边，默认结束当前Activity
	 */
	protected void onClickLeft() {
		finish();
	}

	/**
	 * 点击ViewHead导航栏中间Title
	 */
	protected void onClickTitle() {
	}

	private void gainView() {
		if (hasTitle) {
			viewHeadBar = (ViewHeadBar) this.findViewById(R.id.ActivityBaseHead_viewHeadBar);
			llContainer = (LinearLayout) this.findViewById(R.id.ActivityBaseHead_llContainer);
			viewHeadBar.setBackgroundResource(R.drawable.mine_gradient_background);
			viewHeadBar.setOnClickListener(new ViewHeadBar.OnClickListener() {
				@Override
				public void onClick(View v, ViewHeadBar.ViewHeadType viewHeadType) {
					switch (viewHeadType) {
						case CENTER_TEXT:
							onClickTitle();
							break;
						case LEFT_DEFAULT:
							onClickLeft();
							break;
						case RIGHT_DEFAULT:
							onClickRight();
							break;
					}
				}
			});
		}
	}

	@Override
	protected final int getLayoutId() {
		if (hasTitle) {
			return R.layout.activity_base_head;
		} else {
			return getContainerLayoutId();
		}
	}

	/**
	 * 设置该界面是否有title:true 有title显示   false 没有title
	 *
	 * @return
	 */
	protected abstract boolean getHasTitle();

	/**
	 * 初始化要显示的数据
	 */
	protected abstract void initData();
}
