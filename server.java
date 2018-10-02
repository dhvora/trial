import java.io.*;  
import java.net.*;  
public class server 
{  
	public static void main(String[] args)
	{  
		try
		{  
			int ct;
			ServerSocket ss=new ServerSocket(6666);  
			Socket s=ss.accept();//establishes connection   
			DataInputStream dis=new DataInputStream(s.getInputStream());  
			



			String c=(String)dis.readUTF();  
			ct=Integer.parseInt(c);
			System.out.println("CT= "+ct);  
			ss.close();  
		}
		catch(Exception e)
			{System.out.println(e);}  
	}  
}  





/*
import java.util.*;
 
class rsa
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int p,q,n,z,d=0,e,i;
		System.out.print("Enter the plain text number: ");
		int msg=sc.nextInt();
		double ct,pt;
		System.out.print("Enter 1st prime number p: ");
		p=sc.nextInt();
		System.out.print("Enter 2nd prime number q: ");
		q=sc.nextInt();
		 
		n=p*q;
		z=(p-1)*(q-1);
		 
		for(e=2;e<z;e++)
		{
			if(gcd(e,z)==1)
		 	{ 
				break;
			}
		}
		System.out.println("The value of E = "+e); 
		for(i=0;i<=9;i++)
		{
			int x=1+(i*z);
			if(x%e==0)     
			{
				d=x/e;
				break;
			}
		}
		System.out.println("The value of D = "+d); 
		
		ct=(Math.pow(msg,e))%n;
		System.out.println("Cipher Text is : "+ct);
		
		pt=(Math.pow(ct,d))%n;
		System.out.println("Derypted message is : "+pt);
	}
	 
	static int gcd(int e, int z)
	{
		if(e==0)
			return z; 
		else
			return gcd(z%e,e);
 	}
}*/