package com.project.snave.sombraproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Snave on 28/12/2016.
 */

public class SpeechProcessing {
    private static String stop = "(stop|turn off|pause|wait)";
    private static String analyze = "(watch|look|analyze)";
    private static String front = "(front|move|go ahead)";
    private static String back = "(back)";
    private static String right = "(right)";
    private static String left = "(left)";
    public static String resultOrder = "";

    private static Pattern stopPattern;
    private static Pattern analyzePattern;
    private static Pattern frontPattern;
    private static Pattern backPattern;
    private static Pattern rightPattern;
    private static Pattern leftPattern;

    private static Matcher m;

    private static void processing(String resultToMatch){
        m = stopPattern.matcher(resultToMatch);
        if(m.matches()){
            resultOrder = "stop";
            return;
        }
        m = analyzePattern.matcher(resultToMatch);
        if(m.matches()){
            resultOrder = "watch";
            return;
        }
        m = frontPattern.matcher(resultToMatch);
        if(m.matches()){
            resultOrder = "front";
            return;
        }
        m = backPattern.matcher(resultToMatch);
        if(m.matches()){
            resultOrder = "back";
            return;
        }
        m = rightPattern.matcher(resultToMatch);
        if(m.matches()){
            resultOrder = "right";
            return;
        }
        m = leftPattern.matcher(resultToMatch);
        if(m.matches()){
            resultOrder = "left";
            return;
        }
    }

    public static void processAction(String voice) {
        SpeechProcessing.processing(voice);
        switch (resultOrder){
            case "stop":
                //do something in this case, add the function to stop Sombra
                break;
            case "analyze":
                //add the function to start analyze
                break;
            case "front":
                //add the function to move in front
                break;
            case "back":
                //add the function to move backward
                break;
            case "right":
                //add the function to move in right
                break;
            case "left":
                //add the function to move in left
                break;

        }
    }

    public static void initiatePattern(){
        stopPattern = Pattern.compile(stop);
        analyzePattern = Pattern.compile(analyze);
        frontPattern = Pattern.compile(front);
        backPattern = Pattern.compile(back);
        rightPattern = Pattern.compile(right);
        leftPattern = Pattern.compile(left);
    }
}
