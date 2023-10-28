package com.java.yaala.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import com.java.yaala.bean.Card;

public class JudgementHandUtil {

    public static boolean judgementTwoPair(Card[] cards) {
        int count = 0;
        boolean result = false;
        List<Card> pairCard = new ArrayList<Card>();
        for(Card card1 : cards) {
            for(Card card2 : cards) {
                if(!card1.equals(card2) && !pairCard.contains(card1) && !pairCard.contains(card2)) {
                    if(card1.getNumber() == card2.getNumber()) {
                        count++;
                        pairCard.add(card1);
                        pairCard.add(card2);
                    }
                }
            }
        }
        if(count == 2) {
            result = true;
        }
        return result;
    }
    
    public static boolean judgementThreeCard(Card[] cards) {
        boolean result = false;
        for(Card card1 : cards) {
            int count = 0;
            for(Card card2 : cards) {
                if(!card1.equals(card2)) {
                    if(card1.getNumber() == card2.getNumber()) {
                        count++;
                    }
                }
            }
            if(count >= 2) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public static boolean judgementOnePair(Card[] cards) {
        return countSameLineNumber(cards) > 0;
    }
    
    public static boolean judgementFourCard(Card[] cards) {
        return countSameLineNumber(cards) > 5;
    }
    
    public static boolean judgementFullHouse(Card[] cards) {
        return countSameLineNumber(cards) == 4;
    }
    
    public static boolean judgementStraight(Card[] cards) {

        List<Integer> list = Arrays.stream(cards).map(card -> card.getNumber()).collect(Collectors.toList());
        return list.containsAll(List.of(1,2,3,4,5)) || list.containsAll(List.of(2,3,4,5,6));
    }
    
    public static boolean judgementFlash(Card[] cards) {
        Set<Integer> set = Arrays.stream(cards).map(card -> card.getMark()).collect(Collectors.toSet());
        return set.size() == 1;
    }
    
    public static boolean judgementStraightFlash(Card[] cards) {
        return judgementStraight(cards) && judgementFlash(cards);
    }
    
    public static boolean judgementSunshine(Card[] cards) {
        Map<Integer, List<Integer>> map = convertMarkGroups(cards);
        if(!map.keySet().containsAll(List.of(1,4))) {
            return false;
        }
        if(map.get(1).size()< 2 || map.get(4).size()< 2) {
            return false;
        }
        if(map.get(1).size() > map.get(4).size()) {
            return map.get(1).containsAll(map.get(4));
        } 
        return map.get(4).containsAll(map.get(1));
    }
    
    public static boolean judgementTwinSun(Card[] cards) {
        Map<Integer, List<Integer>> map = convertMarkGroups(cards);
        if (!map.containsKey(1)){
            return false;
        }
        return map.get(1).containsAll(List.of(2,4)) || map.get(1).containsAll(List.of(2,6)) || map.get(1).containsAll(List.of(4,6));
    }
    
    public static boolean judgementStarFlash(Card[] cards) {
        Map<Integer, List<Integer>> map = convertMarkGroups(cards);
        if (!map.containsKey(4)){
            return false;
        }
        return map.get(4).size() == 5;
    }
    
    public static boolean judgementDiaHouse(Card[] cards) {
         Map<Integer, List<Integer>> map = convertMarkGroups(cards);
        if (!map.containsKey(2)){
            return false;
        }
        return map.get(2).containsAll(1,2,3,4) || map.get(2).containsAll(2,3,4,5) || map.get(2).containsAll(3,4,5,6);
    }
    
    public static boolean judgementSpadeLeader(Card[] cards) {
        boolean result = false;
        return result;
    }
    
    public static boolean judgementSpadeDiaMix(Card[] cards) {
        boolean result = false;
        return result;
    }
    
    public static boolean judgementMarkWonder(Card[] cards) {
        boolean result = false;
        return result;
    }
    
    public static boolean judgementSunArch(Card[] cards) {
        boolean result = false;
        return result;
    }

    private static Map<Integer, List<Integer>> convertMarkGroups(Card[] cards) {
        return Arrays.stream(cards).collect(Collectors.groupingBy(Card::getMark, LinkedHashMap::new, Collectors.mapping(Card::getNumber, Collectors.toList())));
    }

    private static int countSameLineNumber(Card[] cards) {
        int count = 0;
        for (int i = 0; i < cards.length - 1; i++) {
            for (int m = i + 1; m < cards.length; m++) {
                if(cards[i].getNumber() == cards[m].getNumber()) {
                    count++;
                }
            }
        }
        return count;
    }
}
