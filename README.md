# interviewVivo3

Esse projeto se trata de uma entrevista, no intervalo de 05/09 - 08/09 foi desenvolvido 3 projetos Java spring boot para 3 exercícios testes.

Esse projeto é referente a seguinte questão:

3. Dado o seguinte log da primeira corrida de Super-Heróis – Volta ao Mundo.
Hora;Super-Heroi;No Volta;Tempo Volta;Velocidade média da volta
23:49:08.277;038–Superman;1;1:02.852;44,275
23:49:10.858;033–Flash;1;1:04.352;43,243
23:49:11.075;002–Mercúrio;1;1:04.108;43,408
23:49:12.667;023–Sonic;1;1:04.414;43,202
23:49:30.976;015–PAPALÉGUA;1;1:18.456;35,47
23:50:11.447;038–Superman;2;1:03.170;44,053
23:50:14.860;033–Flash;2;1:04.002;43,48
23:50:15.057;002–Mercúrio;2;1:03.982;43,493
23:50:17.472;023–Sonic;2;1:04.805;42,941
23:50:37.987;015–PAPALÉGUA;2;1:07.011;41,528
23:51:14.216;038–Superman;3;1:02.769;44,334
23:51:18.576;033–Flash;3;1:03.716;43,675
23:51:19.044;002–Mercúrio;3;1:03.987;43,49
23:51:21.759;023–Sonic;3;1:04.287;43,287
23:51:46.691;015–PAPALÉGUA;3;1:08.704;40,504
23:52:01.796;011–GATOAJATO;1;3:31.315;13,169
23:52:17.003;038–Superman;4;1:02.787;44,321
23:52:22.586;033–Flash;4;1:04.010;43,474
23:52:22.120;002–Mercúrio;4;1:03.076;44,118
23:52:25.975;023–Sonic;4;1:04.216;43,335
23:53:06.741;015–PAPALÉGUA;4;1:20.050;34,763
23:53:39.660;011–GATOAJATO;2;1:37.864;28,435
23:54:57.757;011–GATOAJATO;3;1:18.097;35,633

Desenvolva uma API que leia o arquivo de log acima e retorne as seguintes
informações:

Posição de Chegada, Código do Super-herói, Nome Super-herói, Quantidade de
Voltas Completadas e Tempo Total de Prova.
Observações
• A primeira linha do arquivo pode ser desconsiderada (Cabeçalho). •
A corrida termina quando o primeiro colocado completa 4 voltas.
Bônus
1. Descobrir a melhor volta de cada super-herói.
2. Descobrir a melhor volta da corrida.
3. Calcular a velocidade média de cada super-herói durante toda a corrida.

Para sua utilização, clone e importe o repositório, então execute os seguintes comandos:

mvn clean package
mvn clean install -DskipTests 

A porta utilizada foi a padrão 8080, então as url de acesso a api e o swagger estarão disponíveis na 8080.
Esse projeto conta com a documentação openAPI para o swagger, então recomendo a sua utilização para testar os endpoints.

Ao clicar na InterviewEx3Application com o botão direito e selecionar run, o código está pronto e poderá verificar no endereço:
http://localhost:8080/swagger-ui/index.html?
![image](https://user-images.githubusercontent.com/29410618/189129347-2f7408f3-332e-448f-9d58-3c18f09711ff.png)


Ao adicionar o body (pode ser executado com o próprio log acima do enunciado) e executar o endpoint /running/readLog deverá ler o log e 
retornar as informações da corrida de super heróis na responsta do response entity.
exemplo:
![image](https://user-images.githubusercontent.com/29410618/189129944-77b8b860-f54f-4c91-963d-01cf3efe4df1.png)

Diferente da questão de número dois onde explorei o cenário ruim de execução, nas falhas por badrequest, neste projeto explorei os testes de
cenário feliz, cobrindo toda a camada de serviço e mock.

![image](https://user-images.githubusercontent.com/29410618/189131170-fe4aef19-f4ad-40cc-b688-6231d5fddae8.png)

Pela questão do curto espaço de tempo não foram feitas validações de log, então logs inconsistentes devem retornar erro como resposta e não 
algo tratado com custom exceptions ou mensagens padronizadas de erro.

Vale lembrar que a adição de persistência torna muitos dos processos dessa questão mais fáceis e claros, bem como podendo expor os endpoints
que consumem as entidades, separando todos os requisitos das questões em enpoints, ao invés de sempre ter que criar uma nova corrida e calcular 
tudo novamente, então existem alguns códigos em forma de comentários pela aplicaçãço, para quando for criado o banco serem adicionados nas @table.

Os demais projetos se encontram nos:
https://github.com/rafapiazza/interviewVivo1/tree/master
https://github.com/rafapiazza/interviewVivo/tree/master
