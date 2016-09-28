package cn.dcoder.audoupdatedemo;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.dcoder.autoupdate.UpdateManager;

public class MainActivity extends AppCompatActivity {
    public int curr = 0;



    public void httpTest(){
    /*    Log.e("TEST",getPath());
       // File[] files = getExternalFilesDirs(null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://lab.dcoder.cn/test.mp3");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                HttpURLConnection httpURLConnection = null;
                try {

                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    //httpURLConnection.
                    //Log.e("TEST", "LEN:" + httpURLConnection.getContentLength());
                    final int size = httpURLConnection.getContentLength();

                    InputStream in = httpURLConnection.getInputStream();

                    File file = new File(getPath() + "/test.mp3");

                    FileOutputStream fs = new FileOutputStream(file);

                    byte[] buffer = new byte[10240];

                    int len = 0;

                    while((len = in.read(buffer)) != -1){
                        fs.write(buffer, 0 , len);
                        Log.d("TEST",len + " has been written");
                        curr += len;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                                progressBar.setProgress((int)(100.0 * curr / size));
                            }
                        });
                    }

                    Log.d("TEST","OVER");

                    fs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        }).start();*/


        try {
            String url = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("Update-Param");
            Log.e("TEST",url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* httpTest();
                    Log.e("TEST","CLICK");
                    try {
                        String url = MainActivity.this.getPackageManager().getApplicationInfo(MainActivity.this.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("Update-Param");
                        Log.e("TEST",url);
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/
                    UpdateManager updateManager = new UpdateManager.Builder(MainActivity.this)
                            .checkUrl("http://lab.dcoder.cn/update.json")
                            .build();
                    updateManager.check();
                }
            });
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
