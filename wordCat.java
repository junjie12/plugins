package zhong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class wordCat {
    static List<List<String>>res = new ArrayList<>();
    static LinkedList<String>queue = new LinkedList<>();
    static int count = 0;
    public static void main(String[]args) {
    	String []strings = {"a","b","c"};
    	String beginWord = "a";
    	String endWord = "c";
    	List<String>wordList = Arrays.asList(strings);
    	System.out.println(ladderLength(beginWord, endWord, wordList));
    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int [] path = new int[wordList.size()];
        Arrays.fill(path,-1);
        boolean[] visited = new boolean[wordList.size()];
        HashMap<String,Integer>hashmap  = new HashMap<>();
        for(int i = 0;i < wordList.size();++i){
            hashmap.put(wordList.get(i),i);
        }
        hashmap.put(beginWord, 0xfffffff);
        bfs(beginWord,endWord,hashmap,wordList,path,visited);
        return count;
    }
    public static void bfs(String beginWord,String endWord,HashMap<String,Integer>hashmap,List<String>wordList,int[]path,boolean []visited){
        queue.add(beginWord);
        while(!queue.isEmpty()){
        String nowWord = queue.poll();
        for(int i = 0;i < wordList.size();++i){
            if(iftransfer(nowWord,wordList.get(i))&&!visited[i]){
                queue.add(wordList.get(i));
                visited[i] = true;
                path[i] = hashmap.get(nowWord);
                if(endWord.equals(wordList.get(i))) {
                	int j = i;
                	count+=2;
                    while(path[j]!=0xfffffff) {
                    	j = path[j];
                    	++count;
                    }
                    return;
                }
            }
        }
        }
    }
    public static boolean iftransfer(String str1,String str2){
        int count  = 0;
        for(int i = 0;i < str1.length();++i){
            if(str1.charAt(i)!=str2.charAt(i)){
                ++count;
            }
        }
        if(count == 1) return true;
        else return false;
    }
}