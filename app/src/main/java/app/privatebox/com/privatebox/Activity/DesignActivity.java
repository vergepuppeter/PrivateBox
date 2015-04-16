package app.privatebox.com.privatebox.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import app.privatebox.com.privatebox.R;

public class DesignActivity extends ActionBarActivity {

    // [1] Design dahulu layout. Then baru coding

    // [2] Declare dulu variable yg nak guna. Dalam hal ni, just nak guna imageView sahaja.
    // TextView tu semua just nak show off sahaja. Tak guna pun dalam coding.
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //[3] Macam biasa, set layout mana yang nak guna kat setContentView. Pastikan nama betul.
        //salah nama pun boleh crash
        setContentView(R.layout.activity_design);

        // [4] initialize variable yang nak guna. Disebabkan just nak guna imageView, so just initialize imageView
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);

        //[5] Macam view yg lain, setiap view ada specific function yg tersendiri. Kalau guna button, ada setOnClickListener. Kalau guna textView, ada setText()/getText(). Kalau guna imageView, ada setImageXXXXX.
        //best format image adalah .png
        // Semua image kena letak dalam drawable folder, so kalau nak panggil image tu, pakai R.drawable.nama_image
        imageView1.setImageResource(R.drawable.sample_one);
        imageView2.setImageResource(R.drawable.sample_two);
        imageView3.setImageResource(R.drawable.sample_three);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_design, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
