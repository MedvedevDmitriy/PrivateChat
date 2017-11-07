package server;

import java.util.concurrent.LinkedBlockingQueue;

public class MsgQueue
{
	private static LinkedBlockingQueue<String> msg;
	
	public static LinkedBlockingQueue<String> getI()
	{
		if( msg == null )
		{
			msg = new LinkedBlockingQueue<String>();	
		}	
		return msg;
	}
}