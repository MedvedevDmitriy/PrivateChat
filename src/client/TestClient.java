package client;

public class TestClient
{
	public static void main(String[] args)
	{
		String host = "localhost";
		int port = 7777;
		new TestClientImpl(host, port);
	}
}