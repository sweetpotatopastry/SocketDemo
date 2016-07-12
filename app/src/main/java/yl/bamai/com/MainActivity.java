package yl.bamai.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yl.bamai.com.socketService.ui.ChatUI;
import yl.bamai.com.socketclient.R;
import yl.bamai.com.socketclient.ui.ChatClientMain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_client).setOnClickListener(this);
        findViewById(R.id.btn_server).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_client:
                startActivity(new Intent(MainActivity.this,ChatClientMain.class));
                break;
            case R.id.btn_server:
                startActivity(new Intent(MainActivity.this,ChatUI.class));
                break;
        }
    }
}
