package com.chriseconomou.sampleproject.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.data.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SampleListAdapter extends ArrayAdapter<Result> {

    private LayoutInflater mInflater;

    public SampleListAdapter(Context context, int resource, List<Result> objects) {
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
        Result result = getItem(position);
        viewHolder.mItemTextName.setText(result.name);
        String url = result.url;
        if (url != null) {
            Picasso.with(getContext()).load(url).into(viewHolder.mItemImage);

        }
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'view_list_item.xml'
     * for easy to all layout elements.
     *
     */
    static class ViewHolder {
        @InjectView(R.id.item_text_name)
        TextView mItemTextName;
        @InjectView(R.id.item_image)
        ImageView mItemImage;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}