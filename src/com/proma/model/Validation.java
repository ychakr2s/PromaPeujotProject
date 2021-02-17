package model;


import controller.ControlProcessor;

public class Validation extends Control{
	
	private CheckListComposants qcl1;
	private Qcl2FirstPart qcl2FirstPart;
	private Qcl2SencondPart qcl2SecondPart;
	private Qcl3 qcl3;
	private ControlProcessor processor = ControlProcessor.getInstance(this);
	
	private String [] allPoints = new String [76];

	public Validation(String date, int shift, int numChassis, String gravure, Operator operator, int status) {
		super(date, shift, numChassis, gravure, operator, status);
		//different controls that belong to this control
		this.qcl1 = getQcl1();
		this.qcl2FirstPart = getQcl2FirstPart();
		this.qcl2SecondPart = getQcl2SecondPart();
		this.qcl3 = getQcl3();
	}

	public CheckListComposants getQcl1() {
		
		return qcl1;
	}

	public Qcl2FirstPart getQcl2FirstPart() {
		return qcl2FirstPart;
	}

	public Qcl2SencondPart getQcl2SecondPart() {
		return qcl2SecondPart;
	}

	public Qcl3 getQcl3() {
		return qcl3;
	}

	public String [][] getCountCheckedPoints() {
		return getPossibleCheckResults();
	}

	public void setCountCheckedPoints() {
		setPossibleCheckResults(processor.getCountCheckedPoints(getAllPoints()));
	}
	
	public String[] getAllPoints() {
		int pos = 0;
		for (String element1 : qcl1.getComposants()){
			allPoints [pos] = element1;
			pos++;
		}
		for (String element2 : qcl2FirstPart.getCordonNonSecuritaire()){
			allPoints [pos] = element2;
			pos++;
		}
		for (String element3 : qcl2SecondPart.getCordonSecuritaire()){
			allPoints [pos] = element3;
			pos++;
		}
		for (String element4 : qcl3.getTrous()){
			allPoints [pos] = element4;
			pos++;
		}
		for (String element5 : qcl3.getFinition()){
			allPoints [pos] = element5;
			pos++;
		}
		for (String element6 : qcl3.getRackBatrie()){
			allPoints [pos] = element6;
			pos++;
		}
		return allPoints; 
	}

}
