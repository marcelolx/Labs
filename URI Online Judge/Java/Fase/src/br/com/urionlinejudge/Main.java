package br.com.urionlinejudge;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int competidores = scan.nextInt();
		int minimoClassificados = scan.nextInt();
		int[] pontuacao = new int[competidores];
		
		
		for (int i = 0; i < competidores; i++) {
			pontuacao[i] = scan.nextInt();
		}
		
		Arrays.sort(pontuacao);
		int quantidadeClassificados = 0;
		int ultimaPontuacao = 0;
		int quantidadeRegistrosPercorrer = competidores - minimoClassificados;
		
		for (int i = competidores; i > quantidadeRegistrosPercorrer; i--) {
			quantidadeClassificados += 1;
			ultimaPontuacao = pontuacao[i-1];
			
			if ((i-1) == quantidadeRegistrosPercorrer) {
				for (int j = (i - 1); j > 0; j--) {
					if (pontuacao[j - 1] == ultimaPontuacao) {
						quantidadeClassificados += 1;
					}
				}
			}
		}
		
		System.out.println(quantidadeClassificados);

	}

}
