package bolts;

public enum NavigationResult
{
    APP("app", true), 
    FAILED("failed", false), 
    WEB("web", true);
    
    private String code;
    private boolean succeeded;
    
    private NavigationResult(final String code, final boolean succeeded) {
        this.code = code;
        this.succeeded = succeeded;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public boolean isSucceeded() {
        return this.succeeded;
    }
}
