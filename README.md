# Trabalho Final de Programação Orientada a Objetos - Institution-System

## Objetivo

O objetivo deste trabalho é o de explorar recursos apresentados em sala de aula. Tais
recursos compreendem a modelagem e implementação de uma solução que emprega interface
gráfica e que seja feito explorando os conceitos de orientação a objetos.

## Introdução

Você foi contratado para desenvolver um sistema que permita gerenciar melhora os
indivíduos de uma instituição de ensino. Adicionalmente deve-se poder fazer acesso a detalhes
de disciplinas e horários.

Cada disciplina deve ter um conjunto de professores que podem ministrá-la. A criação
de turmas de uma disciplina depende da existência de alunos. Cada turma pode ter um número
máximo de alunos, valor este informado no momento de cadastro da disciplina. Também faz
parte da criação de uma disciplina informar a quantidade de horas aula por semana. A disciplina
de POO, por exemplo, tem uma carga horária de 4 horas aula por semana.

Os alunos podem se matricular em uma disciplina, mas sua turma é definida de acordo
com a disponibilidade de vagas. A criação das turmas de uma dada disciplina deve ser feita
somente ao final da matrícula dos alunos no sistema. A quantidade de alunos por turma deve
ser equalizada de tal forma que não haja diferença significativa entre as turmas. Um aluno não
deve ter mais de 28 horas aula associadas à sua matrícula. Uma turma de uma dada disciplina
somente pode ser criada se houver pelo menos 20% de sua quantidade máxima de alunos, valor
definido durante a criação de uma disciplina.

Uma vez criadas as turmas, deve-se alocar professores. Um professor não pode ter mais
do que 12 horas aula associadas a ele. A escolha dos professores deve ser feita de acordo com
o apontamento da responsabilidade por disciplina. Caso uma turma não tenha professor para
ser alocado, deve-se contratar mais um profissional.

Considere que os seguintes itens devem fazer parte do desenvolvimento do presente
trabalho.

- Possibilidade de cadastro pessoas
  - Professores e alunos, os quais devem ter um nome específico e um identificador
    único. Todo professor e aluno cadastrado deve ser salvo em um arquivo, o qual
    pode ser lido no início da execução do sistema. O desenvolvedor pode escolher
    se será um arquivo único, onde haverá diferenciação entre professor e aluno,
    ou dois arquivos separados.
- Cadastro de disciplinas

  - Lançamento de detalhes sobre a disciplina, tal como descrição, carga horária e
    professores que podem ministrar a disciplina

- Matrícula por aluno
  - Cada aluno pode sinalizar a disciplina que deseja cursar
- Botão de alocações
  - Processo pelo qual as turmas são criadas e os alunos/professores alocados
    conforme critério apontado.
- Ao final, deve ser possível
  - Visualizar detalhes de uma disciplina
    - Turmas que forma criadas, com o professor e alunos alocados para cada
      turma
  - Visualizar detalhes de um professor
    - Turmas alocadas para cada professor, bem como a carga horária total
  - Visualizar detalhes de um aluno
    - Disciplinas que está realizando e carga horária

Ponto importante é que todos os requisitos apresentados anteriormente devem ser
feitos explorando programação orientada a objetos, buscando sempre o uso de herança e
polimorfismo, coleções, manipulação de arquivos, exceções e operadores de agregação. É
imperativo o uso de interface gráfica (i.e. swing) para a manipulação e visualização dos dados.

## Critérios de avaliação

```
O trabalho será avaliado conforme os seguintes critérios:

- Relatório do projeto: 1 pto.
- Uso de arquivos: 1 pto.
- Tratamento de exceções: 1 pto.
- Interface gráfica com o usuário: 1 pto.
- Uso de herança e polimorfismo: 1 pto.
- Uso de lambda/ stream : 1 pto.
- Sistema conforme a descrição: 4 pts.
```

## Considerações finais

Conforme descrito anteriormente, o trabalho deverá ser composto pela parte de
desenvolvimento, mas também um relatório onde detalhes de funcionamento e compilação
deverão estar disponíveis. Aos autores é dada a a liberdade de adaptação do sistema, desde que
haja uma justificativa a qual deverá estar contida em um relatório final.

O trabalho pode ser desenvolvido em grupos de até 3 alunos. O trabalho deverá ser
postado na sala do moodle até o início da aula. O material a ser postado no moodle deve ser
inserido em um único arquivo zip. Este arquivo deve conter o nome e o sobrenome de cada
integrante do grupo.

Trabalhos com erro de compilação terão grau zero. A mesma avaliação será dada para
trabalhos que percepção de plágio, seja parcial ou completo. Todos envolvidos receberão grau
zero. Dito isto, não compartilhe sua solução nem a deixe pública.

Todos os trabalhos a serem avaliados, serão baixados do moodle e avaliados pelo
professor em um computador da universidade. Sendo assim, considerem validar seus trabalhos
nos computadores da instituição.

## Desenvolvimento

## Compilação e execução
