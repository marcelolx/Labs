package io.github.marcelolx.futurethreads;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFuture4 {

	public static void main(String[] args) throws InterruptedException {
	  final CompletableFuture<String> c1 = new CompletableFuture<>();
      new Thread(() -> {
        try {
          if(new Random().nextInt(2) == 0) {
            throw new InterruptedException("Excecao");
          } else {
            c1.complete("OK");
          }
        } catch (Exception ex) {
          c1.completeExceptionally(ex);
        }
      }).start();
 
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
