package cn.feng.constraintlayoutsample.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseVH> {
    List<T> mDataList;

    public BaseRecyclerAdapter(List<T> dataList) {
        mDataList = dataList;
    }

    public BaseRecyclerAdapter() {
    }

    @NonNull
    @Override
    public BaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BaseVH.get(parent, getItemLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseVH holder, int position) {
        convert(holder, getItemViewType(position), mDataList.get(position), position);
    }


    public abstract @LayoutRes
    int getItemLayoutId(int viewType);

    public abstract void convert(BaseVH viewHolder, int viewType, T data, int position);

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }


    public void setData(List<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }


    public static class BaseVH extends RecyclerView.ViewHolder {
        private final View mItemView;
        private SparseArray<View> mViews;

        public BaseVH(View itemView) {
            super(itemView);
            mItemView = itemView;
            mViews = new SparseArray<>();
        }

        public static BaseVH get(ViewGroup parent, @LayoutRes int itemLayoutId) {
            return new BaseVH(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false));
        }


        public <T extends View> T getView(@IdRes int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mItemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }


        public void setText(@IdRes int viewId, String text) {
            TextView view = getView(viewId);
            view.setText(text);
        }

        public void setText(@IdRes int viewId, @StringRes int textRes) {
            setText(viewId, itemView.getContext().getString(textRes));
        }


        public void setImage(@IdRes int viewId, @DrawableRes int imgRes){
            ImageView view = getView(viewId);
            view.setImageResource(imgRes);
        }

        public View getItemView() {
            return mItemView;
        }

        @Override
        public String toString() {
            return itemView.toString();
        }
    }


    public void remove(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void add(T t, int position) {
        mDataList.add(position, t);
        notifyItemInserted(position);
    }
}
