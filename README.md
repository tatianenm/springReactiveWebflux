# SpringReactiveWebflux
API Spring Reactive Webflux Mongodb

 Projeto é uma  API Rest WebFlux que lista linhas de ônibus e itinerários.

  Requisitos:
     IDE Intellij.
     jdk 11.0.5
     maven
         
   Funcionamento
   A aplicação foi desenvolvida Spring WebFlux.

   Linhas de Ônibus
   URL:GET/webflux/v1/linhas
   Retorna lista de linha de ônibus existente

   Linhas de Ônibus Por Nome
   URL:GET/webflux/v1/linhas/{nome}
   Pesquisa Linha de Ônibus Por Nome

   Salvar Linha de Ônibus
   URL:POST/webflux/v1/linhas

   Atualiza linha de ônibus
   URL:PUT/webflux/v1/linhas

  Código implementado:
  Divisão de responsabilidades das classes onde o a classe Router responsável pelas rotas, handler responsável por aceitar 
  e devolver a requisição,  camada de service responsável pelas regras de negócio e o repository responsável pela conexão 
  com banco de dados, o banco de dados mongodb foi utilizado pela localização geográfica.
  DTOs recebem e devolvem os dados necessários.

  Melhorias no código:
  Faltou implementação dos métodos por falta de tempo e por falta de conhecimento de estrutura e padrão de programação reativa,
  retorno endpoints complexos que retornavam text/html dificultou bastante.
