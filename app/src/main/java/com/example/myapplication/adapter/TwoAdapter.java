package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.entity.CarEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.CarGoodsVH> {
    private Context context;
    private List<CarEntity.OrderDataBean.CartlistBean> cartlistBeans;
    private int mPosition;
    private List<CarEntity.OrderDataBean> list;

    public TwoAdapter(Context context, List<CarEntity.OrderDataBean.CartlistBean> cartlistBeans) {
        this.context = context;
        this.cartlistBeans = cartlistBeans;
        this.mPosition = mPosition;
        this.list = list;
    }

    @NonNull
    @Override
    public CarGoodsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //关联的是商品条目布局
        View view = View.inflate(context, R.layout.item, null);
        return new CarGoodsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarGoodsVH holder, int position) {
        holder.GoodsName.setText(cartlistBeans.get(position).getProductName());
        //价格要加+""
        holder.GoodsPrice.setText(cartlistBeans.get(position).getPrice()+"");
        Glide.with(context).load(cartlistBeans.get(position).getDefaultPic())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.GoodsImage);
        holder.sp.setChecked(cartlistBeans.get(position).isChecked());
        holder.sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartlistBeans.get(position).setChecked(holder.sp.isChecked());
                callback.back(mPosition,isAllCheck());

                boolean isAllCheck=true;
                for (CarEntity.OrderDataBean dataBean:list){
                    for (CarEntity.OrderDataBean.CartlistBean cartlistBean:dataBean.getCartlist()){
                        if (!cartlistBean.isChecked()) isAllCheck=false;
                    }
                    if (!dataBean.isChecked()) isAllCheck=false;
                }
                EventBus.getDefault().postSticky(isAllCheck);

            }
        });
    }
    public boolean isAllCheck() {
        boolean isAllCheck = true;
        for (CarEntity.OrderDataBean.CartlistBean bean :cartlistBeans) {
            if (!bean.isChecked()) {
                isAllCheck = false;
            }
        }
        return isAllCheck;
    }
    SmallCallback callback;
    public interface SmallCallback {
        void back(int position, boolean isChecked);
    }
    public void setCallback(SmallCallback callback) {
        this.callback = callback;
    }
    BigAllChecked bigAllChecked;
    public interface BigAllChecked {
        void back(boolean isChecked);
    }
    public void setBigAllChecked(BigAllChecked bigAllChecked) {
        this.bigAllChecked = bigAllChecked;
    }
    @Override
    public int getItemCount() {
        return cartlistBeans.size();
    }

    class CarGoodsVH extends RecyclerView.ViewHolder {

        private final ImageView GoodsImage;
        private final TextView GoodsName;
        private final TextView GoodsPrice;
         CheckBox sp;

        public CarGoodsVH(@NonNull View itemView) {
            super(itemView);
            GoodsImage = itemView.findViewById(R.id.GoodsImage);
            GoodsName = itemView.findViewById(R.id.GoodsName);
            GoodsPrice = itemView.findViewById(R.id.GoodsPrice);
            sp = itemView.findViewById(R.id.sp);
        }
    }
}
