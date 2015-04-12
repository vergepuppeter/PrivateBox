package app.privatebox.com.privatebox.FragmentClass;

/**
 * Created by Yusri on 4/10/2015.
 */
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import app.privatebox.com.privatebox.Adapter.ListViewAdapter;
import app.privatebox.com.privatebox.Object.Image;
import app.privatebox.com.privatebox.R;

public class FragmentListView extends Fragment {

    private ArrayList<Image> images;
    private ListView listView;
    private ListViewAdapter adapter;
    private DisplayImageOptions options;
    private final String[] urls = {"http://img0.mxstatic.com/wallpapers/0adb044ad26b09208766b6b6a349173b_large.jpeg",
            "http://img0.mxstatic.com/wallpapers/6ef4aa48a557de4af03e411b6e9aa995_large.jpeg",
            "http://elyon.co.in/wp-content/uploads/2014/04/Download-Apple-3D-Background-Full-Hd-Wallpaper.jpeg",
            "http://hdwallpaperd.com/wp-content/uploads/0382763769f46da4ff3c6644eeac648c_large.jpeg",
            "http://hdwallpaperd.com/wp-content/uploads/8040518319d1447bbf20cfb44047d0d431.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view_layout, container, false);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        images = new ArrayList<Image>();
        adapter = new ListViewAdapter(getActivity(), urls, options);

        listView = (ListView)rootView.findViewById(R.id.contentView);
        listView.setAdapter(adapter);

        return rootView;
    }
}