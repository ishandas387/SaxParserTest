package com.sax.test;

public class QuickTest {

	public static void main(String[] args) {
		int n=5;
        for(int i=1;i<=n;i++)
        {
             for(int k=n;k>i;k--)
             {
                 System.out.printf(" ");
             }    
             for(int j=1;j<=i;j++)
             {
                 System.out.printf("#");
             }
            System.out.println();
        }
 
	}

}
