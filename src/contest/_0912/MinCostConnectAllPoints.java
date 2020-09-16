package contest._0912;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 9/12/20 23:20
 *
 * 1584. Min Cost to Connect All Points
 */
public class MinCostConnectAllPoints {

    public static int minCostConnectPoints(int[][] points) {
        if (points == null || points.length <= 1) return 0;
        return new PrimMSTHelper(points).res;
    }

    /**
     * In the Prim's algorithm, we are building a tree starting from some initial point.
     * For the current point, we add its edges to the min heap.
     * Then, we pick a smallest edge that connects to a point that is not visited.
     */
    private static class PrimMSTHelper {
        int[][] points;
        int expectedCnt;    // count of edges, should be cnt(V)-1;
        int actualCnt;

        Set<Integer> visited = new HashSet<>();

        PriorityQueue<Info> pq;

        private int res;

        PrimMSTHelper(int[][] points) {
            this.points = points;
            this.expectedCnt = points.length-1;
            this.pq = new PriorityQueue<>(Comparator.comparing(p -> p.cost));

            updatePQ(0);
            pollAndUpdatePQ();
        }

        /**
         * connect i to other unvisited vertices and put them to PQ
         * @param polledIdx popped Info.idx from PQ
         */
        private void updatePQ(int polledIdx) {
            visited.add(polledIdx);
            int[] p1 = points[polledIdx];

            for (int i = 0; i < points.length; i++) {
                if (visited.contains(i)) continue;
                int[] p2 = points[i];
                pq.offer(new Info(i, Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1])));
            }
        }

        private void pollAndUpdatePQ() {
            while (!pq.isEmpty() && actualCnt < expectedCnt) {
                Info min = pq.poll();
                if (visited.contains(min.idx))
                    continue;

                actualCnt++;
                res += min.cost;
                updatePQ(min.idx);
            }
        }
    }

    private static class Info {
        int idx;
        int cost;

        private Info(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    @Test
    public void case1() {
        assertEquals(20, minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
    }
    
    @Test
    public void case2() {
        assertEquals(113, minCostConnectPoints(new int[][]
                {{11,-6},{9,-19},{16,-13},{4,-9},{20,4},{20,7},{-9,18},{10,-15},{-15,3},{6,6}}
        ));
    }

    @Test
    public void case3() {
        assertEquals(18, minCostConnectPoints(new int[][]
                {{3, 12}, {-2, 5}, {-4, 1}}
        ));
    }

    @Test
    public void case4() {
        assertEquals(53, minCostConnectPoints(new int[][]
                {{2, -3}, {-17, -8}, {13, 8}, {-17, -15}}
        ));
    }
    
    @Test
    public void case5() {
        assertEquals(22679, minCostConnectPoints(new int[][]
                {{75,790},{767,519},{405,210},{-526,-175},{-126,-824},{382,862},{-832,630},{-23,-463},
                        {62,-480},{371,724},{280,-645},{144,-115},{-212,-990},{-856,-393},{43,-429},
                        {959,880},{267,-876},{-212,500},{-699,-240},{349,-745},{-558,92},{-52,-619},
                        {269,282},{-403,-921},{-848,-406},{-737,-453},{335,-521},{-914,953},{-612,-268},
                        {-133,238},{304,-477},{-312,-565},{-643,-114},{908,923},{946,-296},{-1000,-464},
                        {770,405},{-491,543},{439,199},{31,182},{-362,790},{956,935},{-913,-368},{-766,449},
                        {-103,96},{551,-792},{-136,456},{29,592},{462,-813},{859,-832},{-22,-987},{-762,686},
                        {-483,281},{-115,1000},{40,-978},{993,-85},{-516,662},{797,-968},{106,-578},{-517,1000},
                        {535,-696},{-140,-821},{-843,831},{-918,704},{-679,375},{475,105},{-293,1000},{-80,809},
                        {-600,783},{-210,-810},{260,-234},{-374,-488},{-640,820},{-717,23},{-729,-282},{534,-752},
                        {-775,-493},{796,-39},{-403,531},{665,543},{-51,71},{-321,576},{-513,-665},{-510,-38},
                        {733,576},{567,461},{46,-985},{199,-462},{-145,833},{888,908},{-148,445},{-542,-432},
                        {-277,-735},{-564,-428},{-88,790},{495,-227},{-794,83},{-691,-677},{-3,901},{999,-22},
                        {2,408},{-300,482},{-650,-54},{-425,-875},{-163,959},{-609,123},{491,765},{-126,253},
                        {-147,971},{-991,952},{-917,-928},{891,-717},{-232,-173},{231,-335},{869,-890},{369,-108},
                        {708,874},{-452,785},{921,-333},{625,-257},{-490,666},{-271,974},{599,-691},{634,-731},
                        {630,901},{426,-293},{229,-753},{-668,-371},{311,-928},{569,-154},{-766,-232},{847,-457},
                        {48,56},{583,201},{-799,-744},{-119,-967},{-261,-970},{-100,-379},{91,-909},{7,631},
                        {-163,-962},{-979,144},{-590,779},{30,-453},{-300,-488},{-360,-960},{-498,-465},{-753,436},
                        {-193,-494},{-480,-730},{130,-77},{966,694},{121,-420},{359,736},{-717,683},{-786,-619},
                        {357,-58},{163,172},{960,-153},{-89,-536},{-374,-4},{342,-493},{-631,350},{859,682},
                        {777,954},{-124,948},{636,-79},{-587,609},{427,-816},{-856,-672},{828,152},{828,192},
                        {-791,737},{155,-723},{-940,632},{-460,22},{519,-687},{-848,48},{535,-168},{442,304},
                        {-267,436},{-309,-280},{160,-63},{440,229},{732,42},{592,531},{347,-494},{919,-663},
                        {474,941},{88,-405},{-403,-529},{988,342}}
                ));
    }

}
