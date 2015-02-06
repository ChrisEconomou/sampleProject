package  com.chriseconomou.sampleproject.error;

/**
 * Error model, that holds type and message.
 */
public class Error {



    private String mErrorDescription;

    public Error( String errorDescription) {

        mErrorDescription = errorDescription;
    }



    public String getErrorDescription() {
        return mErrorDescription;
    }
}
