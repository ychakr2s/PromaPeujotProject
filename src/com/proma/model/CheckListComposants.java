package model;

public class CheckListComposants extends Control{

	//Check points
	private String[] composantsCheckResult = new String [22];
	public String qcl1Path = "C:/Users/CYassine/Desktop/Quality_Control/Veritasium/CheckListComopsants/";
	public String fileEndingQcl1 = ".qcl1";

	public CheckListComposants(String date, int shift, int numChassis, String gravure, Operator operator, int status) {
		super(date, shift, numChassis, gravure, operator, status);
	}

	public String[] getComposants() {
		return composantsCheckResult;
	}

	public void setComposants(int index, String value) {
		if (index >= 0 && index < 22){
			this.composantsCheckResult [index]= value;
		}
	}
	
	@Override
	public String[] getCheckPoints() {
		return getComposants();
	}
	

	@Override
	public String getCheckListPath() {
		return qcl1Path;
	}

	@Override
	public String getfileEnding() {
		return fileEndingQcl1;
	}
}
