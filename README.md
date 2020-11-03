# Classificador KNN: Implementação Serial e Concorrente

Este repositório apresenta a implementação do classificador KNN em duas versões: Serial e Concorrente, além da comparação entre as duas versões.

### KNN

O algoritmo *k-nearest neighbors* (KNN) é um algoritmo simples de *machine learning* usado para classificação supervisionada, tendo, principalmente, os seguintes componentes:
* Um conjunto de dados de treino: é formado por instâncias com um ou mais atributos
que define cada instância e um atributo especial que determina o rótulo do
instância.
* Um conjunto de dados de teste: usado para medir o comportamento do algoritmo.
* Distância: métrica utilizada para determinar a distância (ou similaridade)
entre as instâncias do conjunto de dados de treino e as novas instâncias que se deseja
classificar.

### Iris Dataset

Para o algoritmo desenvolvido neste repositório, foi utilizado o conjunto de dados Iris, disponível no (UCI)[https://archive.ics.uci.edu/ml/datasets/iris].

O conjunto de dados Iris é formado por 150 instâncias e 4 atributos. São 3 classes de 50 instâncias cada, onde cada classe diz respeito a um tipo da planta Iris. As classes são: *Iris Setosa*, *Iris Versicolour* e *Iris Verginica*, enquanto que os atributos são: *sepal length*, *sepal width*, *petal length* e *petal width*.

### Estrutura

O repositório ficou estruturado da seguinte forma:
* **dataset/Iris**: Conteúdo do conjunto de dados Iris obtido a partir do site do UCI.
* **KNNClassifier**
  * **DatasetLoader**: Classe responsável por fazer as manipulações no conjunto de dados, desde a leitura completa do dataset até a separação dele em conjuntos de treino e teste.
  * **Iris**: Classe responsável por mapear as informações correspondentes ao dataset e efetuar operações que dizem respeito às suas informações, por exemplo, o cálculo da distância.
  * **SerialKnnClassifier**: Classe que implementa a versão sequencial do classificador Knn em cima do conjunto de dados Iris.
  * **ConcurrentKnnClassifier**: Classe que implementa a versão concorrente do classificador Knn em cima do conjunto de dados Iris.
  * **Main**: Classe principal que tem por responsabilidade instanciar a preparação dos datasets, aplicar as versões sequencial e concorrente e, também, registrar a performance de cada versão do classificador.
* **comparison/performances**: Esse arquivo reúne os resultados de cada execução para cada versão do classificador. Foram 10 execuções para cada versão, com o *k* iniciando em 3 e indo até 30 em cada uma. No arquivo podem ser encontradas as informações sobre a acurácia em cada versão, bem como o tempo de execução.

### Comparando as versões

Para testar as versões do classificador, foi utilizado um Notebook Dell com processador Intel Core i3-4005U de 1.70GHz, RAM de 4GB e sistema operacional Windows 10 de 64 bits.

Como mencionado anteriormente, os resultados foram registrados no arquivo **comparison/performances**. Foram 10 execuções para cada versão, com o *k* iniciando em 3 e indo até 30, totalizando 10 registros para cada versão.

Com base nas execuções, percebeu-se que a versão serial foi mais rápida em 90% das vezes, apenas na primeira, com k = 3, foi mais lenta que a versão concorrente. As duas versões tiveram o mesmo valor de acurácia 6 vezes, sendo que dessas 4 restantes, a versão concorrente teve a melhor acurácia em 2. Além disso, a partir de k = 18, a acurácia manteve-se em 66% em ambas as versões.


### Referências

* Gonzalez, J. F. (2017). Mastering Concurrency Programming with Java 9 - Second Edition: Vol. 2nd ed. Packt Publishing
* https://aimlsite.wordpress.com/2018/01/05/k-vizinhos-mais-proximos-algoritmo-de-classificacao/
* https://archive.ics.uci.edu/ml/datasets/iris