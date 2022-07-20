package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

public class MainActivity extends AppCompatActivity {

    private final int BOTH_PERMISSION_CODE = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestGlobalPermission();
    }

    private void requestGlobalPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA )){
            new AlertDialog.Builder(this)
                    .setTitle("Necessidade de permissão")
                    .setMessage("Precisamos da sua permissão para utilizar a câmera e o microfone.")
                    .setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, BOTH_PERMISSION_CODE))
                    .create()
                    .show();

        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, BOTH_PERMISSION_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == BOTH_PERMISSION_CODE){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Microfone e câmera permitidos", Toast.LENGTH_SHORT).show();
                renderWebview();
            } else {
                Toast.makeText(this, "Microfone e/ou câmera não permitidos", Toast.LENGTH_SHORT).show();
                requestGlobalPermission();
            }
        }
    }



    @SuppressLint("SetJavaScriptEnabled")
    private void renderWebview(){
        WebView myWebView = findViewById(R.id.webview);


        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWebView.getSettings().setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        CookieManager.getInstance().setAcceptThirdPartyCookies(myWebView, true);

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
                handler.proceed();
                // Ignore SSL certificate errors
            }
        });
        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.getResources());
                }
            }

        });

        myWebView.loadUrl("https://hml-vonage.conexasaude.com.br/ionicroom?name=Pac._GUILHERME_MIRANDA&sessionId=aqui_vai_o_numero_da_session&token=aqui_vai_o_numero_do_token&doctor=false&docpass=false&external=true&plataforma=CONEXA&apikey=aqui_vai_a_apikey");
    }
}