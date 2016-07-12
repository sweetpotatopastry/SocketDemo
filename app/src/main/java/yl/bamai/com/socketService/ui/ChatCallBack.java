package yl.bamai.com.socketService.ui;

public interface ChatCallBack {

	/**
	 * 启动服务
	 */
	public void startService();

	/**
	 * 停止服务
	 */
	public void stopService();

	/**
	 * 群聊
	 */
	public void sendGroup();

	/**
	 * 单聊
	 */
	public void sendChat();

}
