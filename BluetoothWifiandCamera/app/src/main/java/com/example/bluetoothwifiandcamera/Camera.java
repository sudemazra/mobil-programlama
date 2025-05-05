package com.example.bluetoothwifiandcamera;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Camera extends AppCompatActivity {

    private static final int VIDEO_ACTION_CODE = 101;
    private static final int IMAGE_ACTION_CODE = 102;
    private Button b5, b6;
    private ImageView imageView;
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camera);

        b5 = findViewById(R.id.btnResim);
        b6 = findViewById(R.id.btnVideo);
        imageView = findViewById(R.id.imageView3);
        videoView = findViewById(R.id. videoView);

        b5.setOnClickListener(v -> captureImage());
        b6.setOnClickListener(v -> captureVideo());
    }


    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, IMAGE_ACTION_CODE);
    }


    private void captureVideo() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(takeVideoIntent, VIDEO_ACTION_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;

        switch (requestCode) {
            case VIDEO_ACTION_CODE:
                videoView.setVideoURI(data.getData());
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                videoView.start();
                break;
            case IMAGE_ACTION_CODE:
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(imageBitmap);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}