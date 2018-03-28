package com.yiw.circledemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiw.circledemo.R;
import com.yiw.circledemo.bean.ImageAndText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by hasee-pc on 2018/03/27.
 */

public class ImageAndTextListAdapter extends ArrayAdapter<ImageAndText> {

    private GridView gridView;
    public ImageAndTextListAdapter(Activity activity, List<ImageAndText> imageAndTexts, GridView gridView1) {
        super(activity, 0, imageAndTexts);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();

        // Inflate the views from XML
        View rowView = convertView;
        ViewCache viewCache;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.gridview_forph_item, null);
            viewCache = new ViewCache(rowView);
            rowView.setTag(viewCache);
        } else {
            viewCache = (ViewCache) rowView.getTag();
        }
        ImageAndText imageAndText = getItem(position);

        // Load the image and set it on the ImageView
        String imageUrl = imageAndText.getImgUrl();
        ImageView imageView = viewCache.getImageView();
        imageView.setTag(imageUrl);

        Bitmap bitmap=BitmapFactory.decodeFile(imageUrl);
        imageView.setImageBitmap(bitmap);

//        // Set the text on the TextView
//        TextView textView = viewCache.getTextView();
//        textView.setText(imageAndText.getText());
        return rowView;
    }
    public class ViewCache {

        private View baseView;
        private TextView textView;
        private ImageView imageView;

        public ViewCache(View baseView) {
            this.baseView = baseView;
        }

        public TextView getTextView() {
            if (textView == null) {
                textView = (TextView) baseView.findViewById(R.id.text);
            }
            return textView;
        }

        public ImageView getImageView() {
            if (imageView == null) {
                imageView = (ImageView) baseView.findViewById(R.id.img);
            }
            return imageView;
        }

    }

}
