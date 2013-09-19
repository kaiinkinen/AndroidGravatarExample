package com.futurice.kaiinkinen.yetanotherapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.text.Editable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private Gravatar gravatar;
    private Button theButton;
    private EditText editText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        theButton = (Button) findViewById(R.id.submitButton);
        theButton.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.emailInput);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    return true;
                }

                return false;
            }
        });

        imageView = (ImageView) findViewById(R.id.gravatarImage);

        gravatar = new Gravatar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submitButton) {
            imageView.setImageBitmap(null);
            downloadImage();
        }
    }

    private void downloadImage() {
        final String email = editText.getText().toString();
        final String url = gravatar.getUrl(email);
        new DownloadImageTask(imageView).execute(url);
        startActivity(new Intent(this, getClass()));
//        finish();
    }

}
