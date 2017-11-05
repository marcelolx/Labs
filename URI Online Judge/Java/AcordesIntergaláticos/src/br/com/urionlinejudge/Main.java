package br.com.urionlinejudge;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int[] notas = {0,1,2,3,4,5,6,7,8};
			
		Scanner scan = new Scanner(System.in);
		int n1 = 0, n2 = 0;
		while ((n1 <= 1) && (n2 < 1)) {
			System.out.println("Insira um número maior que 2: ");
			n1 = scan.nextInt();
			System.out.println("Insira um número maior que 1: ");
			n2 = scan.nextInt();			
		}
		
		
		
		
		

	}

}
