package yl.bamai.com.socketclient.net;


import android.util.Log;

import org.xsocket.connection.INonBlockingConnection;
import org.xsocket.connection.NonBlockingConnection;


import yl.bamai.com.socketclient.ui.ChatClientMain;

public class ClientThread extends Thread {

	private ClientHandler clientHandler;
	public boolean isNotMatched = false;

	private INonBlockingConnection nbc;

	public ClientThread(ChatClientMain chatClient) {
		clientHandler = new ClientHandler();
		clientHandler.addObserver(chatClient);
	}
	boolean flage = true;
	@Override
	public void run() {
		while (flage){
			try {
				if (nbc == null || !nbc.isOpen()) {
					nbc = new NonBlockingConnection(ClientConfig.SERVICE_IP, ClientConfig.PORT, clientHandler);
				}
				Log.e("ClientThread","连接成功");
				flage = false;
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("ClientThread","连接失败!");
				flage = true;
			}
		}

	}

	public void send(String content) {
		if ("".equals(content)) {
			Log.e("ClientThread","消息不能为空!");
		} else {
			try {
				if (nbc != null && nbc.isOpen()) {
					nbc.write(content + "\r\n");
					nbc.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("ClientThread","发送失败!");
			}
		}
	}

	public void close() {
		if (nbc != null) {
			try {
				nbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			nbc = null;
		}
	}

}