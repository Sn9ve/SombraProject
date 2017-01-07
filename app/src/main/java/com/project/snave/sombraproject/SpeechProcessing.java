package com.project.snave.sombraproject;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Snave on 28/12/2016.
 */

public class SpeechProcessing {
    private static String stop = "(stop|éteindre|attend)";
    private static String analyze = "(observe|regarde|analyse)";
    private static String front = "(devant|avance)";
    private static String back = "(recule|.*arrière)";
    private static String right = "(droite|.*tribord)";
    private static String left = "(gauche|.*bâbord)";
    public static String resultOrder = "";

    private static Pattern stopPattern;
    private static Pattern analyzePattern;
    private static Pattern frontPattern;
    private static Pattern backPattern;
    private static Pattern rightPattern;
    private static Pattern leftPattern;

    private static Matcher m;

    public static void processing(String resultToMatch){
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

    public static void initiatePattern(){
        stopPattern = Pattern.compile(stop);
        analyzePattern = Pattern.compile(analyze);
        frontPattern = Pattern.compile(front);
        backPattern = Pattern.compile(back);
        rightPattern = Pattern.compile(right);
        leftPattern = Pattern.compile(left);
    }
}
