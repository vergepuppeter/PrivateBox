package app.privatebox.com.privatebox.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 11/04/2015.
 */
public class AudioActivity extends ActionBarActivity {

    protected static final int GALLERY_REQUEST_CODE = 2;
    private EditText urlForm;
    private Button playBtn;
    private static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_layout);

        urlForm = (EditText)findViewById(R.id.urlPath);
        urlForm.setText("http://www.noiseaddicts.com/samples/4928.mp3");
        playBtn = (Button)findViewById(R.id.playAudio);
        playBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

    }

    private void playAudio() {

        final CharSequence[] options = { "From URL", "Choose from File","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Audio");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("From URL"))
                {
                    String url = urlForm.getText().toString(); //yourURLhere
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource("http://www.noiseaddicts.com/samples/4928.mp3");
                        mediaPlayer.prepareAsync();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (options[item].equals("Choose from File"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, GALLERY_REQUEST_CODE);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try
            {
                Uri selectedAudio = data.getData();
                String[] filePath = { MediaStore.Audio.Media.DATA };
                Cursor c = getContentResolver().query(selectedAudio,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String audioPath = c.getString(columnIndex);
                c.close();

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(this, Uri.parse(audioPath));
                mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)
                mediaPlayer.start();


                //Or use intent
                /*
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                File file = new File(audioPath);
                intent.setDataAndType(Uri.fromFile(file), "audio/*");
                startActivity(intent);
                */

            }
            catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public static void CancelAudio()
    {
        if(mediaPlayer != null)
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }
}
