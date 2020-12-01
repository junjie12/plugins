package zhong;

public class leecode_767 {
	public static void main(String[]args) {
		String S = "cxmwmmm";
		reorganizeString(S);
	}
	public static String reorganizeString(String S) {
        StringBuilder res = new StringBuilder();
        StringBuilder strb = new StringBuilder(S);
        int length = -1;
        while(length!=res.length()){
            length = res.length();
            for(int i = 0;i < strb.length();++i){
                int j = 0;
                while(j<res.length()){
                if(res.equals("")||res.charAt(j)!=strb.charAt(i)){
                    res.insert(j,strb.charAt(i));
                    strb.deleteCharAt(i);
                    break;
                }
                ++j;
                }
                if(length!=res.length()) break;
            }
        }
        if(res.length()==S.length()) return res.toString();
        else return "";
    }
}
