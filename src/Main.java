import java.io.File;

/**
 * Classe Principal
 * 1. ESCALONAMENTO (Aula 7): Ao iniciarmos as threads (start), o agendador (scheduler)
 * da JVM/SO assume o controle para decidir qual thread ganha a CPU.
 */
public class Main {
    public static void main(String[] args) {
        // Cria diretório de log se não existir
        File dir = new File("output");
        if (!dir.exists()) dir.mkdir();

        // Recurso Compartilhado
        Buffer buffer = new Buffer();

        // Instanciação das Threads (Produtores e Consumidores)
        // Criamos 2 de cada para gerar maior concorrência
        Produtor p1 = new Produtor(buffer, "Produtor-A");
        Produtor p2 = new Produtor(buffer, "Produtor-B");
        
        Consumidor c1 = new Consumidor(buffer, "Consumidor-A");
        Consumidor c2 = new Consumidor(buffer, "Consumidor-B");

        // Início da execução (Fork)
        p1.start();
        p2.start();
        c1.start();
        c2.start();

        // Join (Espera as threads terminarem para fechar o programa corretamente)
        try {
            p1.join();
            p2.join();
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buffer.fecharLog();
        System.out.println("\n=== FIM DA SIMULAÇÃO ===");
        System.out.println("Verifique o arquivo: output/log.txt");
    }
}