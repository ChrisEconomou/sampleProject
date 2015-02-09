package com.chriseconomou.sampleproject.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.data.CategoryListing;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CategoriesListAdapter extends ArrayAdapter<CategoryListing> {

    private LayoutInflater mInflater;

    public CategoriesListAdapter(Context context, int resource, List<CategoryListing> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.view_list_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        populateData(viewHolder,position);

        return view;
    }

    private void populateData(ViewHolder viewHolder,int position){
        CategoryListing categoryListing = getItem(position);
        viewHolder.mItemTextName.setText(categoryListing.name);
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'view_list_item.xml'
     * for easy to all layout elements.
     *
     */
    static class ViewHolder {
        @InjectView(R.id.item_text_name)
        TextView mItemTextName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}