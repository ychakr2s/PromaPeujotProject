package model;

public class Qcl3 {
	
	private IControl control;

	private String operator;
	private String[] trous = new String [14];
	private String[] finition = new String [8];
	private String[] rackBatrie = new String [3];

	public Qcl3(Control control) {
		this.control = control;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String[] getTrous() {
		return trous;
	}

	public void setTrous(int index, String value) {
		if (index > 0 && index <= 14){
			this.trous [index - 1]= value;
		}
	}

	public String[] getFinition() {
		return finition;
	}

	public void setFinition(int index, String value) {
		if (index > 0 && index <= 8){
			this.finition [index - 1]= value;
		}
	}

	public String[] getRackBatrie() {
		return rackBatrie;
	}

	public void setRackBatrie(int index, String value) {
		if (index > 0 && index <= 3){
			this.finition [index - 1]= value;
		}
	}

	public IControl getControl() {
		return control;
	}

}
