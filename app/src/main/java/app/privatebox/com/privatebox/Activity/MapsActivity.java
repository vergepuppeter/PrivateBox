package app.privatebox.com.privatebox.Activity;

import android.content.Context;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;

import java.util.ArrayList;

import app.privatebox.com.privatebox.R;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Location getCurrentLocation;

    //[1] set coordinate untuk UITM Kedah
    private LatLng uitmKedah = new LatLng(5.711991, 100.449335);

    //[2] set coordinate untuk setiap lokasi/kelas/makmal/bangunan. Just tambah je coordinate dalam list kat bawah. Nanti bila run terus nampak point tempat ni dalam map
    private LatLng[] points =
                            //0                       //1                           //2                              //3                                //4
            {new LatLng(5.71358, 100.45073), new LatLng(5.71351,100.45085), new LatLng(5.71305, 100.45093), new LatLng(5.71249, 100.44991), new LatLng(5.71235, 100.44966),
                        //5                             //6                             //7                            //8
            new LatLng(5.71132, 100.44981), new LatLng(5.71047,100.44943), new LatLng(5.71038, 100.44715), new LatLng(5.71216,100.44669)};

    //[3] set list nama bangunan yg kita assign. Pastikan ikut susun ikut nombor macam kat atas.
                                    //0     //1             //2                  //3            //4                 //5            //6          //7       //8
    private String[] namePlace = {"Guard", "Bank", "Pejabat Unit Keselamatan", "Parkir","Pejabat Pentadbiran", "Perpustakaan", "Pejabat Hep", "Masjid", "Dewan"};
    private AutoCompleteTextView autoCompleteTextView;
    private int setZoomLevel;
    private ArrayList<Marker> marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoComplete);

        marker = new ArrayList<Marker>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, namePlace);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int a = 0;
                for(int i = 0 ; i < namePlace.length; i++)
                {
                    if(autoCompleteTextView.getText().toString().equals(namePlace[i]))
                    {
                        setZoomLevel = 18;
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(points[i],setZoomLevel));
                        marker.get(i).showInfoWindow();
                        hideKeyboard();
                    }
                }
            }
        });
        setUpMapIfNeeded();

        //[4] Marker/point dalam map tu boleh klik. Kalau klik, nanti ada keluar nama tempat.

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMapType(MAP_TYPE_SATELLITE);

        for(int i = 0; i < points.length; i++)
        {
            //ni untuk set marker warna merah. Jangan usik coding ni
            marker.add(mMap.addMarker(new MarkerOptions().position(points[i]).title(namePlace[i])));

        }

        centerMapOnMyLocation();

    }

    private void centerMapOnMyLocation() {

        //LatLng myLocation = null;
        setZoomLevel = 17;
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uitmKedah,setZoomLevel));

        /*
        if (getCurrentLocation != null) {
            myLocation = new LatLng(getCurrentLocation.getLatitude(),
                    getCurrentLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(myLocation).title("You are here!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,16));
        */
    }

    @Override
    public void onBackPressed()
    {
        if(setZoomLevel == 18) {
            setZoomLevel = 17;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uitmKedah, setZoomLevel));
        }
        else
        {
            //do nothing
            setZoomLevel = 17;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uitmKedah, setZoomLevel));
        }
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            //do your stuff here
            finish();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        autoCompleteTextView.getText().clear();

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
