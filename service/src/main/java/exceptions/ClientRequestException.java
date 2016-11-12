package exceptions;

public class ClientRequestException extends RuntimeException
{
	public ClientRequestException(String mes)
	{
		super(mes);
	}
}
