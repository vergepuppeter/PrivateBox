package app.privatebox.com.privatebox.Adapter;

/**
 * Created by Yusri on 4/10/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import app.privatebox.com.privatebox.Object.Video;
import app.privatebox.com.privatebox.R;

/**
 * Created by Yusri on 4/8/2015.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private String[] items;
    private AQuery aQuery;
    private DisplayImageOptions options;

    public ListViewAdapter(Context context, String[] items, DisplayImageOptions options)
    {
        this.context = context;
        this.items = items;
        this.options = options;
    }


    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        aQuery = new AQuery(context);
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView)convertView.findViewById(R.id.image_item);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.img = (ImageView)convertView.findViewById(R.id.image_item);
        }

        ImageLoader.getInstance().displayImage(items[position], viewHolder.img, options);

        return convertView;
    }

    private class ViewHolder
    {
        private ImageView img;
    }
}
