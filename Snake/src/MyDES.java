//Duong Tan Khoa Gael
//ID: 260691624

public class MyDES {
	
	public static void main(String[] args) {
							
		int key = Integer.parseInt(args[0]);
		String mess = args[1];
		
		String e = encrypt(key,mess);																		
		decrypt(key,e);	
		
		
		
		
	}
	
	public static String encrypt(int key, String mess)
	{
		if(key <= 9 || key >= 100)
		{
			System.err.println("Invalid key");
			return "";
		}
		if(mess.equals(""))
		{
			System.err.println("Empty message");
			return "";
		}
		int firstDigit = key/10; 
		int secondDigit = key%10;
		char[][] T = new char[firstDigit][1000];
		
		//T = { {h,l,o}, {e,l} } if firstdigit = 2
		//T = { {h,o}, {e }, {l }, {l } } if firstdigit = 4;
		//{h,l,o}
		//{e,l} 
		
		/*T = store String mess*/
		int j = 0;
		int k = 0;
		for(int i = 0; i < mess.length(); i++)
		{
			T[j][k] = mess.charAt(i);
			j++;
			if(j == firstDigit) {j=0; k++;}		
		}
			
		//test
		
		/*for(int i=0; i<T.length;i++)
		{
			System.out.println(Arrays.toString(T[i]));
		}*/
		//
	
		/* A = transpose of T */
		char[] A = new char[mess.length()];
		int n = 0;
		for(int i = 0; i < T.length; i++)
		{
			for(int m = 0; m < T[i].length; m++)
			{
				if(T[i][m] == Character.MIN_VALUE) {break;}
				A[n] = T[i][m];
				n++;
			}
		}
		
		/* B = Caeser cipher of A using key*/
		char[] B = new char[A.length];
		for(int i = 0; i < A.length; i++)
		{
			B[i] = (char) (A[i] + key);
		}
		
		
		/*T2 = store B*/
		char[][] T2 = new char[firstDigit+1][1000];
		int x = 0;
		int y = 0;
		for(int i = 0; i <B.length; i++)
		{
			T2[x][y] = B[i];
			x++;
			if(x == firstDigit+1) {x=0; y++;}
			
		}
		
		
		/* C = transpose of T2  */
		
		String C = "";
		for(int i = 0; i < T2.length; i++)
		{
			for(int m = 0; m < T2[i].length; m++)
			{
				if(T2[i][m] == Character.MIN_VALUE) {break;}
				C += T2[i][m];
				
			}
		}
		
		System.out.println("Encryption: " + C);			
		return C;
	}
	
	
	public static String decrypt(int key, String encrypted)
	{
		
		int firstDigit = key/10; 
		/*Transform the encrypted String back to B*/
		String B = inverseT(firstDigit+1,encrypted);
		/* B --> A; Caeser cipher*/
		String A = "";
		for(int i = 0; i < B.length(); i++)
		{
			A += (char) (B.charAt(i) - key);
		}
		/*Transform A back to original message m*/
		String m = inverseT(firstDigit,A);
		
		
		System.out.println("Decryption: " + m);
		return m;
	}
	
	//This method converts a transposed String back to the original String
	//Ex: if String m => populate m in T with key k => String A 
	//    then inverse(k,A) = m
	public static String inverseT(int k, String m)
	{
		String b ="";
		int diff = m.length()%k; //2
		int counter = 0;
		
		int d = (m.length()/k)+1;//2
		
		for(int j = 0; j < (m.length()/k)+1; j++)
		{
			for(int i = j; i < m.length(); i += d )
			{
				if(counter == diff) {d--;}
				if(b.length() >= m.length()) break;
				b += m.charAt(i);
				
				
				counter++;
			}
			counter = 0;
			d++;
			
		}
		
		return b;
	}

}
