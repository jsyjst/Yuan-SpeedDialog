package com.example.yuan_dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.yuan_dialog.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import androidx.annotation.NonNull;


/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 确认框
 * </pre>
 */


public class SpeedDialog extends Dialog implements View.OnClickListener {

    private static final String TAG = "SpeedDialog";

    private OnYuanClickListener mSureClickListener;
    private OnYuanClickListener mCancelClickListener;
    private LinearLayout mNormalPanel;
    private LinearLayout mProgressPanel;
    private TextView mTitleTv; //标题
    private TextView mMessageTv; //内容
    private TextView mSureBtn; //确定框
    private TextView mCancelBtn;//取消框
    private ProgressWheel mProgressBar;//加载框
    private TextView mProgressTv;//加载描述
    private String mTitleText;
    private String mMessageText;
    private String mCancelText;
    private String mSureText;
    private String mProgressText;
    private int mDialogType;
    private int mSureTextColor;
    private int mProgressColor;

    public static final int NORMAL_TYPE = 0; //正常中间确认框
    public static final int PROGRESS_TYPE = 1;//加载框
    public static final int INPUT_TYPE = 2; //输入框
    public static final int BOTTOM_SELECT_TYPE = 2; //底部选择框


    public interface OnYuanClickListener {
        void onClick(SpeedDialog dialog);
    }


    public SpeedDialog(@NonNull Context context) {
        this(context, NORMAL_TYPE);
    }

    public SpeedDialog(Context context, int dialogType) {
        super(context, R.style.MyDialog);   //设置自己的风格
        mDialogType = dialogType;
        if (mDialogType == PROGRESS_TYPE) {
            setCanceledOnTouchOutside(false);
        } else {
            setCancelable(true);  //点击屏幕和返回键，dialog消失
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mDialogType == BOTTOM_SELECT_TYPE) {
            setContentView(R.layout.dialog_bottom);
        } else {
            View parentPanel = LayoutInflater.from(getContext()).inflate(R.layout.dialog_center, null);
            setContentView(parentPanel);
            mNormalPanel = parentPanel.findViewById(R.id.normalPanel);
            mProgressPanel = parentPanel.findViewById(R.id.progressPanel);
            mTitleTv = parentPanel.findViewById(R.id.dialogTitleTv);        //标题
            mMessageTv = parentPanel.findViewById(R.id.dialogMessageTv);    //内容
            mCancelBtn = parentPanel.findViewById(R.id.dialogCancelBtn);    //取消
            mSureBtn = parentPanel.findViewById(R.id.dialogSureBtn);    //确定
            mProgressBar = parentPanel.findViewById(R.id.progressBar);//加载框
            mProgressTv = parentPanel.findViewById(R.id.progressTv);//加载文字

            mCancelBtn.setOnClickListener(this);
            mSureBtn.setOnClickListener(this);

            setTitle(mTitleText);
            setMessage(mMessageText);
            setCancelText(mCancelText);
            setSureText(mSureText);
            setProgressText(mProgressText);
            setSureTextColor(mSureTextColor);
            setProgressColor(mProgressColor);
            showByDialogType(mDialogType);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialogCancelBtn:
                cancel();
                if (mCancelClickListener != null) mCancelClickListener.onClick(this);
                break;
            case R.id.dialogSureBtn:
                cancel();
                if (mSureClickListener != null) mSureClickListener.onClick(this);
                break;
            default:
                break;
        }
    }

    /**
     * 重新该方法，使dialog适应屏幕的宽高
     */
    @Override
    public void show() {
        super.show();
        Window dialogWindow = this.getWindow();
        assert dialogWindow != null;
        if (mDialogType == BOTTOM_SELECT_TYPE) {
            dialogWindow.setGravity(Gravity.BOTTOM); //设置位置
            dialogWindow.setWindowAnimations(R.style.bottom_menu_animation);//设置动画
            dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    //设置标题
    public SpeedDialog setTitle(String title) {
        mTitleText = title;
        if (mTitleTv != null && mTitleText != null) {
            mTitleTv.setText(mTitleText);
        }
        return this;
    }

    //设置消息
    public SpeedDialog setMessage(String text) {
        mMessageText = text;
        if (mMessageTv != null && mMessageText != null) {
            mMessageTv.setText(mMessageText);
        }
        return this;
    }

    //设置取消按钮的文字
    public SpeedDialog setCancelText(String cancel) {
        mCancelText = cancel;
        if (mCancelText != null && mCancelBtn != null) {
            mCancelBtn.setText(mCancelText);
        }
        return this;
    }

    //设置确定按钮的文字
    public SpeedDialog setSureText(String sure) {
        mSureText = sure;
        if (mSureText != null && mSureBtn != null) {
            mSureBtn.setText(mSureText);
        }
        return this;
    }

    //设置确定按钮文字的颜色
    public SpeedDialog setSureTextColor(int color) {
        mSureTextColor = color;
        if (mSureBtn != null && mSureTextColor != 0) {
            mSureBtn.setTextColor(mSureTextColor);
        }
        return this;
    }

    //设置加载框的颜色
    public SpeedDialog setProgressColor(int color) {
        mProgressColor = color;
        if (mProgressColor != 0 && mProgressBar != null) {
            mProgressBar.setBarColor(color);
        }
        return this;
    }

    public SpeedDialog setProgressText(String text) {
        mProgressText = text;
        if (mProgressText != null && mProgressTv != null) {
            mProgressTv.setText(mProgressText);
        }
        return this;
    }

    //设置确定按钮监听器
    public SpeedDialog setSureClickListener(OnYuanClickListener sureClickListener) {
        mSureClickListener = sureClickListener;
        return this;
    }

    //设置取消按钮监听器
    public SpeedDialog setCancelClickListener(OnYuanClickListener cancelClickListener) {
        mCancelClickListener = cancelClickListener;
        return this;
    }

    private void showByDialogType(int dialogType) {
        switch (dialogType) {
            case NORMAL_TYPE:
                mNormalPanel.setVisibility(View.VISIBLE);
                mProgressPanel.setVisibility(View.GONE);
                break;
            case PROGRESS_TYPE:
                mNormalPanel.setVisibility(View.GONE);
                mProgressPanel.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }


}