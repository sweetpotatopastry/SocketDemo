package yl.bamai.com.socketService.ui;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import yl.bamai.com.socketService.net.ServerThread;
import yl.bamai.com.socketclient.R;
import yl.bamai.com.socketclient.ui.UpdateInfo;

@SuppressWarnings("serial")
public class ChatUI extends Activity implements  View.OnClickListener, Observer {

	private static final String START_SERVICE = "启动服务器";
	private static final String STOP_SERVICE = "关闭服务器";
	private static final String SEND_GROUP = "群聊";
	private static final String SEND_CHAT = "单聊";

	private ServerThread serverThread;
	private TextView show_meassge;
	private ChatUI chat;
	private EditText et_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		findViewById(R.id.start_service).setOnClickListener(this);
		findViewById(R.id.stop_service).setOnClickListener(this);
		findViewById(R.id.send_chat).setOnClickListener(this);
		findViewById(R.id.send_group).setOnClickListener(this);
		show_meassge = (TextView) findViewById(R.id.show_message);
		et_content = (EditText) findViewById(R.id.et_content);

	}
	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.start_service:
				startService();
				break;
			case R.id.stop_service:
				stopService();
				break;
			case R.id.send_chat:
				sendChat();
				break;
			case R.id.send_group:
				sendGroup();
				break;
		}
	}

	public void startService() {
		stopService();
		serverThread = new ServerThread(this);
		serverThread.start();
	}

	public void stopService() {
		if (serverThread != null) {
			serverThread.close();
			serverThread = null;
		}
	}

	public void sendGroup() {
		serverThread.sendAll(new Date().toLocaleString() + ": 都可以收到的信息" );
		show_meassge.setText("");
	}

	public void sendChat() {
		serverThread.send(et_content.getText().toString().trim() + "","只有你可以收到的信息");
		show_meassge.setText("");
	}

	@Override
	public void update(Observable observable, Object o) {
		UpdateInfo info = (UpdateInfo) o;
		if (info.type == 0) {
			Log.e("updateInfo.type" ,"===============================================<0  service>");
			show_meassge.setText(info.msg);
		} else if (info.type == 1) {
			Log.e("updateInfo.type" ,"===============================================<1  service>");
			show_meassge.setText(info.msg);
		} else if (info.type == 2) {
			Log.e("updateInfo.type" ,"===============================================<1  service>");
			show_meassge.setText(info.msg);
		}
	}
}
