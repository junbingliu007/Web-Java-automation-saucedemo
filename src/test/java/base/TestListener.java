package base;

import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import utils.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("=== Starting test: " + result.getMethod().getMethodName() + " ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("=== Test passed: " + result.getMethod().getMethodName() + " ===");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("=== Test failed: " + result.getMethod().getMethodName() + " ===");
        if (result.getThrowable() != null) {
            Log.error("Failure reason: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn("=== Test skipped: " + result.getMethod().getMethodName() + " ===");
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}