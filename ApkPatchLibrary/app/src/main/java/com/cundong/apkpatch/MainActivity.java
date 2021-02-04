package com.cundong.apkpatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.cundong.utils.PatchUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("ApkPatchLibrary");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText("testJNI");
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File oldfile = new File(getExternalFilesDir(""), "153.so");
                File patchfile = new File(getExternalFilesDir(""), "153_154path.so");
                File newfile = new File(getExternalFilesDir(""), "new_154path.so");
                PatchUtils.patch(oldfile.getAbsolutePath(), newfile.getAbsolutePath(), patchfile.getAbsolutePath());
            }
        });
    }
}