package com.github.wheroj.cloneevent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.wheroj.cloneevent.common.BuildCons;


/**
 * @Title:ViewHeadBar.java
 * @Description:公共LayoutHead
 * @Author:OliverTan(www.tandunzhao.cn)
 * @Date:16/4/11下午4:45
 * @Version V1.0
 */
public class ViewHeadBar extends LinearLayout {
    private RelativeLayout rlParentLayout;

    private FrameLayout llLeftLayout;
    public TextView tvLeft;
    public ImageView ivLeft;

    private LinearLayout llMiddleLayout;
    public ImageView ivLogo;
    public TextView tvTitle;
    private TextView tvLiiteTitle;

    public FrameLayout llRightLayout;
    public TextView tvRight;

    public ImageView ivRight;
    private OnClickListener onClickListener;

    public ViewHeadBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initView(context);
    }

    public ViewHeadBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public ViewHeadBar(Context context) {
        super(context);
        this.initView(context);
    }

    /**
     * 设置导航栏右边TextView文字
     *
     * @param text
     */
    public void setRightTextView(String text) {
        ivRight.setVisibility(View.GONE);
        tvRight.setText(text);
        tvRight.setVisibility(View.VISIBLE);
        llRightLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置导航栏右边TextView文字
     *
     * @param resId
     */
    public void setRightTextView(int resId) {
        ivRight.setVisibility(View.GONE);
        tvRight.setText(resId);
        tvRight.setVisibility(View.VISIBLE);
        llRightLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置导航栏右边HeadView
     *
     * @param resDrawable
     */
    public void setRightImageView(int resDrawable) {
        tvRight.setVisibility(View.GONE);
        ivRight.setImageResource(resDrawable);
        ivRight.setVisibility(View.VISIBLE);
        llRightLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置导航栏右边HeadView
     *
     * @param drawable
     */
    public void setRightImageView(Drawable drawable) {
        tvRight.setVisibility(View.GONE);
        ivRight.setImageDrawable(drawable);
        ivRight.setVisibility(View.VISIBLE);
        llRightLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置导航栏左边TextView文字
     *
     * @param text
     */
    public void setLeftTextView(String text) {
        ivLeft.setVisibility(View.GONE);
        tvLeft.setText(text);
        tvLeft.setVisibility(View.VISIBLE);
        llLeftLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置导航栏左边TextView文字
     *
     * @param resId
     */
    public void setLeftTextView(int resId) {
        ivLeft.setVisibility(View.GONE);
        tvLeft.setText(resId);
        tvLeft.setVisibility(View.VISIBLE);
        llLeftLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置导航栏左边HeadView
     *
     * @param resDrawable
     */
    public void setLeftImageView(int resDrawable) {
        tvLeft.setVisibility(View.GONE);
        ivLeft.setImageResource(resDrawable);
        ivLeft.setVisibility(View.VISIBLE);
        llLeftLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置导航栏左边HeadView
     *
     * @param drawable
     */
    public void setLeftImageView(Drawable drawable) {
        tvLeft.setVisibility(View.GONE);
        ivLeft.setImageDrawable(drawable);
        ivLeft.setVisibility(View.VISIBLE);
        llLeftLayout.setVisibility(View.VISIBLE);
    }

    public void hideRightLayout() {
        llRightLayout.setVisibility(View.INVISIBLE);
    }

    public void hideLeftLayout() {
        llLeftLayout.setVisibility(View.INVISIBLE);
    }

    public void showLeftImageBack() {
        tvLeft.setVisibility(View.GONE);
        ivLeft.setVisibility(View.VISIBLE);
        llLeftLayout.setVisibility(View.VISIBLE);
    }

    public void hideMiddleLayout() {
        llMiddleLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置导航栏居中标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (title == null) title = "";
        this.tvTitle.setText(title);
    }

    /**
     * 获取Title
     *
     * @return
     */
    public TextView getTitleTextView() {
        return this.tvTitle;
    }

    /**
     * 设置导航栏居中标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        this.tvTitle.setText(resId);
    }

    /**
     * 显示logo
     *
     * @param drawable
     */
    public void showLogo(Drawable drawable) {
        llMiddleLayout.setVisibility(View.VISIBLE);
        ivLogo.setImageDrawable(drawable);
    }

    /**
     * 显示logo
     *
     * @param resId
     */
    public void showLogo(int resId) {
        llMiddleLayout.setVisibility(View.VISIBLE);
        ivLogo.setVisibility(View.VISIBLE);
        ivLogo.setImageResource(resId);
    }

    /**
     * 显示右边icon
     */
    public void visibleRightImage(){
        ivRight.setVisibility(VISIBLE);
    }

    /**
     * 隐藏右边icon
     */
    public void invisibleRightImage(){
        ivRight.setVisibility(INVISIBLE);
    }

    /**
     * 隐藏右边icon
     */
    public void goneRightImage(){
        ivRight.setVisibility(GONE);
    }
    /**
     * Title的右边既显示文字也显示图标
     *
     * @param resId
     */
    public void showRightTextAndImage(String text, int resId) {
        if (text == null) text = "";
        llRightLayout.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(resId);
        tvRight.setText(text);
    }

    /**
     * Title显示二层小title
     */
    public void showMiddleLitteTitle(String title, String litteTitle) {
        if (title == null) title = "";
        if (litteTitle == null) litteTitle = "";
        llMiddleLayout.setVisibility(View.VISIBLE);
        ivLogo.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
        tvLiiteTitle.setVisibility(View.VISIBLE);
        tvLiiteTitle.setText(litteTitle);
        tvTitle.setText(title);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        this.rlParentLayout.setBackgroundResource(resId);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_head_bar, this);
        this.rlParentLayout = (RelativeLayout) this.findViewById(R.id.ViewHeadBar_rlParentLayout);

        this.llLeftLayout = (FrameLayout) this.findViewById(R.id.ViewHeadBar_llLeftLayout);
        this.tvLeft = (TextView) this.findViewById(R.id.ViewHeadBar_tvLeft);
        this.ivLeft = (ImageView) this.findViewById(R.id.ViewHeadBar_ivLeft);

        this.llMiddleLayout = (LinearLayout) this.findViewById(R.id.ViewHeadBar_llMiddleLayout);
        this.tvTitle = (TextView) this.findViewById(R.id.ViewHeadBar_tvTitle);
        this.tvLiiteTitle = (TextView) this.findViewById(R.id.ViewHeadBar_tvLittleTitle);
        tvTitle.setText(BuildCons.titleText);//默认显示的头文字
        this.ivLogo = (ImageView) this.findViewById(R.id.ViewHeadBar_ivLogo);

        this.llRightLayout = (FrameLayout) this.findViewById(R.id.ViewHeadBar_llRightLayout);
        this.tvRight = (TextView) this.findViewById(R.id.ViewHeadBar_tvRight);
        this.ivRight = (ImageView) this.findViewById(R.id.ViewHeadBar_ivRight);

        if (isInEditMode()) {
            return;
        }
        this.setOnClickListener();
    }

    private void setOnClickListener() {
        this.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v, ViewHeadType.CENTER_TEXT);
                }
            }
        });
        this.llLeftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v, ViewHeadType.LEFT_DEFAULT);
                }
            }
        });
        this.llRightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v, ViewHeadType.RIGHT_DEFAULT);
                }
            }
        });
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public enum ViewHeadType {
        LEFT_DEFAULT,
        CENTER_TEXT,
        RIGHT_DEFAULT
    }

    public interface OnClickListener {
        public void onClick(View v, ViewHeadType viewHeadType);
    }
}
