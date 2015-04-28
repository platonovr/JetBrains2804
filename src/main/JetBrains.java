package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 28.04.2015.
 */
public class JetBrains {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
