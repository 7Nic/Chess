CHESS

Parte 2 do projeto:

===============THREADS==================
* Chamar o construtor (de um obj, exemplo: autosave, que extende Thread ou runnable) na main passando o que voce quer salvar (o estado do jogo, o model, controller)
* Essa classe tem o run() e dentro desse run voce ira pegar o objeto passado e salvar no disco. E para salvar no disco é necessário extender o seiralize, na hirarquia inteira das heranças
* Na main, depois de construir vc da start na thread

Como funcionará:
Serializar o model, se quiser carregar um salvo basta carregar esse objeto e colocar no  programa. Se quiser começar do 0 basta criar um novo objeto model chamando o construtor dele.

========================TRY CATCH================
Colocar erros de peça, erros do programa, etc
