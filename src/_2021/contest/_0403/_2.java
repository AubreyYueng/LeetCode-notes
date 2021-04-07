package _2021.contest._0403;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2021/4/3 11:57
 */
public class _2 {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        if (s1.length < s2.length)
            return areSentencesSimilar(sentence2, sentence1);

        int i = 0;
        while (i < s2.length && s1[i].equals(s2[i]))
            i++;
        System.out.println(i);

        int j = 1;
        while (s1.length - j >= 0 && s2.length - j >= 0 && s1[s1.length-j].equals(s2[s2.length - j]))
            j++;
        System.out.println(i + ",  " + j);

        return i+j-1 >= s2.length;
    }

    @Test
    public void case1() {
        assertTrue(areSentencesSimilar("My name is Haley", "My Haley"));
    }

}
