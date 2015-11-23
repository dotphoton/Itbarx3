package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.custom.component.ButtonBold;
import com.itbarx.custom.component.ButtonRegular;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.utils.FileUtility;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class TestActivity extends BaseActivity {

	Button btnConvert;
	TextView textView;
	SharedPreferences.Editor preferenceEditor;

	@Override protected int getLayoutResourceId() {

		return R.layout.test;
	}

	@Override protected Context getContext() {

		return TestActivity.this;
	}

	@Override protected void initViews() {
		btnConvert = (Button) findViewById(R.id.button_Convert);
		textView = (TextView) findViewById(R.id.text);

	}

	public void generateNoteOnSD(String sFileName, String sBody) {
		try {
			File root = new File(Environment.getExternalStorageDirectory(), "Notes");
			if (!root.exists()) {
				root.mkdirs();
			}
			File gpxfile = new File(root, sFileName);
			FileWriter writer = new FileWriter(gpxfile);
			writer.append(sBody);
			writer.flush();
			writer.close();
			Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}
