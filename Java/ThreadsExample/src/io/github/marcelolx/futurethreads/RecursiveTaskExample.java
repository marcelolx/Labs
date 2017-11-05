package io.github.marcelolx.futurethreads;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
 
public class RecursiveTaskExample extends RecursiveTask<List<String>> {
 
    /**
	 */
	
	private static final long serialVersionUID = 1L;
	private final String diretorio;
    private final String extensao;
 
    public RecursiveTaskExample(String diretorio, String extension) {
        this.diretorio = diretorio;
        this.extensao = extension;
    }
 
    @Override
    protected List<String> compute() {
        List<String> lista = new ArrayList<>();
        List<RecursiveTaskExample> tarefas = new ArrayList<>();
        File arquivo = new File(diretorio);
        File conteudo[] = arquivo.listFiles();
 
        if (conteudo != null) {
            for (int i = 0; i < conteudo.length; i++) {
                if (conteudo[i].isDirectory()) {
                    RecursiveTaskExample tarefa = new RecursiveTaskExample(conteudo[i].getAbsolutePath(), extensao);
                    tarefa.fork();
                    tarefas.add(tarefa);
                } else if (verificaArquivo(conteudo[i].getName())) {
                    lista.add(conteudo[i].getAbsolutePath());
                }
            }
        }
        if (tarefas.size() > 50) {
            System.out.printf("%s: %d tarefas executando.\n", arquivo.getAbsolutePath(), tarefas.size());
        }
        addResultadosDaTarefa(lista, tarefas);
        return lista;
    }
 
    private void addResultadosDaTarefa(List<String> lista, List<RecursiveTaskExample> tarefas) {
        for (RecursiveTaskExample item : tarefas) {
            lista.addAll(item.join());
        }
    }
 
    private boolean verificaArquivo(String nome) {
        return nome.endsWith(extensao);
    }
}