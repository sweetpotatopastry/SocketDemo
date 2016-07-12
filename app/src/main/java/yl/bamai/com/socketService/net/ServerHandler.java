package yl.bamai.com.socketService.net;

import android.util.Log;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;

import yl.bamai.com.socketclient.ui.UpdateInfo;


// , IConnectHandler, IDisconnectHandler, IConnectionTimeoutHandler
public class ServerHandler extends Observable implements IDataHandler, IConnectHandler, IDisconnectHandler {

	public Map<String, INonBlockingConnection> nbcs;
	public Map<String, INonBlockingConnection> loginNbcs;

	public ServerHandler() {
		nbcs = new HashMap<>();
		loginNbcs = new HashMap<>();
	}

	@Override
	public boolean onConnect(INonBlockingConnection nbc)
			throws IOException, BufferUnderflowException {
		Log.e("ServerHandler" ,"=====ServerHandler连接成功==========================<0  service>"+"nbc.getId() ="+nbc.getId());
		nbcs.put(nbc.getId(), nbc);
		setChanged();
		notifyObservers(new UpdateInfo(1, nbcs.size() + ""));
		return true;
	}

	@Override
	public boolean onDisconnect(INonBlockingConnection nbc) throws IOException {
		Log.e("ServerHandler" ,"=====ServerHandler连接断开==========================<0  service>"+"nbc.getId() ="+nbc.getId());

		nbcs.remove(nbc.getId());
//		loginNbcs.remove(loginNbcs)loginNbcs;
		setChanged();
		notifyObservers(new UpdateInfo(1, nbcs.size() + ""));
		return true;
	}

	@Override
	public boolean onData(INonBlockingConnection nbc)
			throws IOException, BufferUnderflowException{
		String data = nbc.readStringByDelimiter("\r\n");
		Log.e("ServerHandler" ,"=====ServerHandler收到数据：==========================<0  service>"+"nbc.getId() ="+nbc.getId());
		if (data.startsWith("#id#") && data.endsWith("#id#")) {
			String id = data.replace("#id#", "");
			if (loginNbcs.containsKey(id)) {
				nbc.write("idError\r\n");
				nbc.flush();
			} else {
				loginNbcs.put(id, nbcs.get(nbc.toString()));
				setChanged();
				notifyObservers(new UpdateInfo(2, id));
			}
		} else {
			setChanged();
			notifyObservers(new UpdateInfo(0, data));
		}
		return true;
	}

}