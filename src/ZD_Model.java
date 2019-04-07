import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ZD_Model {
	//static final String INPUT_CHANGE = "in";
	//static final String BASE_INPUT_CHANGE = "basein";
	//static final String BASE_OUTPUT_CHANGE = "baseout"; 
	static final String OUTPUT_CHANGE = "out";
	String number_output;int base_input, base_output;
	int result;
	String number_input;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	public String getNumber_input() {
		return number_input;
	}
	public void setNumber_input(String string) {
		this.number_input = string;
	}
	public String getNumber_output() {
		return number_output;
	}
	public void setNumber_output(String number_output) {
		this.number_output = number_output;
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
	public String getResult() {
		
		String output = fromDeci();
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
	public void process()
	{
		int oldResult = result;
		pcs.firePropertyChange(OUTPUT_CHANGE,  oldResult, getResult());

	}
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
	private int convertToDecimal()
	{
		
		
		int base = getBase_input();
		if(base < 10)
		{
			int decimal_value = Integer.valueOf(getNumber_input());
			int begin = 0;
			String input = Integer.toString(getNumber_input());
			
			for(int i = 0; i < input.length(); i++)
			{
				int digit = Integer.valueOf(input.charAt(i)) - '0';
				if(digit >= base)
				{
					throw new NumberFormatException();
				}
				decimal_value = (digit + begin);
				begin = decimal_value * base; 
			}
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
	private  String fromDeci() 
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
	    String res = sb.reverse().toString(); 

	    // Reverse the result 
	    //reverse(res); 
	    return new String(res); 
	}
}
