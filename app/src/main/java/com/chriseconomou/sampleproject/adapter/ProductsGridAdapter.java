package com.chriseconomou.sampleproject.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.data.CategoryListing;
import com.chriseconomou.sampleproject.data.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ProductsGridAdapter extends ArrayAdapter<Product> {

    private LayoutInflater mInflater;

    public ProductsGridAdapter(Context context, int resource, List<Product> objects) {
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
            view = mInflater.inflate(R.layout.view_grid_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        populateData(viewHolder, position);

        return view;
    }

    private void populateData(ViewHolder viewHolder, int position) {
        Product product = getItem(position);
        viewHolder.mGridTextName.setText(product.currentPrice);
        if (product.productImageUrl.size() > 0 && product.productImageUrl.get(0) != null) {
            Picasso.with(getContext()).load(product.productImageUrl.get(0)).into(viewHolder.mGridImage);
        }
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'view_grid_item.xml'
     * for easy to all layout elements.
     */
    static class ViewHolder {
        @InjectView(R.id.grid_image)
        ImageView mGridImage;
        @InjectView(R.id.grid_text_name)
        TextView mGridTextName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}