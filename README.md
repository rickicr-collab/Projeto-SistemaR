# Sistema InfoR

O **InfoR** é um sistema de gestão de Ordens de Serviço desenvolvido em **Java**, utilizando **MySQL** como sistema de gerenciamento de banco de dados e o framework **iReport** para geração de relatórios dinâmicos.
O sistema tem foco em organizar e gerar ordem de serviços para facilitar o trabalho do prestador de serviço, filtrando e mostrando informações úteis para seus clientes para facilitar o trabalho para o mesmo e seus clientes ao prestar seus serviços. Sistema totalemnte funcional e operavel com integraçao com bando de dado relacional Mysql para armazenagem de dados e filtragens.
o sistema possui integração com o IReport framework muito utilizado nos para alguns sistemas para gerações de relatorios dinamicos no entanto parte de suas funções são apenas utilizaveis utilizando a versão do Java é possivel baixar esta mesma versão no site da empresa Oracle a proprietária do Java e não tornala principal apenas uma versão armazenada no seu computador e configura-la para a boa utilização do Ireport no seu computador sem gerar imcompatibilidade das versões pela JVM.

## 💻 Tecnologias Utilizadas
- Java principal - qualquer versão (recomendado a mais atual)
- Java 7 (necessário utilizar a versão 7 para compatibilidade com o iReport)
- MySQL
- iReport (para relatórios)
- JDBC (conector MySQL)
- IDE recomendada: NetBeans (compatível com iReport)

## ⚠️ Requisitos Importantes

### Versão do Java

> ⚠️ Devido à compatibilidade do iReport, é **necessário utilizar o Java 7** para o correto funcionamento do framework de relatórios.

Embora o sistema possa ser executado em versões mais recentes do Java, a geração de relatórios com o iReport requer especificamente a versão 7. Caso utilize uma versão superior, recomenda-se configurar múltiplas JDKs e usar o Java 7 apenas para compilar e executar os relatórios.

## 📦 Dependências (JARs Necessárias)

Para garantir o correto funcionamento do iReport e geração de relatórios no seu sistema, certifique-se de incluir os seguintes arquivos JAR no seu projeto:

- commons-beanutils-1.8.2
- commons-collections-3.2.1
- commons-digester-2.1
- commons-logging-1.1
- groovy-all-2.0.1
- iText-2.1.7.js2
- jasperreports-5.6.0
- jxl-2.6.10
- poi-3.7-20101029


## 🎬 Vídeo Tutorial

Um professor chamado José de Assis irá explicar passo a passo como configurar corretamente o iReport com o Java 7 para uso no sistema InfoR Recomendo seu canal para mais tutoriais e explicações de outros assuntos e aprendizado.

**➡️ https://youtu.be/3ak4XjcKaw8?si=iMauMA-mw7n-TMlY.**

## 🛠️ Configuração do Ambiente

1. Instale o Java 7 em seu sistema.
2. Configure sua IDE (recomenda-se NetBeans) para utilizar o JDK 7.
3. Configure a conexão com o banco de dados MySQL.
4. Adicione os arquivos JAR necessários para o iReport.
5. Teste a geração de relatórios.

## 🖼️ Imagens do Sistema

Abaixo estão algumas imagens que ilustram a interface e o funcionamento do sistema:

### 📌 Tela Inicial

![Tela Inicial](Images/TelaInicial.jpg)

A tela inicial apresenta uma visão geral do sistema, com acesso rápido às principais funcionalidades.

### 📌 Exemplo de Funcionalidade

![Funcionalidade](images/funcionalidade-exemplo.png)

Esta imagem mostra uma das funcionalidades em execução, destacando a usabilidade e o design intuitivo da aplicação.




## 📖 Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

Desenvolvido com 💡 por [Ricardo Rosendo]





