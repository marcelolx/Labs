package br.com.urionlinejudge;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n1 = scan.nextInt();
		int n2 = scan.nextInt();
		int n3 = scan.nextInt();
		
		int paraAndar2 = n1 * 2 + n3 * 2;
		int paraAndar1 = n2 * 2 + n3 * 4;
		int paraAndar3 = n1 * 4 + n2 * 2;
		
		if ((paraAndar1 < paraAndar2) && (paraAndar1 < paraAndar3)) {
			System.out.println(paraAndar1);
		}else if ((paraAndar2 < paraAndar1) && (paraAndar2 < paraAndar3)) {
		    System.out.println(paraAndar2);
		}else if ((paraAndar3 < paraAndar1) && (paraAndar3 < paraAndar2)) {
			System.out.println(paraAndar3);
		}else {
			System.out.println(paraAndar2);
		}
		
		/*if (((n1 > n2) && (n1 > n3)) || 
			((n3 > n1) && (n3 == n2) && ((n2 * 2) < n1))) {
			System.out.println((n2 * 2) + (n3 * 4));
		}else if (((n2 > n1)  && (n2 > n3)) || 
				  ((n3 > n1) && (n1 == n2) && ((n1 * 2) > n3)) ||
				  ((n2 > n1) && (n1 == n3) && ((n1 * 2) > n3)) ||
				  ((n3 < n1) && (n3 == n2) && ((n2 * 2) > n1))) {
			
			System.out.println((n1 * 2) + (n3 * 2));
		}else if (((n3 > n1) && (n3 > n2)) || 
				 ((n3 > n1) && (n1 == n2) && ((n1 * 2) < n3))) {
			
			System.out.println((n1 * 4) + (n2 * 2));
		} */
		
	}

}
