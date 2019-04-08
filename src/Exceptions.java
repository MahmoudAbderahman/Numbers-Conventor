
public class Exceptions extends Exception {
	private String message;
	public Exceptions(String message)
	{
		super(message);
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}
}
