package com.nellpoi.task2_account_management;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class father extends AppCompatActivity {

    public void showDialogAlertWithTitleAndMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public View setLayout(String data1, String data2, String data3, String data4) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.template_topup, null, false);
        ViewHolder2 holder2 = new ViewHolder2(view);
        holder2.name.setText(data1);
        holder2.xiaofei.setText(data2);
        holder2.date.setText(data3);
        holder2.yue.setText(data4);
        return view;
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public Activity getContext() {
        return this;
    }

    public void back() {
        finish();
    }

    public static
    class ViewHolder2 {
        public View rootView;
        public TextView name;
        public TextView xiaofei;
        public TextView date;
        public TextView yue;

        public ViewHolder2(View rootView) {
            this.rootView = rootView;
            this.name = (TextView) rootView.findViewById(R.id.name);
            this.xiaofei = (TextView) rootView.findViewById(R.id.xiaofei);
            this.date = (TextView) rootView.findViewById(R.id.date);
            this.yue = (TextView) rootView.findViewById(R.id.yue);
        }

    }
}
