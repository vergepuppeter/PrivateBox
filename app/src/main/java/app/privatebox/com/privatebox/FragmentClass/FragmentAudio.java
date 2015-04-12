package app.privatebox.com.privatebox.FragmentClass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;

import java.io.File;
import java.io.IOException;

import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 11/04/2015.
 */
public class FragmentAudio extends Fragment{

    protected static final int CAMERA_REQUEST_CODE = 1;
    protected static final int GALLERY_REQUEST_CODE = 2;
    private EditText urlForm;
    private Button playBtn;
    private static MediaPlayer mediaPlayer;
    private MediaController mcontroller;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.audio_layout, container, false);

        urlForm = (EditText)rootView.findViewById(R.id.urlPath);
        urlForm.setText("http://www.noiseaddicts.com/samples/4928.mp3");
        playBtn = (Button)rootView.findViewById(R.id.playAudio);
        playBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        return rootView;
    }

    private void playAudio() {

        final CharSequence[] options = { "From URL", "Choose from File","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

        if (resultCode == getActivity().RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try
            {
                Uri selectedAudio = data.getData();
                String[] filePath = { MediaStore.Audio.Media.DATA };
                Cursor c = getActivity().getContentResolver().query(selectedAudio,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String audioPath = c.getString(columnIndex);
                c.close();

                mediaPlayer = new MediaPlayer();
                //mcontroller = new MediaController(getActivity());
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(getActivity(), Uri.parse(audioPath));
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
