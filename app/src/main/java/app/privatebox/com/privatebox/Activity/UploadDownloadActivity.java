package app.privatebox.com.privatebox.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 12/04/2015.
 */
public class UploadDownloadActivity extends ActionBarActivity{

    //declare variable/widget/Object/Adapter
    private Button downloadBtn;
    private Button uploadBtn;
    protected static final int GALLERY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view layout for activity
        setContentView(R.layout.upload_download_layout);

        //initialize variable/widget/ObjectAdapter
        downloadBtn = (Button)findViewById(R.id.downloadBtn);
        uploadBtn = (Button)findViewById(R.id.uploadBtn);

        //Implements action for button
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile();
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });


    }

    public void downloadFile()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://...........mp3", new FileAsyncHttpResponseHandler(this) {
            ProgressDialog pd;
            @Override
            public void onStart() {
                super.onStart();
                pd = new ProgressDialog(UploadDownloadActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage("Downloading...");
                pd.show();
                downloadBtn.setEnabled(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(UploadDownloadActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                downloadBtn.setEnabled(true);

                if(pd.isShowing())
                    pd.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                // Do something with the file `response`
                if(pd.isShowing())
                    pd.dismiss();

                downloadBtn.setEnabled(true);
                Toast.makeText(UploadDownloadActivity.this, "Success", Toast.LENGTH_SHORT).show();
                //create path to store the downloaded file
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"/"+System.currentTimeMillis()+"/");
                InputStream in = null;
                OutputStream out = null;
                try {
                    //check if the folder is not exist
                    if (!dir.exists())
                    {
                        //then create the folder
                        dir.mkdirs();
                    }

                    //get file's data
                    in = new FileInputStream(getTargetFile());
                    //put file's data
                    out = new FileOutputStream(dir + ".mp3");

                    //store data
                    byte[] buffer = new byte[1024];
                    int read;
                    //move the data/bytes
                    while ((read = in.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                    }

                    //close the process
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                }
                catch (FileNotFoundException fnfe1) {
                }
                catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }

                //automatic open gallery
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(dir + ".mp3")), "audio/*");
                startActivity(intent);
            }


        });
    }




    public void uploadFile(File file)
    {
        RequestParams params = new RequestParams();
        try {
            params.put("userfile",file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://192.168.42.3:8080/AndroidWorkshop/upload", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(UploadDownloadActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(UploadDownloadActivity.this, String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }
        });
    }

       @Override
       protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try
            {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();

                uploadFile(new File(picturePath));
            }
            catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}
