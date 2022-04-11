package com.cold.coordinatorlayoutdemo.tab;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cold.coordinatorlayoutdemo.R;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHolder>{
    private int[] c;

    private Context context;
    private List<String> list;
    private View inflater;
    //构造方法，传入数据
    public TypeAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
        c = new int[]{Color.parseColor("#33FF0000"),
                Color.parseColor("#3300FF00"),
                Color.parseColor("#330000FF")};
    }

    @Override
    public TypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item_type_layout, parent,false);
        TypeAdapter.MyViewHolder myViewHolder = new TypeAdapter.MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(TypeAdapter.MyViewHolder holder, int position) {
        //将数据和控件绑定
        inflater.setBackgroundColor(c[position % 3]);
        holder.textView.setText("item" + (position + 1));
    }

    @Override
    public int getItemCount() {
        //返回Item总条数
        return list.size();
    }

//    @Override
//    protected void convert(RecyclerView.ViewHolder holder, String data, int position) {
//        holder.setBgColor(R.id.item_tv, c[position % 3]);
//        holder.setText(R.id.item_tv, "item" + (position + 1));
//    }
//
//    @Override
//    protected int getItemLayoutId() {
//        return R.layout.item_type_layout;
//    }

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}


//public class RecycleAdapterDome extends RecyclerView.Adapter<RecycleAdapterDome.MyViewHolder>{
//    private Context context;
//    private List<String> list;
//    private View inflater;
//    //构造方法，传入数据
//    public RecycleAdapterDome(Context context, List<String> list){
//        this.context = context;
//        this.list = list;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        //创建ViewHolder，返回每一项的布局
//        inflater = LayoutInflater.from(context).inflate(R.layout.item_dome,parent,false);
//        MyViewHolder myViewHolder = new MyViewHolder(inflater);
//        return myViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        //将数据和控件绑定
//        holder.textView.setText(list.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        //返回Item总条数
//        return list.size();
//    }
//
//    //内部类，绑定控件
//    class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView textView;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.text_view);
//        }
//    }
//}
