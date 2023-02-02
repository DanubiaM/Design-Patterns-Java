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


## Template Method

  Nesse pattern tempos a implementação de uma classe abstrata que encapsula uma template de um algoritmo, permitindo que as classes concretas que herdarem sua implementação, possa definir o passo a passo do algoritmo.
  
  ### Exemplo
Imagine uma classe abstrata chamada Network responsável por fornecer a template para a realização do login em uma rede social. De acordo com a rede social o envio de dado, login e logout varia. 
    
    public class Facebook extends Network {
        public Facebook(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public boolean logIn(String userName, String password) {
            System.out.println("\nChecking user's parameters");
            System.out.println("Name: " + this.userName);
            System.out.print("Password: ");
            for (int i = 0; i < this.password.length(); i++) {
                System.out.print("*");
            }
            simulateNetworkLatency();
            System.out.println("\n\nLogIn success on Facebook");
            return true;
        }

        public boolean sendData(byte[] data) {
            boolean messagePosted = true;
            if (messagePosted) {
                System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
                return true;
            } else {
                return false;
            }
        }

        public void logOut() {
            System.out.println("User: '" + userName + "' was logged out from Facebook");
        }

        private void simulateNetworkLatency() {
            try {
                int i = 0;
                System.out.println();
                while (i < 10) {
                    System.out.print(".");
                    Thread.sleep(500);
                    i++;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
Implementação  na main
    
      public class Demo {
        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Network network = null;
            System.out.print("Input user name: ");
            String userName = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();

            network = new Facebook(userName, password);
         
            network.post(message);
        }
    }   
  
 
#### Fontes:
  - <a href= "https://cursos.alura.com.br/course/introducao-design-patterns-java"> Design Patterns em Java I: boas práticas de programação</a>
  - <a href="https://refactoring.guru/design-patterns/template-method/java/example">Template Method</a>
