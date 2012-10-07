package com.psyco.tplmc.CustomMessages;

/**
 * Created with IntelliJ IDEA.
 * User: Patrick
 * Date: 10/6/12
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    public static String getSpacedString(String[] args, int start, int end){
        if(args == null || start > end || (args.length + 1) <= end)
            throw new IllegalArgumentException();
        if(start == end)
            return args[start];
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < end; i++){
            sb.append(args[i]).append(" ");
        }
        return sb.toString().trim();
    }


}
