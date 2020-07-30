package math.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/29 21:28
 *
 * 1344. Angle Between Hands of a Clock
 */
public class AngleBetweenHandsOfAClock {

    public double angleClock(int hour, int minutes) {
        if (hour == 12) hour = 0;
        double mOfH = minutes / 60.0;

        double degreeM = mOfH * 360;
        double degreeH = hour/12.0*360 + mOfH*30.0;
        double res = Math.abs(degreeM-degreeH);
        return res >= 180 ? 360-res : res;
    }

    @Test
    public void case1() {
        assertEquals(165, angleClock(12, 30), 0.000001);
    }

    @Test
    public void case2() {
        assertEquals(75, angleClock(3, 30), 0.000001);
    }

    @Test
    public void case3() {
        assertEquals(7.5, angleClock(3, 15), 0.000001);
    }

    @Test
    public void case4() {
        assertEquals(155, angleClock(4, 50), 0.000001);
    }

    @Test
    public void case5() {
        assertEquals(0, angleClock(12, 0), 0.000001);
    }

    @Test
    public void case6() {
        assertEquals(76.5, angleClock(1, 57), 0.000001);
    }

}
