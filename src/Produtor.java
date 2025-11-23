/**
 * Classe Produtor
 * 1. THREADS (Aula 5): Estende a classe Thread. Representa um processo/tarefa independente.
 * 2. COMPARTILHAMENTO (Aula 6): Recebe a referência do objeto 'Buffer' no construtor,
 * permitindo comunicação por memória compartilhada.
 */
public class Produtor extends Thread {
    private Buffer buffer;
    private String nome;
    private static final int COTA_PRODUCAO = 15; // Requisito: Produzir até 15 itens
    
    public Produtor(Buffer buffer, String nome) {
        this.buffer = buffer;
        this.nome = nome;
    }
    
    @Override
    public void run() {
        System.out.println(">> " + nome + " iniciou.");
        buffer.produzir(nome, COTA_PRODUCAO);
        System.out.println(">> " + nome + " terminou.");
    }
}