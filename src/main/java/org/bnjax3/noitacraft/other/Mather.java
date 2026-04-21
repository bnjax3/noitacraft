package org.bnjax3.noitacraft.other;

public class Mather {
    // vanilla not having this is pissing me off
    // i am the original                                helper class used twice
    // MATE.......... TOMATE
    public static final double PI = Math.PI;
    public static final double DegToRad = Math.PI / 180;
    public static final double RadToDeg = 180 / Math.PI;
    public static final float DegToRadF = (float) (Math.PI / 180);
    public static final float RadToDegF = (float) (180 / Math.PI);

    // bc the dumbass java math function decided not to work anymore
    // wtf same issue as before
    // oh it wasnt the function, im justa dumbass
    public static double atan2(double o, double a, boolean returnDegrees){
        if (a > 0) { return convAtan2(Math.atan(o / a), returnDegrees); }
        if (a < 0) {
            return o >= 0? convAtan2(Math.atan(o / a) + PI, returnDegrees) : convAtan2(Math.atan(o / a) - PI, returnDegrees);
        }
        if (o > 0) { return convAtan2(PI/2, returnDegrees); }
        if (o < 0) { return convAtan2(-PI/2, returnDegrees); }

        return 0;
    }
    public static double atan2(double o, double a){
        return atan2(o, a, false);
    }


    private static double convAtan2(double n, boolean returnDegrees){
        return returnDegrees ? n * RadToDeg : n;
    }
}
