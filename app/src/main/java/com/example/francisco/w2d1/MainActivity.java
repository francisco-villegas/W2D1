package com.example.francisco.w2d1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Main";
    private WebView webView;
    Button btn1,btn2;
    EditText et1,et2;
    TextView tv1,tv2;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView= (WebView) findViewById(R.id.webview);

        WebViewClient webViewClient = new WebViewClient();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("https://developer.android.com");

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn1:
                MyContact contact = new MyContact(et1.getText().toString(), et2.getText().toString());
                Log.d(TAG, "onClick: "+contact.getName() + " " + contact.getPhone());
                databaseHelper.saveNewContact(contact);
                break;
            case R.id.btn2:
                ArrayList<MyContact> contacts = databaseHelper.getContacts();
                String tv1S = "", tv2S="";
                for(int i=0;i<contacts.size();i++){
                    tv1S += contacts.get(i).getName() + "\n";
                    tv2S += contacts.get(i).getPhone() + "\n";
                }
                tv1.setText(tv1S);
                tv2.setText(tv2S);
                break;
        }
    }
}
