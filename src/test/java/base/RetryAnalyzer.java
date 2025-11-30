package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.ConfigReader;
import utils.Log;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = ConfigReader.getRetryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            Log.info("Retrying test " + result.getMethod().getMethodName() +
                    " for the " + retryCount + " time");
            return true;
        }
        return false;
    }
}