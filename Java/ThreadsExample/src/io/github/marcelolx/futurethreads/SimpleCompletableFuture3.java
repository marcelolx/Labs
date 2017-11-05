package io.github.marcelolx.futurethreads;

import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFuture3 {

	public static void main(String[] args) throws InterruptedException {
		final CompletableFuture<String> c1 = new CompletableFuture<>();
        new Thread(() -> {
            try {
                 throw new InterruptedException("Excecao");
             } catch(Exception ex) {
                 c1.completeExceptionally(ex);
             }
         }).start();
         c1.exceptionally(ex -> {
             System.out.println("Erro = " + ex.getMessage());
             return "Erro";
         });
         c1.thenAccept(str -> System.out.println("Ok = " + str));
         Thread.sleep(1000);
	}

}
