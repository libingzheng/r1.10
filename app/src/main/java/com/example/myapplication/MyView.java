package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyView extends LinearLayout {

    public TextView jia,jian,num;
    public int nun=1;
    public MyView(Context context) {
        super(context);
        initView();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.view, null);
        addView(view);//添加子控件到当前容器

        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);
        num = view.findViewById(R.id.num);

        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nun--;
                if (nun<1){
                    Toast.makeText(getContext(), "数量不能小于1", Toast.LENGTH_SHORT).show();
                    nun=1;
                }
                num.setText(nun+"");
                numCallBack.numClick(nun);
            }

        });
    }
    private NumCallBack numCallBack;

    public void setNumCallBack(NumCallBack numCallBack) {
        this.numCallBack = numCallBack;
    }

    public interface NumCallBack{
        void numClick(int nun);
    }
}
