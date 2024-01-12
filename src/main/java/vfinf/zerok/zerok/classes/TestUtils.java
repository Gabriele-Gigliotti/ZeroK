package vfinf.zerok.zerok.classes;

import java.util.Arrays;

public class TestUtils {
    public static void printR(String txt){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String trace = "Unknown";

        if (stackTrace.length >= 3) {
            // Index 0 is getStackTrace, index 1 is this method, index 2 is the caller
            StackTraceElement callingElement = stackTrace[2];
            String fullClassName = callingElement.getClassName() + "." + callingElement.getMethodName()
                    + "(" + callingElement.getFileName() + ":" + callingElement.getLineNumber() + ")";

            // Extract only the class name without the package
            trace = fullClassName;
        }

        System.out.println("["+trace+ "]: " + txt);
    }
    public static void printR(String txt, String customName){
        System.out.println("["+customName+"]: " + txt);
    }
}
