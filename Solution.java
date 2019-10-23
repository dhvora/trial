import java.util.*;

public class Solution{
	static class Node implements Comparable<Node>{
		int val;
		char c;
		Node(int val, char c)
		{
			this.val = val;
			this.c = c;
		}
		public int compareTo(Node that)
		{
			return that.val - this.val;
		}
	}
	
	public static void main(String args[]) {
		ArrayList<Node> pQueue = new ArrayList<Node>(); 
		int aval=8;
		int bval = 2;
		int cval=2;
		pQueue.add(new Node(aval,'A'));
		pQueue.add(new Node(bval,'B'));
		pQueue.add(new Node(cval,'C'));
		Collections.sort(pQueue);
		int sum;
		String ans;
		Node top = pQueue.get(0);
		if(top.c=='A') {
			if((bval + cval) > aval/2) {
				ans = helperfunc(pQueue);
			}
			else {
				ans = dusrafunc(pQueue);
			}
		}
		else if(top.c=='B') {
			if((aval + cval) > bval/2) {
				ans = helperfunc(pQueue);
			}
			else {
				ans = dusrafunc(pQueue);
			}
		}
		else {
			if((bval + aval) > cval/2) {
				ans = helperfunc(pQueue);
			}
			else {
				ans = dusrafunc(pQueue);
			}
		}
		System.out.println(ans);
	}
	public static String helperfunc(ArrayList<Node> pQueue) {
		String ans="";
		char prev = '.';
		do {
			
			if(prev!=pQueue.get(0).c){
				Node a = pQueue.get(0);
				prev = a.c;
				
				if(a.val>=2) {
					ans+=a.c;
					ans+=a.c;
					a.val-=2;
				}
				else if(a.val==1){
					ans+=a.c;
					a.val-=1;
				}
				else pQueue.remove(0);
			}
			else{
				//prev equal hai
				if(pQueue.size()==1) {
					break;
				}
				else{
					Node a = pQueue.get(1);
					prev = a.c;
					
					if(a.val>=2) {
						
						ans+=a.c;
						ans+=a.c;
						a.val-=2;
					}
					else if(a.val==1){
						ans+=a.c;
						a.val-=1;
					}
					else pQueue.remove(1);
				}
				
			}

		}while(!pQueue.isEmpty());
		return ans;
	}

	public static String dusrafunc(ArrayList<Node> pQueue) {
		String ans="";
		char prev='.';
		do {
				if(pQueue.size()==1) 
					break;
				Node a = pQueue.get(0);
				if(a.val>=2) {
					ans+=a.c;
					ans+=a.c;
					a.val-=2;
					if(pQueue.get(1).val == 0)
						pQueue.remove(1);
					Node b = pQueue.get(1);
					ans+= b.c;
					b.val-=1;
				}
				else if(a.val==1){
					ans+=a.c;
					a.val-=1;
				}
				else pQueue.remove(0);

		}while(!pQueue.isEmpty());
		return ans;
	}
}