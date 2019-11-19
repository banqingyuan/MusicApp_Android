package com.example.nojie.fragment.Personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nojie.R;

import java.util.List;

public class MyPersonalRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonalRecyclerViewAdapter.ViewHolder> {

private List<personal_action> MyData;
private MyPersonalRecyclerViewAdapter.onRecyclerItemClickLitener onRecyclerItemClickLitener;

class ViewHolder extends RecyclerView.ViewHolder {

    personal_action mItem;
    final View mView;
    final TextView mContent;
    final TextView mType;
    final TextView mDate;
    final ImageView mImageView;

    public ViewHolder(View view) {
        super(view);

        mView = view;
        mContent=mView.findViewById(R.id.personal_active_content);
        mImageView=mView.findViewById(R.id.person_active_image);
        mDate=mView.findViewById(R.id.personal_active_date);
        mType=mView.findViewById(R.id.personal_active_type);

    }

}
    public MyPersonalRecyclerViewAdapter(List<personal_action> item){
        MyData=item;
    }
    @NonNull
    @Override
    public MyPersonalRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personal_active_item, parent, false);
        return new MyPersonalRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPersonalRecyclerViewAdapter.ViewHolder holder, final int position) {
        if(onRecyclerItemClickLitener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClickLitener.onRecyclerItemClick(holder,position);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onRecyclerItemClickLitener.onRecyclerItemLongClick(holder,position);
                    return false;
                }
            });
        }
        holder.mItem=MyData.get(position);
        holder.mContent.setText(holder.mItem.getContent_text());
        holder.mDate.setText(holder.mItem.getTime());
        holder.mType.setText(holder.mItem.getAction_type());
    }

    /**
     * 指定位置移除item
     */
    public void removeData(int position) {
        MyData.remove(position);
        notifyItemRemoved(position);
    }

public interface onRecyclerItemClickLitener {
    void onRecyclerItemClick(RecyclerView.ViewHolder view, int position);
    void onRecyclerItemLongClick(RecyclerView.ViewHolder view , int position);
}
    public void setOnRecyclerItemClickLitener(MyPersonalRecyclerViewAdapter.onRecyclerItemClickLitener onRecyclerItemClickLitener)
    {
        this.onRecyclerItemClickLitener = onRecyclerItemClickLitener;
    }
    @Override
    public int getItemCount() {

        return MyData.size();
    }

public abstract static class LoadMoreRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public LoadMoreRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
}
