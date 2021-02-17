package controller;

import model.IControl;

import java.io.File;


public class ControlProcessor {

    private static ControlProcessor controlProcessor = null;

    public IControl getControl() {
        return control;
    }

    private static IControl control;
    private String generalPath; //= "C:/Users/HASORA/Desktop/Quality_Control/Veritasium/CheckListComopsants/";
    private String fileEnding; // = ".qcl1";
    private WriteManager writeManager = WriteManager.getInstance();
    //the count of all necessary information of a control like start date, operator name, status ...
    private int controlGeneralInfoCount = 8;
    private int failureCount = 0;

    private ControlProcessor(IControl control) {
        this.control = control;
        generalPath = control.getCheckListPath();
        fileEnding = control.getfileEnding();
    }

    public static ControlProcessor getInstance(IControl control) {
        if (controlProcessor == null) {
            controlProcessor = new ControlProcessor(control);
        }
        return controlProcessor;
    }

    public static ControlProcessor getInstance() {
        return controlProcessor;
    }

    public String[][] getCountCheckedPoints(String[] allPoints) {
        String[][] countCheckedPoints = control.getPossibleCheckResults();
        int ok = 0, m = 0, r = 0, s = 0, f = 0, s2 = 0, f2 = 0, s3 = 0, f3 = 0, tr = 0, tr2 = 0, tr3 = 0, tr4 = 0, por = 0, dec = 0, per = 0, pen = 0;

        for (int i = allPoints.length; i >= 0; i--) {

            switch (allPoints[i]) {
                case "OK":
                    ok++;
                    break;
                case "M":
                    m++;
                    break;
                case "R":
                    r++;
                    break;
                case "S":
                    s++;
                    break;
                case "F":
                    f++;
                    break;
                case "2S":
                    s2++;
                    break;
                case "2F":
                    f2++;
                    break;
                case "3S+":
                    s3++;
                    break;
                case "3F+":
                    f3++;
                    break;
                case "TR":
                    tr++;
                    break;
                case "2TR":
                    tr2++;
                    break;
                case "3TR":
                    tr3++;
                    break;
                case "4TR+":
                    tr4++;
                    break;
                case "Porosit�":
                    por++;
                    break;
                case "D�calage":
                    dec++;
                    break;
                case "Perc�":
                    per++;
                    break;
                case "P�n�tration":
                    pen++;
                    break;
            }
        }

        for (String[] element : countCheckedPoints) {
            switch (element[0]) {
                case "OK":
                    element[1] = "" + ok;
                    break;
                case "M":
                    element[1] = "" + m;
                    break;
                case "R":
                    element[1] = "" + r;
                    break;
                case "S":
                    element[1] = "" + s;
                    break;
                case "F":
                    element[1] = "" + f;
                    break;
                case "2S":
                    element[1] = "" + s2;
                    break;
                case "2F":
                    element[1] = "" + f2;
                    break;
                case "3S+":
                    element[1] = "" + s3;
                    break;
                case "3F+":
                    element[1] = "" + f3;
                    break;
                case "TR":
                    element[1] = "" + tr;
                    break;
                case "2TR":
                    element[1] = "" + tr2;
                    break;
                case "3TR":
                    element[1] = "" + tr3;
                    break;
                case "4TR+":
                    element[1] = "" + tr4;
                    break;
                case "Porosit�":
                    element[1] = "" + por;
                    break;
                case "D�calage":
                    element[1] = "" + dec;
                    break;
                case "Perc�":
                    element[1] = "" + per;
                    break;
                case "P�n�tration":
                    element[1] = "" + pen;
                    break;
            }
        }
        control.setPossibleCheckResults(countCheckedPoints);
        return countCheckedPoints;
    }

    public String getLastComposantsControlResult(String filePath) {
        String fileContent;
        ReadManager reader = new ReadManager();
        fileContent = reader.readLastEntry(filePath);
        return fileContent;
    }

    public String[] loadLastComposantsControlResult() {
        String[] ret = control.getCheckPoints();
        String fileName = control.getfileName() + fileEnding;
        String filePath = generalPath + fileName;
        String lastEntry = getLastComposantsControlResult(filePath);
        if (lastEntry != null) {
            String[] lastResult = lastEntry.split(" ");
            int y = 0;
            for (int i = controlGeneralInfoCount; i < lastResult.length; i++) {
                ret[y] = lastResult[i];
                y++;
            }
        }
        return ret;
    }

    public void createControl() {
        String filePath = control.getCheckListPath() + control.getfileName() + control.getfileEnding();
        control.setFilePath(filePath);
        if (!controlExists(filePath)) {
            writeManager.createControlFile(filePath);
            control.getStauts().setCreated();
        } else {
            loadLastComposantsControlResult();
        }
    }

    // TODO check if all points are handled
    public void saveControl() {
        writeManager.write(control.getFilePath(), control.controlToString());
        control.getStauts().setStarted();
    }

    private boolean controlExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    // TODO set correct status
    public boolean validate() {
        boolean valid = true;
        int failureCount = 0;
        for (String result : control.getCheckPoints()) {
            if (!result.equals("OK")) {
                failureCount++;
            }
        }
        if (failureCount > 0) {
            setFailureCount(failureCount);
            valid = false;
        }

        System.out.println("fail: "+ failureCount);

        return valid;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public void dropControl() {
        controlProcessor = null;
    }

}
