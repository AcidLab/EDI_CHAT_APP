package co.acidlabs.lanala;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.acidlabs.lanala.helpers.Utils;


public class PayActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;
    int i = 0;
    String url = "https://edi.proassur.tn/PROASSUR.LANALA/WebUI/Financier/PaiementEnLigneInMobile.aspx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);



        //webView.loadUrl(url);





    }



    @Override
    public void onStart() {
        super.onStart();

         WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Loading started for URL
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (i == 0) {
                    if (Utils.fromQ == true) {

                        try {
                            //Toast.makeText(getApplicationContext(),Utils.QtoPay().toString(),Toast.LENGTH_LONG).show();
                            Log.e("row",Utils.QtoPay().toString());
                            view.postUrl(url,Utils.QtoPay().toString().getBytes("utf-8"));
                            //finish();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }


                    }

                    else {

                        //url = "https://edi.proassur.tn/PROASSUR.LANALA/WebUI/Financier/PaiementEnLigneInMobile.aspx?idCot=C";
                        try {
                            //Toast.makeText(getApplicationContext(),Utils.LtoPay(Utils.selectedContract.getNumeroPolice(),getIntent().getDoubleExtra("montant",0)).toString(),Toast.LENGTH_LONG).show();
                            Log.e("row",Utils.QtoPay().toString());
                            view.postUrl(url,Utils.LtoPay(Utils.selectedContract.getNumeroPolice(),getIntent().getDoubleExtra("montant",0)).toString().getBytes("utf-8"));
                            //finish();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }

                else {
                    view.loadUrl(url);
                }
                i++;

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Loading finished for URL

            }

        };

        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);

        if (Utils.fromQ == true) {

            try {
                //Toast.makeText(getApplicationContext(),Utils.QtoPay().toString(),Toast.LENGTH_LONG).show();
                Log.e("row",Utils.QtoPay().toString());
                webView.postUrl(url,Utils.QtoPay().toString().getBytes("utf-8"));
                //finish();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }


        }

        else {

            url = "https://edi.proassur.tn/PROASSUR.LANALA/WebUI/Financier/PaiementEnLigneInMobile.aspx?idCot=C";
            try {
                //Toast.makeText(getApplicationContext(),Utils.LtoPay(Utils.selectedContract.getNumeroPolice(),getIntent().getDoubleExtra("montant",0)).toString(),Toast.LENGTH_LONG).show();
                Log.e("row",Utils.QtoPay().toString());
                webView.postUrl(url,Utils.LtoPay(Utils.selectedContract.getNumeroPolice(),getIntent().getDoubleExtra("montant",0)).toString().getBytes("utf-8"));
                //finish();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }


    }

    public void onBackClick (View v) {

        Utils.selectedBox.clear();
        finish();
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        Utils.selectedBox.clear();
    }

    public class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            //progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {

            // TODO Auto-generated method stub

            super.onPageFinished(view, url);
            //progressBar.setVisibility(View.GONE);
        }

    }

}
