# Design-Patterns-Java
Repositório  voltado para o estudo de Design Patterns em Java.


## Strategy
Padão que permite o conapsulamento de um algoritmo, de modo que possa variar entre os clientes que utilizam.


#### Exemplo:
  Um imposto, pode variar seu cálculo de acordo com o tipo de imposto. Portando, devemos implementar uma Interface que tenha uma função para o cálculo do imposto.
  
 
  
    public interface Imposto {
      BigDecimal calcular(Orcamento orcamento); 
    }
    
  
  Dessa forma, as classes ao qual implementa-lo poderá desenvolver diferentes cálculos de impostos.
  
    public class ICMS implements Imposto{

      public BigDecimal calcular(Orcamento orcamento) {
        return orcamento.getValor().multiply(new BigDecimal("0.1"));
      }
    }
  
    public class CalculadoraDeImpostos {
      public BigDecimal calcular(Orcamento orcamento, Imposto imposto) {
        return imposto.calcular(orcamento);
      }
    }

Esse padrão obdece o princípio Open-Closed de SOLID. Sempre que houver a necessidade do cálculo de um imposto diferente basta que agora seja implementada uma nova classe, não necessitando de modificação no algoritmo já existente.

## Chain of Responsabilitty

Permite que solicitações sejam enviadas ao longo de uma cadeia. Ao receber uma solicitação, o manipulador verifica se atende a necessidade atual ou passa para o próximo manipulador.

O padrão faz com que não seja necessário o uso encadeado de estruturas condicionais para cada situação encontrada.

#### Exemplo
  Imaginando um cenário onde o cálculo do desconto sobre um orçamento varia de acordo com orcamento. Temos a implementação realizada da seguinte forma:
  
  ##### Classe abstrata onde é implementado o encadeamento. 
    
    public  abstract class Desconto {

      protected Desconto proximo;

      public Desconto(Desconto proximo) {
        this.proximo = proximo;
      }

      public abstract BigDecimal calcular(Orcamento orcamento);

    }

Implementações do cálculo do desconto

    public class DescontoParaOrcamentoComValorMaiorQueQuinhentos extends Desconto{

      public DescontoParaOrcamentoComValorMaiorQueQuinhentos(Desconto proximo) {
        super(proximo);
        // TODO Auto-generated constructor stub
      }

      public BigDecimal calcular(Orcamento orcamento) {

        if(orcamento.getValor().compareTo(new BigDecimal("500")) > 0 ) {
          return orcamento.getValor().multiply(new BigDecimal("0.05"));
        }

        return proximo.calcular(orcamento);
      }
    }
    
   public class SemDesconto extends Desconto{

    public SemDesconto() {
      super(null);
      // TODO Auto-generated constructor stub
    }

    public BigDecimal calcular(Orcamento orcamento) {

      return BigDecimal.ZERO;
    }
  }

A implementação da função para chamada encadeada
    public class CalculadoraDeDescontos {

      public BigDecimal calcular(Orcamento orcamento) {

        Desconto desconto  = 
            new DescontoParaOrcamentoComMaisDeCincoItens(
            new DescontoParaOrcamentoComValorMaiorQueQuinhentos( new SemDesconto()));

        return desconto.calcular(orcamento);
      }

    }
    
Essse padrão abrange o principio de responsabilidade única e aberto fechado.


 
