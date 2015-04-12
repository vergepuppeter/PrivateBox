package app.privatebox.com.privatebox.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import app.privatebox.com.privatebox.Adapter.GridViewAdapter;
import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 11/04/2015.
 */
public class GridViewActivity extends ActionBarActivity {

    private GridView gridView;
    private GridViewAdapter adapter;
    private DisplayImageOptions options;
    private final String[] urls =
            {"http://hdwallpaperd.com/wp-content/uploads/galatasaray-anonymous-mask-sport-hd-wallpaper-1920x1200-91891.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/new-wallpaper-16.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/windows-8-wallpapers-2560x1600-2311_1.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/Wallpaper-Desktop-80.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/Nature-Wallpaper-daydreaming-34811098-1024-768.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/wallpaper-nature-3d1.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/wallpapers-7020-7277-hd-wallpapers.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/unnamed.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/new-wallpaper-16.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/indian-beach-wallpaper1.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/Golden-Sunset-On-The-Beach-Wallpaper.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/hd-1080p-wallpaper-1080p-backgrounds-hd-backgrounds-29099.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/computer-desktop-wallpaper.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/cool-hd-widescreen-moving-wallpapers1.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/closeup-leaves-water-drops-wallpaper-for-1920x1080-hdtv-1080p-18-168.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/colorful_kites_hd_1080p-HD.jpg",
                    "http://hdwallpaperd.com/wp-content/uploads/asd.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_layout);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        adapter = new GridViewAdapter(this, urls, options);

        gridView = (GridView)findViewById(R.id.contentView);
        gridView.setAdapter(adapter);

    }
}
