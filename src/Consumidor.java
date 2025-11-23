/**
 * Classe Consumidor
 * 1. ESTADOS DO PROCESSO (Aula 5): Durante a execução do método run(), esta thread
 * alternará entre os estados 'Executando' (processando), 'Bloqueado' (esperando semáforo)
 * e 'Pronto' (aguardando CPU).
 */
public class Consumidor extends Thread {
    private Buffer buffer;
    private String nome;
    private static final int COTA_CONSUMO = 12; // Requisito: Consumir até 12 itens
    
    public Consumidor(Buffer buffer, String nome) {
        this.buffer = buffer;
        this.nome = nome;
    }
    
    @Override
    public void run() {
        System.out.println(">> " + nome + " iniciou.");
        buffer.consumir(nome, COTA_CONSUMO);
        System.out.println(">> " + nome + " terminou.");
    }
}