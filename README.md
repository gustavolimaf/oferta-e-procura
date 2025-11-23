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
oferta_e_procura/
├── src/
│   ├── Main.java          # Classe principal
│   ├── Buffer.java        # Buffer compartilhado (região crítica)
│   ├── Produtor.java      # Thread produtora
│   └── Consumidor.java    # Thread consumidora
├── bin/                   # Arquivos compilados (.class)
├── output/
│   └── log.txt           # Log de execução (gerado)
├── .gitignore            # Arquivos ignorados pelo Git
└── README.md             # Este arquivo
```

### Como Compilar e Executar
```powershell
# Compilar
& "C:\Program Files\Java\jdk-21\bin\javac.exe" -d bin src\*.java

# Executar
& "C:\Program Files\Java\jdk-21\bin\java.exe" -cp bin Main

# Ou tudo de uma vez
& "C:\Program Files\Java\jdk-21\bin\javac.exe" -d bin src\*.java; & "C:\Program Files\Java\jdk-21\bin\java.exe" -cp bin Main
```

### Formato do Log
```
Produtor - Inserido um item no buffer – espaços disponíveis: 6
Consumidor - Consumido um item no buffer – espaços disponíveis: 7
```