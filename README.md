# Sistema Impacta: Gestão de Ações Socioambientais

Sistema desenvolvido em Java para apoiar a logística de ações comunitárias em instituições de ensino, permitindo o cadastro de voluntários, o gerenciamento de ações socioambientais e o controle de inscrições, com acompanhamento do histórico de engajamento de cada aluno.

Projeto acadêmico da disciplina de **Programação Orientada a Objetos** — curso de Sistemas de Informação.

## Sobre o projeto

O Impacta permite que alunos voluntários se cadastrem no sistema e participem de ações socioambientais promovidas pela instituição, acumulando pontuação de impacto conforme se inscrevem e participam das atividades.

Não há uma classe `Main`: o sistema é composto pelas classes que fornecem as funcionalidades a serem invocadas (`Impacta`) e pelas entidades de domínio, sendo validado através de testes unitários com JUnit 5.

## Funcionalidades

### Voluntários

- Cadastro de voluntários com nome, e-mail e matrícula (e-mail como chave única de identificação)
- Exibição dos detalhes de um voluntário: nome, quantidade de ações que participa e pontuação de impacto acumulada
- Listagem de todos os voluntários cadastrados, em ordem decrescente de pontuação (desempate por ordem alfabética do nome)

### Ações Socioambientais

Toda ação possui título, descrição, data e capacidade máxima de voluntários. O sistema oferece três tipos de ação, cada um com sua própria fórmula de pontuação:

| Tipo | Atributo específico | Fórmula de pontuação |
|---|---|---|
| **Plantio de Mudas** | Quantidade de mudas | 5 pontos base + 2 pontos por muda plantada |
| **Mutirão de Reciclagem** | Duração (horas) | 4 pontos por hora de duração |
| **Oficina Ecológica** | Duração (horas) e kit de material educativo | 3 pontos por hora + 10 pontos bônus se houver kit |

- Cadastro de ações de cada tipo
- Inscrição de voluntários em ações existentes
- Exibição dos detalhes de uma ação (título, descrição, data, pontuação calculada, lista de inscritos e atributos específicos do tipo)

## Arquitetura

O sistema utiliza herança e polimorfismo para modelar os diferentes tipos de ação:

```
Acao (classe abstrata)
 ├── titulo, descricao, data, maxCapacidade, lista de inscritos
 ├── calcularPontuacao() — método abstrato
 │
 ├── Plantio
 ├── MutiraoReciclagem
 └── OficinaEcologica
```

Cada subclasse implementa sua própria versão de `calcularPontuacao()`, permitindo que o sistema calcule a pontuação de qualquer ação de forma polimórfica, sem precisar saber seu tipo concreto.

A classe `Impacta` atua como fachada do sistema, mantendo as coleções de voluntários e ações e expondo os métodos de negócio.

## Regras de negócio e exceções

O sistema lança exceções específicas para as seguintes situações:

- **`AcaoLotadaException`** — tentativa de inscrição em uma ação que já atingiu sua capacidade máxima
- **`InscricaoDuplicadaException`** — tentativa de inscrever o mesmo voluntário duas vezes na mesma ação
- **`IllegalArgumentException`** — cadastro de voluntário com e-mail já existente, matrícula já existente, título de ação duplicado, ou campos obrigatórios inválidos

## Testes

O projeto conta com testes unitários (JUnit 5), organizados em `ImpactaTest`, cobrindo:

- Cadastro de voluntários e ações, com e sem sucesso
- Cálculo polimórfico de pontuação das três subclasses de `Acao`
- Ordenação do ranking de voluntários (por pontuação e desempate por nome)
- Inscrição de voluntários, incluindo os cenários de ação lotada e dupla inscrição

## Estrutura de classes

| Classe | Responsabilidade |
|---|---|
| `Impacta` | Fachada do sistema; cadastro, listagem e inscrição |
| `Voluntario` | Dados do aluno voluntário |
| `Acao` | Classe abstrata com atributos e comportamento comuns às ações |
| `Plantio` | Ação de plantio de mudas |
| `MutiraoReciclagem` | Ação de mutirão de reciclagem |
| `OficinaEcologica` | Ação de oficina ecológica |
| `AcaoLotadaException` | Exceção de capacidade máxima excedida |
| `InscricaoDuplicadaException` | Exceção de inscrição repetida |

## Tecnologias

- Java
- JUnit 5
