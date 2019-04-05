import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ZD_Model {
	//static final String INPUT_CHANGE = "in";
	//static final String BASE_INPUT_CHANGE = "basein";
	//static final String BASE_OUTPUT_CHANGE = "baseout"; 
	static final String OUTPUT_CHANGE = "out";
	String number_output;int base_input, base_output;
	int result, number_input;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	public int getNumber_input() {
		return number_input;
	}
	public void setNumber_input(Integer integer) {
		this.number_input = integer;
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
	public int getResult() {
		
		int output = convertToDecimal(getBase_input());
		return output;
	}
	public ZD_Model(int number_input, int base_input, int base_output) {
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
	private int convertToDecimal(int base)
	{
		int decimal_value = getNumber_input();
		base = getBase_input();
		if(base != 10)
		{
			
			int begin = 0;
			String input = Integer.toString(getNumber_input());
			
			for(int i = 0; i < input.length(); i++)
			{
				int digit = Integer.valueOf(input.charAt(i)) - '0';
				decimal_value = (digit + begin);
				begin = decimal_value * base; 
			}
		}
		return decimal_value;
	}
	
}
