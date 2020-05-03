package com.jiker.keju.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class CalculateFee {
    public Map getWaiteTimeAndDistanceFee(String stringLine) {
        Map<String,Integer> map = new HashMap<>();
        String[] lineInput = stringLine.split(",");
        Pattern p = compile("[^\\d]");
        int distance = Integer.parseInt(p.matcher(lineInput[0]).replaceAll(""));
        int  waiteTime = Integer.parseInt(p.matcher(lineInput[1]).replaceAll(""));
        map.put("distance",distance);
        map.put("waiteTime",waiteTime);
        return map;
    }

    public int getOneReceipt(Map<String,Integer> map) {
        if ( map.get("distance")<= 2) {
            return (int) Math.round(6 + 0.25 * map.get("waiteTime"));
        } else if (map.get("distance") <= 8) {
            return (int) Math.round(4.4 + 0.8 * map.get("distance") + 0.25 * map.get("waiteTime"));
        } else {
            return (int) Math.round(1.2 + 1.2 * map.get("distance") + 0.25 * map.get("waiteTime"));
        }
    }

    public String getRecepit(List<String> stringLines) {
        String receipt = "";
        for (String strLine : stringLines) {
            Map map = getWaiteTimeAndDistanceFee(strLine);
            int fee = getOneReceipt(map);
            receipt = receipt.concat("收费"+fee+"元\n") ;
        }
        return receipt;
    }
}
