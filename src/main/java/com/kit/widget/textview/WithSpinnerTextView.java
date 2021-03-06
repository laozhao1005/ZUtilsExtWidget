package com.kit.widget.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kit.extend.widget.R;
import com.kit.utils.DensityUtils;
import com.kit.utils.StringUtils;
import com.kit.widget.spinner.BetterSpinner;

/**
 * @author joeyzhao
 */
public class WithSpinnerTextView extends LinearLayout {

    private TextView tvTitle, tvContent;
    private ImageButton ibInfo;
    private String contentString, WithSpinnerTextView_title;
    private Drawable WithSpinnerTextView_background;

    private RelativeLayout llWithSpinnerTextView;

    private boolean is_content_text_left;
    private int title_size, content_size,
            margin, margin_left, margin_right;

    private int title_color, content_color;

    private BetterSpinner spinner;

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public WithSpinnerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 方式1获取属性
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.WithSpinnerTextView);

        title_color = a
                .getColor(
                        R.styleable.WithSpinnerTextView_WithSpinnerTextView_title_color,
                        getResources().getColor(R.color.black));

        content_color = a
                .getColor(
                        R.styleable.WithSpinnerTextView_WithSpinnerTextView_content_color,
                        getResources().getColor(R.color.black));

        WithSpinnerTextView_background = a
                .getDrawable(R.styleable.WithSpinnerTextView_WithSpinnerTextView_background);

        WithSpinnerTextView_title = a
                .getString(R.styleable.WithSpinnerTextView_WithSpinnerTextView_title);

        contentString = a
                .getString(R.styleable.WithSpinnerTextView_WithSpinnerTextView_content);


        is_content_text_left = a
                .getBoolean(
                        R.styleable.WithSpinnerTextView_WithSpinnerTextView_is_content_text_left,
                        true);


        View view = LayoutInflater.from(context).inflate(
                R.layout.with_spinner_textview, null);

        llWithSpinnerTextView =  view
                .findViewById(R.id.ll);

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);

        ibInfo = (ImageButton) view.findViewById(R.id.ibInfo);

        tvContent = (TextView) view.findViewById(R.id.tvContent);


        spinner = (BetterSpinner) view.findViewById(R.id.spinner);

        if (WithSpinnerTextView_background != null) {
            llWithSpinnerTextView
                    .setBackground(WithSpinnerTextView_background);
        }


        if (WithSpinnerTextView_title != null) {
            tvTitle.setText(WithSpinnerTextView_title);
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


        title_size = (int) a.getDimension(
                R.styleable.WithSpinnerTextView_WithSpinnerTextView_title_size, -1);

        content_size = (int) a.getDimension(
                R.styleable.WithSpinnerTextView_WithSpinnerTextView_content_size, -1);

        margin = (int) a.getDimension(
                R.styleable.WithSpinnerTextView_WithSpinnerTextView_margin, -1);

        margin_left = (int) a.getDimension(
                R.styleable.WithSpinnerTextView_WithSpinnerTextView_margin_left, 0);

        margin_right = (int) a.getDimension(
                R.styleable.WithSpinnerTextView_WithSpinnerTextView_margin_right, 0);

        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

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

        llWithSpinnerTextView.setLayoutParams(lp);
        a.recycle();

        // 把获得的view加载到这个控件中
        addView(view);

    }

    /**
     * @param text activity返回过来的文字
     * @return void 返回类型
     * @Title setContent
     * @Description 设置activity返回过来的文字
     */
    public void setContent(CharSequence text) {
        if(text==null||StringUtils.isEmpty(text.toString())){
            tvContent.setText("");
            tvContent.setVisibility(GONE);
            return;
        }
        tvContent.setVisibility(VISIBLE);
        tvContent.setText(text);
    }


    /**
     * @param text activity返回过来的文字
     * @return void 返回类型
     * @Title setTitle
     * @Description 设置activity返回过来的文字
     */
    public void setTitle(CharSequence text) {
        tvTitle.setText(text);
    }


    /**
     * 设置info点击监听器
     *
     * @param
     */
    public void setOnIbInfoClickListener(OnClickListener onClickListener) {
        ibInfo.setVisibility(VISIBLE);
        ibInfo.setOnClickListener(onClickListener);
    }


    /**
     * 设置Spinner默认选项
     *
     * @param hint
     */
    public void setSpinnerHint(CharSequence hint) {
        spinner.setHint(hint);
    }


    /**
     * 得到Spinner默认选项
     */
    public CharSequence getSpinnerHint() {
        return spinner.getHint();
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {
        spinner.setAdapter(adapter);
    }

    /**
     * 设置Spinner 状态变更监听器
     *
     * @param l
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener l) {
        spinner.setOnItemClickListener(l);
    }


    public BetterSpinner getSpinner() {
        return spinner;
    }
}