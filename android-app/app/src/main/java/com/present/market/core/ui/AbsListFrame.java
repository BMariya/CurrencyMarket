package com.present.market.core.ui;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.present.market.core.base.AppAction;
import com.present.market.core.base.AppType;

import java.util.List;

public abstract class AbsListFrame<ItemView extends AbsView, Item> extends AbsFrame {
    public AbsListFrame(ViewGroup contentView) {
        super(contentView);
        this.setListView();
    }

    private ListAdapter mListAdapter;
    private void setListView() {
        log().debug("setListView");
        this.mListAdapter = new ListAdapter();
        RecyclerView rvList = getRecyclerView(this.onGetRecyclerViewIdRes());
        rvList.setAdapter(this.mListAdapter);
    }

    @IdRes
    protected abstract int onGetRecyclerViewIdRes();

    protected abstract ItemView onCreateListItemView(ViewGroup viewGroup);

    protected abstract void onSetListItemView(ItemView itemView, Item item);

    public final void show(List<Item> dataList, AppAction<Item> listClickAction) {
        log().debug("show");
        this.mListAdapter.show(dataList, listClickAction);
    }
    //=============================================================================================

    private final class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private List<Item> mDataList;
        private AppAction<Item> mListClickAction;

        private ListAdapter() {
            super();
        }

        public int getItemCount() {
            return AppType.OBJ_IS_NULL(this.mDataList) ? 0 : this.mDataList.size();
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            AbsListFrame.this.onSetListItemView(holder.getView(), this.mDataList.get(position));
        }

        public ViewHolder onCreateViewHolder(ViewGroup contentView, int viewType) {
            return new ViewHolder(AbsListFrame.this.onCreateListItemView(contentView));
        }

        public void show(List<Item> dataList, AppAction<Item> listClickAction) {
            this.mDataList = dataList;
            this.mListClickAction = listClickAction;
            notifyDataSetChanged();
        }

        private void onClickListItemView(int position) {
            if (AppType.OBJ_IS_NULL(this.mListClickAction)) return;
            this.mListClickAction.onResult(this.mDataList.get(position));
        }
        //=========================================================================================

        final class ViewHolder extends RecyclerView.ViewHolder {
            private final ItemView mView;
            private ViewHolder(ItemView view) {
                super(view.getView());
                this.mView = view;
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListAdapter.this.onClickListItemView(ViewHolder.this.getAdapterPosition());
                    }
                });
            }

            private ItemView getView() {
                return this.mView;
            }
        }
    }
}
