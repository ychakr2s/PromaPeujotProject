package model;

public interface IControl {

    //Content of the control general info
    String getControlDate();

    Shift getShift();

    NumChassis getNumChassis();

    Gravure getGravure();

    Operator getOperator();

    ControlStatus getStauts();

    String[][] getPossibleCheckResults();

    String[] getCheckPoints();

    String getCheckListPath();

    String getfileName();

    String getfileEnding();

    String controlToString();

    String getFilePath();

    void setFilePath(String filePath);

    void setPossibleCheckResults(String[][] countCheckedPoints);

    void setStatus(int i);

    void resetStatus();

    void setOperatorByName(String selectedControleur);
}
