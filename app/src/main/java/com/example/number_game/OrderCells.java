package com.example.number_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderCells {
    boolean isValid = false;
    int [] Order_Number_List;
    int index = 0;
    public  int [] Number_List (List<Integer> number_list) {
        Order_Number_List = new int[number_list.size()];
        for(int number: number_list) {
            Order_Number_List[index] = number;
            index++;
        }
        return Order_Number_List;
    }

    public int [] Number_List_Sorted(int [] number_list) {
        Arrays.sort(number_list);
        return  number_list;
    }

    int index_ForSetOrder = 0;
    public void setOrder_Number_List(int Number) {

            if (Number == Order_Number_List[index_ForSetOrder]) {
                isValid = true;
                index_ForSetOrder++;
            }
    }
}
