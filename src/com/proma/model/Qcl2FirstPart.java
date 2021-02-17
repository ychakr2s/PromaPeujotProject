package model;

public class Qcl2FirstPart {
	
	private IControl control;

	private String operator;
	private String[] cordonNonSecuritaire = new String [18];

	public Qcl2FirstPart(Control control) {
		this.control = control;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String[] getCordonNonSecuritaire() {
		return cordonNonSecuritaire;
	}

	public void setCordonNonSecuritaire(int index, String value) {
		if (index > 0 && index <= 18){
			this.cordonNonSecuritaire [index - 1]= value;
		}
	}

	public IControl getControl() {
		return control;
	}

}
