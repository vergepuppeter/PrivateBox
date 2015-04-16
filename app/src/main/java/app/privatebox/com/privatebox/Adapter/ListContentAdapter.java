package app.privatebox.com.privatebox.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 11/04/2015.
 */
public class ListContentAdapter extends BaseAdapter {
    private String[] items;
    private Context context;
    private String[] itemsDesc;

    public ListContentAdapter(Context context, String[] items, String[] itemsDesc)
    {
        this.items = items;
        this.context = context;
        this.itemsDesc = itemsDesc;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.content_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt = (TextView)convertView.findViewById(R.id.text_item);
            viewHolder.txtDesc = (TextView)convertView.findViewById(R.id.desc_item);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.txt = (TextView)convertView.findViewById(R.id.text_item);
            viewHolder.txtDesc = (TextView)convertView.findViewById(R.id.desc_item);
        }

        viewHolder.txt.setText(items[position]);
        viewHolder.txtDesc.setText(itemsDesc[position]);

        return convertView;
    }

    private class ViewHolder
    {
        private TextView txt, txtDesc;
    }
}
