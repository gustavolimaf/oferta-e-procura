# Trabalho 2 - Sistema Operacional
## Problema Produtor-Consumidor

### Integrantes
- Gustavo Fernandes Lima
- Sabrina Teixeira Vianna

### Descrição
Implementação do problema clássico produtor-consumidor utilizando:
- **Semaphore** (espacosVazios e itensCheios)
- **ReentrantLock** (mutex para exclusão mútua)
- Buffer com 7 posições
- Produtores: produzem 15 itens cada
- Consumidores: consomem 12 itens cada

### Conceitos Aplicados (Relação com as Aulas)

#### Aula 5 - Processos e Threads
- Uso de threads (subprocessos) para execução concorrente
- Compartilhamento de espaço de endereçamento entre threads
- Estados de threads (Pronto, Executando, Bloqueado)

#### Aula 6 - Comunicação entre Processos
- **Semáforos**: Controle de acesso ao buffer (down/up)
- **Mutex**: Exclusão mútua na região crítica
- **Região Crítica**: Métodos produzir() e consumir()
- **Sincronização**: Evita condição de corrida
- Problema do buffer compartilhado

#### Aula 7 - Escalonamento
- Threads competindo por CPU
- Processos I/O-bound (consumidores esperando dados)

### Estrutura do Projeto
```
ProjetoSO/
├── src/
│   ├── Main.java          # Classe principal
│   ├── Buffer.java        # Buffer compartilhado (região crítica)
│   ├── Produtor.java      # Thread produtora
│   └── Consumidor.java    # Thread consumidora
├── output/
│   └── log.txt           # Log de execução (gerado)
└── README.md             # Este arquivo
```

### Formato do Log
```
Produtor-1 - Inserido item 1 no buffer – espaços disponíveis: 6
Consumidor-1 - Consumido item 1 do buffer – espaços disponíveis: 7
```