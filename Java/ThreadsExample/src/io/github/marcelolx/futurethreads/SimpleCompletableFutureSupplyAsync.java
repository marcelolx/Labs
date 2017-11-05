package io.github.marcelolx.futurethreads;

import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFutureSupplyAsync {

	public static void main(String[] args) throws InterruptedException {
		final CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> "OK");
        
		c1.handle((content, ex) -> {
            if (ex == null) {
                System.out.println("Conteudo = " + content);
            } else {
                ex.printStackTrace();
            }
            return "";
        });
        Thread.sleep(1000);

	}

}
