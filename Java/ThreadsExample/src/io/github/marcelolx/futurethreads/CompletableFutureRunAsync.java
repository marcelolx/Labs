package io.github.marcelolx.futurethreads;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureRunAsync {

	public static String getValor() throws InterruptedException {
        if (new Random().nextInt(2) == 0) {
            throw new InterruptedException("Erro");
        } else {
            return "OK";
        }
    }
	
	public static void main(String[] args) throws InterruptedException {
		final CompletableFuture<String> c1 = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
              c1.complete(getValor());
            } catch(InterruptedException ex) {
              c1.completeExceptionally(ex);
            }
        });
        c1.exceptionally(ex -> {
            System.out.println("Erro = " + ex.getMessage());
            return "Erro";
        }).thenAccept(str -> System.out.println("Ok = " + str));
        Thread.sleep(1000);
	}

}
