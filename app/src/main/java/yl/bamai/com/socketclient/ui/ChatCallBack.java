package yl.bamai.com.socketclient.ui;

public interface ChatCallBack {

	/**
	 * 连接服务器
	 */
	public void connect();

	/**
	 * 登录
	 */
	public void login();

	/**
	 * 发送消息
	 */
	public void send();

}
