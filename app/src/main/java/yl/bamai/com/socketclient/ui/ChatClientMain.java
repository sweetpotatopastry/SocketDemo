package yl.bamai.com.socketclient.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import yl.bamai.com.socketclient.R;
import yl.bamai.com.socketclient.net.ClientThread;


@SuppressWarnings("serial")
public class ChatClientMain extends Activity implements Observer{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client);
		findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				connect();
			}
		});
		findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				send();
			}
		});

	}

	private ClientThread clientThread;

	public void connect() {
		if (clientThread != null){
			clientThread.close();
		}
		clientThread = new ClientThread(this);
		clientThread.start();
	}

	public void send() {
		clientThread.send(new Date().toLocaleString()+": 模拟消息啦啦");
	}

	@Override
	public void update(Observable observable, Object o) {
		UpdateInfo updateInfo = (UpdateInfo)o;

		if (updateInfo.type == 0){
			Log.e("updateInfo.type" ,"===============================================< 1 >");
		}else if (updateInfo.type == 1){
			Log.e("updateInfo.type" ,"===============================================< 2>");
		}

	}
}
