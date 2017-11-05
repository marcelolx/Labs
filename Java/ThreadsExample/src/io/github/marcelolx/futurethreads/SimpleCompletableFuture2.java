package io.github.marcelolx.futurethreads;

import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFuture2 {

	public static void main(String[] args) throws InterruptedException{
		final CompletableFuture<String> c1 = new CompletableFuture<>();
        new Thread(() -> c1.complete("Ola Mundo")).start();
        c1.thenAccept(str -> System.out.println(str));
        Thread.sleep(1000);
	}
	
}
