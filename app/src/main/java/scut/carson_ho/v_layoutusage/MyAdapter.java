package scut.carson_ho.v_layoutusage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carson_Ho on 17/4/26.
 */
public class MyAdapter extends DelegateAdapter.Adapter<MyAdapter.MainViewHolder> {
    // 使用DelegateAdapter首先就是要自定义一个它的内部类Adapter，让LayoutHelper和需要绑定的数据传进去
    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法，其他的都是一样的做法.

    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyItemClickListener myItemClickListener;


    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;

    //构造函数
    public MyAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<HashMap<String, Object>> listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
    }

    public MyAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, Object>> listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;


    }

    // 把ViewHolder绑定Item的布局
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }


    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }


    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));


    }


    @Override
    public int getItemCount() {
        return count;
    }//返回Item数目


    public void setOnItemClickListener(MyItemClickListener listener) {
        myItemClickListener = listener;
    }//绑定MainActivity传进来的点击监听器


//    static class MainViewHolder extends RecyclerView.ViewHolder {
//        public TextView tv1;
//        public MainViewHolder(View itemView) {
//            super(itemView);
//            tv1 = (TextView) itemView.findViewById(R.id.item_tv1);
//        }
//    }


    //定义Viewholder
    class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView Text;
        public ImageView image;

        public MainViewHolder(View root) {
            super(root);
            Text = (TextView) root.findViewById(R.id.Item);
            image = (ImageView) root.findViewById(R.id.Image);

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemClickListener != null)
                        myItemClickListener.onItemClick(v, getPosition());
                }

            }//监听到点击就回调MainActivity的onItemClick函数
            );

        }

        public TextView getText() {
            return Text;
        }


    }
}