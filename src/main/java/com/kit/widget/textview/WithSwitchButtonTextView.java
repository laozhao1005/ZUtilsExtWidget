package com.kit.widget.textview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kit.extend.widget.R;
import com.kit.utils.ApiLevel;
import com.kit.utils.DensityUtils;
import com.kit.utils.StringUtils;

public class WithSwitchButtonTextView extends LinearLayout {

    // private ImageView ivGo;
    // private EditText et;
    private TextView tvTitle, tvContent;
    private ImageButton ibInfo;
    private String contentString, WithSwitchButtonTextView_title;
    private Drawable WithSwitchButtonTextViewDeleteIcon,
            WithSwitchButtonTextView_background, goSrc;

    private RelativeLayout llWithSwitchButtonTextView;
    private RelativeLayout rlEditText;

    private boolean is_content_text_left;
    private int title_size, content_size, title_margin_right,
            content_margin, content_margin_left, content_margin_right,
            margin, margin_left, margin_right;

    private int title_color, content_color;

    public SwitchCompat switchButton;

    public WithSwitchButtonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 方式1获取属性
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.WithSwitchButtonTextView);

        title_color = a
                .getColor(
                        R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_title_color,
                        getResources().getColor(R.color.black));

        content_color = a
                .getColor(
                        R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_content_color,
                        getResources().getColor(R.color.black));

        WithSwitchButtonTextView_background = a
                .getDrawable(R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_background);

        WithSwitchButtonTextView_title = a
                .getString(R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_title);

        contentString = a
                .getString(R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_content);

        is_content_text_left = a
                .getBoolean(
                        R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_is_content_text_left,
                        true);


        title_size = (int) a.getDimension(
                R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_title_size, -1);

        content_size = (int) a.getDimension(
                R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_content_size, -1);

        margin = (int) a.getDimension(
                R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_margin, -1);

        margin_left = (int) a.getDimension(
                R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_margin_left, 0);

        margin_right = (int) a.getDimension(
                R.styleable.WithSwitchButtonTextView_WithSwitchButtonTextView_margin_right, 0);
        a.recycle();

         LayoutInflater.from(context).inflate(
                R.layout.with_switchbutton_textview, this);

        llWithSwitchButtonTextView =  findViewById(R.id.llWithSwitchButtonTextView);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ibInfo = (ImageButton) findViewById(R.id.ibInfo);

        tvContent = (TextView) findViewById(R.id.tvContent);

        rlEditText = (RelativeLayout) findViewById(R.id.rlEditText);

        switchButton = (SwitchCompat) findViewById(R.id.switchButton);

        if (WithSwitchButtonTextView_background != null) {
            if (ApiLevel.ATLEAST_JELLY_BEAN) {
                llWithSwitchButtonTextView
                        .setBackground(WithSwitchButtonTextView_background);
            }
        }

        if (WithSwitchButtonTextView_title != null) {
            tvTitle.setText(WithSwitchButtonTextView_title);
        }

        if (!StringUtils.isEmptyOrNullStr(contentString)) {
            tvContent.setText(contentString);
            tvContent.setVisibility(VISIBLE);
        } else {
            tvContent.setText("");
            tvContent.setVisibility(GONE);
        }

        tvTitle.setTextColor(title_color);

        tvContent.setTextColor(content_color);

        if (is_content_text_left) {
            tvContent.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        } else {
            tvContent.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        }


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        if (title_size != -1) {
            tvContent.setTextSize(DensityUtils.px2dip(context, title_size));
        }

        if (content_size != -1) {
            tvContent.setTextSize(DensityUtils.px2dip(context, content_size));
        }


        if (margin != -1) {
            lp.setMargins(margin, 0, margin, 0);
        } else {
            lp.setMargins(margin_left, 0, margin_right, 0);
        }

        llWithSwitchButtonTextView.setLayoutParams(lp);
    }


    public SwitchCompat getSwitchButton() {
        return switchButton;
    }

    /**
     * @param text
     * @return void 返回类型
     * @Title setContent
     * @Description 设置
     */
    public void setContent(CharSequence text) {
        tvContent.setText(text);
    }


    /**
     * @param color 文字颜色
     * @return void 返回类型
     * @Title setTitle
     * @Description
     */
    public void setTitleColor(ColorStateList color) {
        tvTitle.setTextColor(color);
    }


    /**
     * @param color 文字颜色
     * @return void 返回类型
     * @Title setTitle
     * @Description
     */
    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }


    /**
     * @param text
     * @return void 返回类型
     * @Title setTitle
     * @Description 设置
     */
    public void setTitle(CharSequence text) {
        tvTitle.setText(text);
    }


    /**
     * 设置switchbutton 状态变更监听器
     *
     * @param onCheckedChangeListener
     */
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        switchButton.setOnCheckedChangeListener(onCheckedChangeListener);
    }


    /**
     * 设置info点击监听器
     *
     * @param
     */
    public void setOnIbInfoClickListener(OnClickListener onClickListener) {
//        ibInfo.setVisibility(VISIBLE);
        ibInfo.setOnClickListener(onClickListener);
    }


    public ImageButton getIbInfo() {
        return ibInfo;
    }

    /**
     * 设置是否选中
     *
     * @param b
     */
    public void setChecked(boolean b) {
        switchButton.setChecked(b);
    }

    /**
     * 设置是否选中
     */
    public boolean isChecked() {
        return switchButton.isChecked();
    }


}