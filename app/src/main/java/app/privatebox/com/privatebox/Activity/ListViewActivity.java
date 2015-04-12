package app.privatebox.com.privatebox.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import app.privatebox.com.privatebox.Adapter.ListViewAdapter;
import app.privatebox.com.privatebox.Object.Image;
import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 11/04/2015.
 */
public class ListViewActivity extends ActionBarActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private DisplayImageOptions options;
    private final String[] urls = {"http://img0.mxstatic.com/wallpapers/0adb044ad26b09208766b6b6a349173b_large.jpeg",
            "http://img0.mxstatic.com/wallpapers/6ef4aa48a557de4af03e411b6e9aa995_large.jpeg",
            "http://elyon.co.in/wp-content/uploads/2014/04/Download-Apple-3D-Background-Full-Hd-Wallpaper.jpeg",
            "http://hdwallpaperd.com/wp-content/uploads/0382763769f46da4ff3c6644eeac648c_large.jpeg",
            "http://hdwallpaperd.com/wp-content/uploads/8040518319d1447bbf20cfb44047d0d431.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        adapter = new ListViewAdapter(this, urls, options);

        listView = (ListView)findViewById(R.id.contentView);
        listView.setAdapter(adapter);
    }
}
