package model;

public class ControlStatus {

    private boolean created = false;
    private boolean started = false;
    private boolean retouched = false;
    private boolean validated = false;
    private boolean archived = false;
    private boolean undefined = true;
    private String status = "";

    public ControlStatus(int status) {
        setStatus(status);
    }

    //the status created takes the number 1
    //the status started takes the number 2
    //the status retrouched takes the number 3
    //the status validated takes the number 4
    //the status archived takes the number 5
    private void setStatus(int status) {
        switch (status) {
            case 1:
                created = true;
                undefined = false;
                this.status = "created";
                break;
            case 2:
                started = true;
                undefined = false;
                this.status = "started";
                break;
            case 3:
                retouched = true;
                undefined = false;
                this.status = "retouched";
                break;
            case 4:
                validated = true;
                undefined = false;
                this.status = "validated";
                break;
            case 5:
                archived = true;
                undefined = false;
                this.status = "archived";
                break;
            default:
                undefined = true;
                this.status = "undefined";
        }
    }

    private void unSetStatus() {
        created = false;
        started = false;
        retouched = false;
        validated = false;
        archived = false;
        undefined = true;
    }

    public String statusToString() {
        return status;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated() {
        unSetStatus();
        setStatus(1);
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted() {
        unSetStatus();
        setStatus(2);
    }

    public boolean isRetouched() {
        return retouched;
    }

    public void setRetouched() {
        unSetStatus();
        setStatus(3);
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated() {
        unSetStatus();
        setStatus(4);
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived() {
        unSetStatus();
        setStatus(5);
    }

    public boolean isUndefined() {
        return undefined;
    }

    public void setUndefined() {
        unSetStatus();
    }

}
