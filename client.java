import java.util.*;  
import java.io.*;  
import java.net.*;  
public class client 
{  
	public static void main(String[] args) 
	{
		try
		{      
			Scanner sc=new Scanner(System.in);
			int p,q,n,z,d=0,e,i;
			double ct,pt;
			Socket s=new Socket("localhost",6666);  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			DataInputStream din=new DataInputStream(s.getInputStream());  
			
			System.out.print("Enter the plain text number: ");
			int msg=sc.nextInt();
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
			
			ct=(Math.pow(msg,e))%n;
			System.out.println("Cipher Text is : "+ct);
			dout.writeUTF(ct);  
			dout.flush();  
			dout.close();  
			s.close();  
		}
		catch(Exception e)
			{System.out.println(e);}  
	}
	static int gcd(int e, int z)
	{
		if(e==0)
			return z; 
		else
			return gcd(z%e,e);
 	}  
}  
