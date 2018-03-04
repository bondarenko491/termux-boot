package com.termux.boot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BootActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);

        TextView text = new TextView(this);
        text.setText("Files: \n");
        text.setTextSize(24);
        @SuppressLint("SdCardPath") final String BOOT_SCRIPT_PATH = "/data/data/com.termux/files/home/.termux/boot";
        final File BOOT_SCRIPT_DIR = new File(BOOT_SCRIPT_PATH);

        if (!BOOT_SCRIPT_DIR.exists())
            BOOT_SCRIPT_DIR.mkdirs();

        File[] files = BOOT_SCRIPT_DIR.listFiles();

        if (files == null) files = new File[0];

        for (File file : files) {
            if (!file.isFile()) return;

            text.append(file.getName());

            text.append(" r:");
            if(file.canRead()) text.append("+"); else text.append("-");
            text.append(" x:");
            if(file.canExecute()) text.append("+"); else text.append("-");
            text.append("\n");
        }

        main.addView(text);

        setContentView(main);
    }

}
