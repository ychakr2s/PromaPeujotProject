package model;

/*import java.text.SimpleDateFormat;
 import java.util.Date;
 */
public class Control implements IControl {

    private String date;
    private Shift shift;
    private NumChassis numChassis;
    private Gravure gravure;

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    private Operator operator;
    private ControlStatus status;
    private String filePath;
    private String fileEnding;

    // anwa3 l mola7adat lli momkina
    private String[][] checkResults = {{"OK", null}, {"M", null},
            {"R", null}, {"S", null}, {"F", null}, {"2S", null},
            {"2F", null}, {"3S+", null}, {"3F+", null}, {"TR", null},
            {"2TR", null}, {"3TR", null}, {"4TR+", null}, {"Porosit�", null}, {"D�calage", null}, {"Perc�", null}, {"P�n�tration", null}};

    public Control(String date, int shift, int numChassis, String gravure, Operator operator, int status) {
        // General information to the current control
        // date = getStartDate();
        this.date = date;
        this.shift = new Shift(shift);
        this.numChassis = new NumChassis(numChassis);
        this.gravure = new Gravure(gravure);
        this.operator = operator;
        this.setStatus(status);
    }

    public String getControlDate() {
        return date;
    }

    public Shift getShift() {
        return shift;
    }

    public NumChassis getNumChassis() {
        return numChassis;
    }

    public Gravure getGravure() {
        return gravure;
    }

    public Operator getOperator() {
        return operator;
    }

    public String[][] getPossibleCheckResults() {
        return checkResults;
    }

    @Override
    public void setPossibleCheckResults(String[][] checkResults) {
        this.checkResults = checkResults;
    }

    public ControlStatus getStauts() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = new ControlStatus(status);
    }
	@Override
    public void resetStatus() {
        this.status = null;
    }

    @Override
    public void setOperatorByName(String selectedControleur) {
        if (null == operator) {
            setOperator(Operator.create(selectedControleur));
            //operator = Operator.create(selectedControleur);
        } else {
            operator.assignOperatorByName(selectedControleur);
        }
    }

    @Override
    public String[] getCheckPoints() {
        return null;
    }

    // Path without file name
    @Override
    public String getCheckListPath() {
        return filePath;
    }

    // complete file path including file name and file ending
    @Override
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String path) {
        filePath = path;
    }

    @Override
    public String getfileEnding() {
        return fileEnding;
    }

    @Override
    public String getfileName() {
        String numChassis = "" + getNumChassis().getNumChassis();
        String gravure = getGravure().getGravure();
        return numChassis + gravure;
    }

    public String controlToString() {
        String controlToString;
        String delimiter = " ";

        controlToString = getControlDate() + delimiter;
        controlToString = controlToString + getShift().getShift() + delimiter;
        controlToString = controlToString + getNumChassis().getNumChassis() + delimiter;
        controlToString = controlToString + getGravure().getGravure() + delimiter;
        controlToString = controlToString + getOperator().getName() + delimiter;
        controlToString = controlToString + getOperator().getSurname() + delimiter;
        controlToString = controlToString + getStauts().statusToString() + delimiter;

        for (String element : getCheckPoints()) {
            controlToString = controlToString + element + delimiter;
        }

        return controlToString;
    }

}
