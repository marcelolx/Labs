package io.github.marcelolx.futurethreads;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureCompose {

	public static int getValor() {
        Integer i = new Random().nextInt(1000);
        System.out.println("valor original = " + i);
        return i;
    }
	
	public static void main(String[] args) throws InterruptedException {
		final CompletableFuture<Integer> c1 =  CompletableFuture
                .supplyAsync(()->getValor())
                .thenCompose(i -> CompletableFuture.supplyAsync(()->i*2)); //somente é executado após a thread ser finalizada
        
        c1.exceptionally(ex -> {
            System.out.println("Erro = " + ex.getMessage());
            return -1;
        }).thenAccept(num -> System.out.println("Operacao = " + num));
        Thread.sleep(1000);
	}

}
