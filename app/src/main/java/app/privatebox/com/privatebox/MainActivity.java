package app.privatebox.com.privatebox;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import app.privatebox.com.privatebox.Activity.AudioActivity;
import app.privatebox.com.privatebox.Activity.GridViewActivity;
import app.privatebox.com.privatebox.Activity.ListViewActivity;
import app.privatebox.com.privatebox.Activity.TakePictureActivity;
import app.privatebox.com.privatebox.Activity.UploadDownloadActivity;
import app.privatebox.com.privatebox.Activity.ValidationFormActivity;
import app.privatebox.com.privatebox.Adapter.ListContentAdapter;
import app.privatebox.com.privatebox.FragmentClass.FragmentAudio;
import app.privatebox.com.privatebox.FragmentClass.FragmentGridView;
import app.privatebox.com.privatebox.FragmentClass.FragmentListView;
import app.privatebox.com.privatebox.FragmentClass.FragmentValidation;
import app.privatebox.com.privatebox.Object.Student;

public class MainActivity extends ActionBarActivity {

    //private String URL = "http://192.168.1.7:8080/BengkelAndroid/AndroidController";
    private FragmentGridView fGridView;//declaration
    private FragmentAudio fragmentAudio;
    private FragmentListView fListView;
    private FragmentValidation fValidation;
    private ListContentAdapter adapter;
    private ListView listView;
    private final String[] menu = {"GridView","ListView","Validation", "Play Local/Streaming Audio", "Take Picture",
    "Upload/Download"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //view

            listView = (ListView)findViewById(R.id.listContent);

            adapter = new ListContentAdapter(this, menu);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i;
                    switch (position){
                        case 0:
                            i = new Intent(MainActivity.this, GridViewActivity.class);
                            startActivity(i);
                            break;
                        case 1:
                            i = new Intent(MainActivity.this,ListViewActivity.class);
                            startActivity(i);
                            break;
                        case 2:
                            i = new Intent(MainActivity.this, ValidationFormActivity.class);
                            startActivity(i);
                            break;
                        case 3:
                            i = new Intent(MainActivity.this, AudioActivity.class);
                            startActivity(i);
                            break;
                        case 4:
                            i = new Intent(MainActivity.this, TakePictureActivity.class);
                            startActivity(i);
                            break;
                        case 5:
                            i = new Intent(MainActivity.this, UploadDownloadActivity.class);
                            startActivity(i);
                            break;
                    }

                }
            });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.gridView){
            FragmentAudio.CancelAudio();
            //fGridView = new FragmentGridView();
            //getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, fGridView).commit();
            return true;
        }
        else if(id == R.id.listView){
            FragmentAudio.CancelAudio();
            //FragmentListView fListView = new FragmentListView();
           // getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, fListView).commit();
            return true;
        }
        else if(id == R.id.btn_and_validation){
            FragmentAudio.CancelAudio();
            //FragmentValidation fValidation = new FragmentValidation();
            //getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, fValidation).commit();
            return true;
        }

        else if(id == R.id.play_audio){
            FragmentAudio.CancelAudio();
            //FragmentAudio fAudio = new FragmentAudio();
            //getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, fAudio).commit();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    /*
    private class GetXMLTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String output = null;
            for (String url : urls) {
                output = getOutputFromUrl(url);
            }
            return output;
        }

        private String getOutputFromUrl(String url) {
            String output = null;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                output = EntityUtils.toString(httpEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output;
        }

        @Override
        protected void onPostExecute(String output) {

            //Toast.makeText(context, output, Toast.LENGTH_SHORT).show();
        }
    }
    */
}
