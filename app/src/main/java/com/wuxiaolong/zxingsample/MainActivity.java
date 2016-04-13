package com.wuxiaolong.zxingsample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.appcompat.BuildConfig;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.encoding.EncodingHandler;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 0;

    Button scannerQRCode, generateQRCode;
    ImageView qrcodeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannerQRCode = (Button) findViewById(R.id.qrcode_dencode);
        generateQRCode = (Button) findViewById(R.id.qrcode_encode);
        qrcodeImg = (ImageView) findViewById(R.id.qrcode_img);

        scannerQRCode.setOnClickListener(this);
        generateQRCode.setOnClickListener(this);
        Log.d("wxl", "DEBUG=" + BuildConfig.DEBUG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_dencode: //扫描
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.qrcode_encode: //生成
                try {
                    Bitmap mBitmap = EncodingHandler.createQRCode("www.baidu.com", 300);
                    qrcodeImg.setImageBitmap(mBitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(MainActivity.this, scanResult, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
