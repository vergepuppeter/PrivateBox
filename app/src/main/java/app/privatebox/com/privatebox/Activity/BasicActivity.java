package app.privatebox.com.privatebox.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.privatebox.com.privatebox.R;

public class BasicActivity extends ActionBarActivity {

    //[1] Design dulu layout. Lepas tu baru datang balik ke coding.

    //[2] Ini namanya 'Variable'. Declare variable yg nak digunakan berdasarkan apa yg ada dalam layout. Pi check kat dalam layout
    // Kalau ada button, declare 'private Button apa2nama'. Same goes to textView or whatsoever.
    //Korang nak declare private ke, public ke tak kisah. Tak nak letak private/public pun takpe.
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private TextView basicText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // [3] set layout yg dah didesign dalam setContentView(R.layout.XXXXXXXX). Pastikan nama layout tu betul.
        // Benda ni wajib, kalau tinggal, konfem crash
        setContentView(R.layout.activity_basic);

        // [4] Ini namanya 'initialization'. Korang kena initialize dulu variable ni dengan apa yg ada dalam layout. Kalau dalam layout korg letak button tu punya id 'btn1',
        // so korg kena initialize variable tu dengan id 'btn1'.
        // Kalau tak, button/textview ni semua tak boleh guna sbb dia tak jumpa button yang mana korg nak guna dalam layout tu.
        // Tak kisah apa-apa pun yg korg nak guna dalam layout tu, kena declare dulu variable then initialize,
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        basicText = (TextView)findViewById(R.id.basicTextView);

        // [5] Kalau dah pakai button, korg kena set dia punya 'setOnClickListener. Ni memang basic function. Memang kena pakai.
        // Kalau malas sangat nak taip just copy paste je. Tapi pastikan copy dengan betul.
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //[5] Ni untuk popu
                Toast.makeText(BasicActivity.this, "Buttin 1 is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BasicActivity.this, "Buttin 2 is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BasicActivity.this, "Buttin 3 is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // [6] setText ni untuk set apa2 msj/string dalam textView
        basicText.setText("This is basic level");


        //[7] Note: Kalau nak create Activity baru, Right Click kat project 'app' > New > Activity > Blank Activity
        //Nanti Android Studio akan auto generate semua.

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic, menu);
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
