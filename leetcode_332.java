package zhong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class leetcode_332 {
	class Solution {
	    List<String>list = new ArrayList<>();
	    List<String>result = new ArrayList<>();
	    public List<String> findItinerary(List<List<String>> tickets) {
	        for(int i = 0;i < tickets.size();++i){
	            if(!list.contains(tickets.get(i).get(0))){
	                list.add(tickets.get(i).get(0));
	            }
	            if(!list.contains(tickets.get(i).get(1))){
	                list.add(tickets.get(i).get(1));
	            }
	        }
	        int[][]edge = new int[list.size()][list.size()];
	        int n = tickets.size();
	        Collections.sort(list);
	        int start = list.indexOf("JFK");
	        for(int i = 0;i < tickets.size();++i){
	            ++edge[list.indexOf(tickets.get(i).get(0))][list.indexOf(tickets.get(i).get(1))];
	        }
	        int count = 1;
	        backtrace(start,n,edge,count,list);
	        result.add("JFK");
	        Collections.reverse(result);
	        return result;
	    }
	    public boolean backtrace(int start,int n,int [][]edge,int count,List<String>list) {
	        if(count == n+1) return true;
	        for(int i = 0;i < list.size();++i){
	            if(count == n+1) break;
	            if(edge[start][i] > 0){
	                ++count;
	                --edge[start][i];
	                if(backtrace(i,n,edge,count,list)){
	                    result.add(list.get(i));
	                    return true;
	                }else{
	                    ++edge[start][i];
	                    --count;
	                }
	            }
	        }
	        return false;
	    }
	}
}
