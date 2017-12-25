package com.demo.android.bmi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InterruptedIOException;


public class Bmi extends AppCompatActivity {

    Button button;
    EditText fieldHeight, fieldWeight;
//    TextView result, suggest;
    String[] items;
    int[] iconRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        init();
        findViews();
        setOnClickListener();
    }

    void init() {
        items = getResources().getStringArray(R.array.items);
        iconRes = new int[]{
                R.drawable.bullbasaur,
                R.drawable.pikachu,
                R.drawable.meowth,
                R.drawable.psyduck,
                R.drawable.snorlax};
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    void findViews() {
        button = (Button) findViewById(R.id.submit);
        fieldHeight = (EditText) findViewById(R.id.height);
        fieldWeight = (EditText) findViewById(R.id.weight);
//        result = (TextView) findViewById(R.id.result);
//        suggest = (TextView) findViewById(R.id.suggest);
    }

    void setOnClickListener() {
        button.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Bmi.this, Report.class);
            startActivity(intent);
            intent = new Intent();
            intent.setClass(Bmi.this, Report.class);
            Bundle bundle = new Bundle();
            bundle.putString("KEY_HEIGHT",fieldHeight.getText().toString());
            bundle.putString("KEY_WEIGHT",fieldWeight.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu,menu);
        for (int i = 0; i < items.length; i++)
            menu.add(0, (i + 1) * 100, 0, items[i]).setIcon(iconRes[i]).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    Intent intent;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 100: openOptionsDialog();break;
            case 200:
            case 300:
            case 400:
            case 500:
                Toast.makeText(Bmi.this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    void openOptionsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.about_title);
        builder.setMessage(R.string.about_msg);
        builder.setNegativeButton("首頁",dialog_listener);
        builder.show();
    }

    DialogInterface.OnClickListener dialog_listener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
//            Uri myBlogUri = Uri.parse("http://google.com");
            intent = new Intent();
            intent.setClass(Bmi.this, Report.class);
            Bundle bundle = new Bundle();
            bundle.putString("KEY_HEIGHT",fieldHeight.getText().toString());
            bundle.putString("KEY_WEIGHT",fieldWeight.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

}
