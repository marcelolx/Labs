package io.github.marcelolx.futurethreads;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureEncadeada {
	
	public static int getValor() {
        return new Random().nextInt(1000);
    }
 
    public static void main(String... args) throws InterruptedException {
        CompletableFuture
            .supplyAsync(()->getValor())
            .thenApply(i -> String.valueOf(i))        
            .exceptionally(ex -> {
                System.out.println("Erro = " + ex.getMessage());
                return "Erro";
            }).thenAccept(str -> System.out.println("String = " + str));
        Thread.sleep(1000);
    }

}
