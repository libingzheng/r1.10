package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.adapter.OneAdapter;
import com.example.myapplication.entity.CarEntity;
import com.example.myapplication.mvp.Contract;
import com.example.myapplication.mvp.Presenter;
import com.example.myapplication.utils.RetrofitUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View {
    private Presenter presenter = new Presenter(this);
    private RecyclerView Rv;
    private CheckBox qx;
    private ImageView GoodsImage;
    CheckBox sp;
    Button js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalPrice();
        Rv = findViewById(R.id.Rv);
        qx = findViewById(R.id.qx);
        sp = findViewById(R.id.sp);
        js = findViewById(R.id.js);
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        Rv.setLayoutManager(new LinearLayoutManager(this));
        if (RetrofitUtil.getInstance().is()){
            presenter.Show();
        }else {
            Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
        }
    }

    private void totalPrice() {
        StringBuilder stringBuilder = new StringBuilder();
    }

    @Override
    public void success(Object o) {
        if (o instanceof CarEntity){
            CarEntity carEntity= (CarEntity) o;
            List<CarEntity.OrderDataBean> orderData = carEntity.getOrderData();
            OneAdapter oneAdapter = new OneAdapter(this, orderData);
            Rv.setAdapter(oneAdapter);
            qx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = qx.isChecked();
                    for (CarEntity.OrderDataBean dataBean:carEntity.getOrderData()){
                        for (CarEntity.OrderDataBean.CartlistBean cartlistBean:dataBean.getCartlist()){
                            cartlistBean.setChecked(checked);
                        }
                        dataBean.setChecked(checked);
                    }
                    oneAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void failure(Throwable throwable) {

    }

}
