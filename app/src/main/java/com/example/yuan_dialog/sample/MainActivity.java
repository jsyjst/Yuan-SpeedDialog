package com.example.yuan_dialog.sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.yuan_dialog.R;
import com.example.yuan_dialog.dialog.SpeedDialog;
import com.example.yuan_dialog.listener.OnInputDialogButtonClickListener;

public class MainActivity extends AppCompatActivity {
    AlertDialog dialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dialogBtn = findViewById(R.id.dialogBtn);
        dialogBtn.setOnClickListener(view->showDialog());
        findViewById(R.id.dialogCancelBottomBtn).setOnClickListener(view -> showBottomDialog());
        findViewById(R.id.dialogCancelCenterBtn).setOnClickListener(view -> showCenterCancelDialog());
        findViewById(R.id.dialogProgressBtn).setOnClickListener(view -> showProgress());
        findViewById(R.id.dialogInputBtn).setOnClickListener(view -> showInputDialog());
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setMessage("Message部分")
                .setTitle("Title部分")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         //确认按钮的响应
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //取消按钮的响应
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
    private void showBottomDialog(){
        SpeedDialog bottomDialog = new SpeedDialog(this, SpeedDialog.BOTTOM_SELECT_TYPE);
        bottomDialog.show();
    }

    private void showCenterCancelDialog(){
        SpeedDialog normalDialog = new SpeedDialog(this);
        normalDialog.setTitle("删除？")
                .setSureText("删除")
                .setMessage("是否删除所有历史记录")
                .setSureClickListener(dialog1 -> Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show())
                .show();
    }

    private void showProgress(){
        SpeedDialog progressDialog = new SpeedDialog(this, SpeedDialog.PROGRESS_TYPE);
        progressDialog.setProgressColor(ContextCompat.getColor(this,R.color.colorPrimary))
                .setProgressText("正在加载...")
                .show();
    }

    private void showInputDialog(){
        SpeedDialog inputDialog = new SpeedDialog(this,SpeedDialog.INPUT_TYPE);
        inputDialog.setTitle("登录")
                .setSureText("确定")
                .setInputDialogSureClickListener(new OnInputDialogButtonClickListener() {
                    @Override
                    public void onClick(Dialog dialog, String inputText) {
                        Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


}
