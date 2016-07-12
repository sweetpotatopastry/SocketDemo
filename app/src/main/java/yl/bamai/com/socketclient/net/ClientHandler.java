package yl.bamai.com.socketclient.net;

import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.Observable;

import yl.bamai.com.Utils;
import yl.bamai.com.socketclient.ui.UpdateInfo;


public class ClientHandler extends Observable implements IDataHandler, IDisconnectHandler {

	@Override
	public boolean onData(INonBlockingConnection nbc)
			throws IOException, BufferUnderflowException {
		String data = nbc.readStringByDelimiter("\r\n");
		System.out.println("收到数据：" + data);
		if ("idError".equals(data)) {
			Utils.showToast("唯一标识冲突，请重新设置！");
			setChanged();
			notifyObservers(new UpdateInfo(1, data));
		} else {
			setChanged();
			notifyObservers(new UpdateInfo(0, data));
		}
		return true;
	}

	@Override
	public boolean onDisconnect(INonBlockingConnection arg0) throws IOException {
		Utils.showToast("网络中断！");
		return true;
	}

}