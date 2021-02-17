package model;

public class Qcl2SencondPart {
	
	private IControl control;

	private String operator;
	private String[] cordonSecuritaire = new String [11];

	public Qcl2SencondPart(Control control) {
		this.control = control;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String[] getCordonSecuritaire() {
		return cordonSecuritaire;
	}

	public void setCordonSecuritaire(int index, String value) {
		if (index > 0 && index <= 11){
			this.cordonSecuritaire [index - 1]= value;
		}
	}

	public IControl getControl() {
		return control;
	}

}
