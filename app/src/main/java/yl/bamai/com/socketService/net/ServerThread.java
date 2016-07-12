package yl.bamai.com.socketService.net;

import android.util.Log;


import org.xsocket.connection.INonBlockingConnection;
import org.xsocket.connection.IServer;
import org.xsocket.connection.Server;

import yl.bamai.com.socketService.ui.ChatUI;


public class ServerThread extends Thread {

	private IServer srv;
	private ServerHandler serverHandler;

	public ServerThread(ChatUI chatService) {
		serverHandler = new ServerHandler();
		serverHandler.addObserver(chatService);
	}

	@Override
	public void run() {
		try {
			srv = new Server(ServiceConfig.PORT, serverHandler);
			srv.start();
			Log.e("ClientThread","启动成功！");

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ClientThread","启动失败！");
		}
	}

	public void sendAll(String content) {
		if ("".equals(content)) {
			Log.e("ClientThread","消息不能为空！");
		} else {
			for (INonBlockingConnection nbc : serverHandler.nbcs.values()) {
				try {
					if (nbc != null && nbc.isOpen()) {
						nbc.write(content + "\r\n");
						nbc.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void send(String id, String content) {
		if ("".equals(content)) {
			Log.e("ClientThread","ID不能为空！");
		} else if ("".equals(content)) {
			Log.e("ClientThread","消息不能为空！");
		} else {
			INonBlockingConnection nbc = serverHandler.loginNbcs.get(id);
			try {
				if (nbc != null && nbc.isOpen()) {
					nbc.write(content + "\r\n");
					nbc.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		if (srv != null) {
			try {
				srv.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			srv = null;
		}
	}

}