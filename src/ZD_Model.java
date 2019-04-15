import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ZD_Model {
	
	static final String OUTPUT_CHANGE = "out";
	int base_input, base_output;
	int result;
	String number_input;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	public String getNumber_input() {
		return number_input;
	}
	public void setNumber_input(String string) {
		this.number_input = string;
	}
	
	public int getBase_input() {
		return base_input;
	}
	public void setBase_input(Integer integer) {
		this.base_input = integer;
	}
	public int getBase_output() {
		return base_output;
	}
	public void setBase_output(int base_output) {
		this.base_output = base_output;
	}
	public String getResult() throws Exceptions {
		
		String output = fromDeci();
		if(output == null || output.length() == 0)
		{
			return "0";
		}
		return output;
	}
	public ZD_Model(String number_input, int base_input, int base_output) {
		super();
		this.number_input = number_input;
		this.base_input = base_input;
		this.base_output = base_output;
	}

	public ZD_Model() {
		super();
		
	}
	public void process() throws Exceptions
	{
		int oldResult = result;
		pcs.firePropertyChange(OUTPUT_CHANGE,  oldResult, getResult());

	}
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
	
	private int convertToDecimal() throws Exceptions
	{
		
		int decimal_value = 0;
		int base = getBase_input();
		boolean base_morethan_ten = base >= 10 ? true : false;
		String input;
		String digits = "0123456789ABCDEF";
		if(base_morethan_ten)
		{
			input = getNumber_input().toUpperCase();
		}
		else
		{
			input = getNumber_input();
		}
		
		for(int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);
			int d = digits.indexOf(c);
			if(checkNumber(d) == true)
			{
				throw new NumberFormatException("Eine Ziffer ist groesser als die Basis!");
			}

			if(overflowCheck(decimal_value, d) == true)
			{
				throw new Exceptions("Die eingegebene Nummer verursacht Integerueberlauf!");
			}
			decimal_value = base*decimal_value + d;

		}
		return decimal_value;
	}
	// To return char for a value. For  
	// example '2' is returned for 2.  
	// 'A' is returned for 10. 'B' for 11 
	private  char reVal(int num) 
	{ 
	    if (num >= 0 && num <= 9) 
	        return (char)(num + 48); 
	    else
	        return (char)(num - 10 + 65); 
	} 
	
	// Function to convert a given decimal number 
	// to a base 'base' and 
	private  String fromDeci() throws Exceptions 
	{ 
	    String s = ""; 
	    int decimal_num = convertToDecimal();
	    
	    // Convert input number is given  
	    // base by repeatedly dividing it 
	    // by base and taking remainder 
	    while (decimal_num > 0) 
	    { 
	        s += reVal(decimal_num % getBase_output()); 
	        decimal_num /= getBase_output(); 
	    } 
	    
	    StringBuilder sb = new StringBuilder(s);
	// Reverse the result 
	    String res = sb.reverse().toString(); 

	    
	    //reverse(res); 
	    return new String(res); 
	}
	
	// Function to check if a number bigger than the base
	private boolean checkNumber(int n)
	{
		if(n >= getBase_input() || n < 0)
		{
			return true;
		}
		return false;
	}
	// Check if the number causes integer overflow
	private boolean overflowCheck(int x, int z)
	{
		if(x > ((2147483647 - z) / getBase_input()))
		{
			return true;
		}
		return false;

	}
	
}
