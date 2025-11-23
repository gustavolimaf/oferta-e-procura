import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe Buffer - Representa o recurso compartilhado entre produtores e consumidores.
 * * 1. REGIÃO CRÍTICA (Aula 6): Os métodos produzir() e consumir() manipulam o array 'buffer'.
 * O acesso a este array é a região crítica que precisa ser protegida.
 * * 2. SEMÁFOROS (Aula 6): 
 * - 'espacosVazios': Inicia com 7. Controla se o produtor pode produzir.
 * - 'itensCheios': Inicia com 0. Controla se o consumidor pode consumir.
 * Utilizamos acquire() (down) e release() (up).
 * * 3. MUTEX / EXCLUSÃO MÚTUA (Aula 6): 
 * - O 'ReentrantLock' atua como um Mutex. Ele garante que apenas UMA thread
 * (seja produtor ou consumidor) manipule os ponteiros do array e escreva no log
 * ao mesmo tempo. Isso evita condição de corrida e logs misturados.
 */
public class Buffer {
    private static final int CAPACIDADE = 7; // Requisito: Buffer de 7 posições
    
    // Implementação clássica de Buffer Circular (comum em Gerência de Memória)
    private int[] buffer = new int[CAPACIDADE];
    private int contadorItens = 0; 
    private int ponteiroProducao = 0; 
    private int ponteiroConsumo = 0;   
    
    // Semáforos de controle de fluxo
    private Semaphore espacosVazios = new Semaphore(CAPACIDADE);
    private Semaphore itensCheios = new Semaphore(0);
    
    // Lock para Exclusão Mútua (Mutex)
    private ReentrantLock mutex = new ReentrantLock();
    
    // Ferramentas de Log
    private PrintWriter logWriter;
    private int idItemGlobal = 0; // Apenas para numerar os itens produzidos sequencialmente
    
    public Buffer() {
        try {
            // false = sobrescreve o arquivo a cada execução
            logWriter = new PrintWriter(new FileWriter("output/log.txt", false));
            logWriter.println("=== LOG DE EXECUÇÃO: OFERTA E PROCURA ===");
            logWriter.println("Integrantes: Gustavo Fernandes Lima | Sabrina Teixeira Vianna\n");
            logWriter.flush();
        } catch (IOException e) {
            System.err.println("Erro ao criar log: " + e.getMessage());
        }
    }
    
    // Método Produzir
    public void produzir(String nomeProdutor, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            try {
                // DOWN(Vazios): Se não tiver espaço, a thread dorme aqui.
                espacosVazios.acquire();
                
                // ENTRADA NA REGIÃO CRÍTICA (Lock)
                mutex.lock();
                try {
                    // Lógica do Buffer Circular
                    idItemGlobal++;
                    buffer[ponteiroProducao] = idItemGlobal;
                    ponteiroProducao = (ponteiroProducao + 1) % CAPACIDADE;
                    contadorItens++;
                    
                    int espacosDisponiveis = CAPACIDADE - contadorItens;
                    
                    // Escrita no Log (Requisito do Texto)
                    String msg = String.format("Produtor - Inserido um item no buffer – espaços disponíveis: %d", 
                                             espacosDisponiveis);
                    registrarLogArquivo(msg);
                    
                } finally {
                    // SAÍDA DA REGIÃO CRÍTICA (Unlock)
                    mutex.unlock();
                }
                
                // UP(Cheios): Avisa que tem item novo disponível.
                itensCheios.release();
                
                // Pequena pausa para variar a concorrência
                Thread.sleep((long)(Math.random() * 100));
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // Método Consumir
    public void consumir(String nomeConsumidor, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            try {
                // DOWN(Cheios): Se buffer vazio, a thread dorme aqui.
                itensCheios.acquire();
                
                // ENTRADA NA REGIÃO CRÍTICA (Lock)
                mutex.lock();
                try {
                    // Lógica do Buffer Circular
                    // (O item não é "apagado", apenas movemos o ponteiro, simulando consumo de memória)
                    ponteiroConsumo = (ponteiroConsumo + 1) % CAPACIDADE;
                    contadorItens--;
                    
                    int espacosDisponiveis = CAPACIDADE - contadorItens;
                    
                    // Escrita no Log (Requisito do Texto)
                    String msg = String.format("Consumidor - Consumido um item no buffer – espaços disponíveis: %d", 
                                             espacosDisponiveis);
                    registrarLogArquivo(msg);
                    
                } finally {
                    // SAÍDA DA REGIÃO CRÍTICA (Unlock)
                    mutex.unlock();
                }
                
                // UP(Vazios): Avisa que liberou uma vaga.
                espacosVazios.release();
                
                Thread.sleep((long)(Math.random() * 150));
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // Método auxiliar para logar apenas no arquivo
    private void registrarLogArquivo(String mensagem) {
        if (logWriter != null) {
            logWriter.println(mensagem);
            logWriter.flush();
        }
    }
    
    public void fecharLog() {
        if (logWriter != null) logWriter.close();
    }
}