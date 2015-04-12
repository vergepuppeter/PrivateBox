package app.privatebox.com.privatebox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;

import java.util.ArrayList;

import app.privatebox.com.privatebox.Object.Image;

/**
 * Created by Yusri on 4/8/2015.
 */
public class ImageAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Image> items;
    private AQuery aQuery;

    public ImageAdapter(Context context, ArrayList<Image> imgs)
    {
        this.context = context;
        this.items = imgs;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        aQuery = new AQuery(context);
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView)convertView.findViewById(R.id.image_item);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.img = (ImageView)convertView.findViewById(R.id.image_item);
        }

        aQuery.id(viewHolder.img).image(items.get(position).getPath(), false, false);

        return convertView;
    }

    private class ViewHolder
    {
        private ImageView img;
    }
}
