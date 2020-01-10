package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.CarEntity;

import java.util.List;

public class OneAdapter extends RecyclerView.Adapter<OneAdapter.CarVH> {
    private Context context;
    private List<CarEntity.OrderDataBean> list;

    public OneAdapter(Context context, List<CarEntity.OrderDataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //关联的是第二个布局,上面是商家,下面是RecyclerView,里面放的是商品布局
        View view = View.inflate(context, R.layout.caritem, null);
        return new CarVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarVH holder, int position) {
        holder.CartName.setText(list.get(position).getShopName());
        List<CarEntity.OrderDataBean.CartlistBean> cartlistBeanList=list.get(position).getCartlist();
        //把第二个布局的适配器放进第一个布局,可以实现嵌套
        TwoAdapter adapter = new TwoAdapter(context, cartlistBeanList);
        holder.RvGoods.setAdapter(adapter);
        holder.RvGoods.setLayoutManager(new LinearLayoutManager(context));
        holder.sj.setChecked(list.get(position).isChecked());
        holder.sj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = holder.sj.isChecked();
                list.get(position).setChecked(checked);
                List<CarEntity.OrderDataBean.CartlistBean> cartlistBeanList=list.get(position).getCartlist();
                for (CarEntity.OrderDataBean.CartlistBean bean:cartlistBeanList){
                    bean.setChecked(checked);
                }
                notifyDataSetChanged();
            }
        });
        adapter.setCallback((position1, isChecked) -> {
            list.get(position1).setChecked(isChecked);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CarVH extends RecyclerView.ViewHolder {

        CheckBox sj;
        CheckBox qx;
        TextView CartName;
        RecyclerView RvGoods;

        public CarVH(@NonNull View itemView) {
            super(itemView);
            CartName = itemView.findViewById(R.id.CartName);
            RvGoods = itemView.findViewById(R.id.RvGoods);
            qx = itemView.findViewById(R.id.qx);
            sj = itemView.findViewById(R.id.sj);
        }
    }
}
