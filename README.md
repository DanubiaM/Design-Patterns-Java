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

### Visual é melhor ;)

![image](https://user-images.githubusercontent.com/53872016/218346794-5e51084f-ace4-476b-930c-dbb23303394c.png)



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
### Visual é melhor ;)

![image](https://user-images.githubusercontent.com/53872016/219983598-aab5dca7-5f1a-4120-a6af-4f3e4f201e15.png)

## Template Method

  Nesse pattern tempos a implementação de uma classe abstrata que encapsula uma template de um algoritmo, permitindo que as classes concretas que herdarem sua implementação, possa definir o passo a passo do algoritmo.
  
  ### Exemplo
Imagine uma classe abstrata chamada Network responsável por fornecer a template para a realização do login em uma rede social. De acordo com a rede social o envio de dado, login e logout varia. 

A network:
	public abstract class Network {
	    String userName;
	    String password;

	    Network() {}

	    /**
	     * Publish the data to whatever network.
	     */
	    public boolean post(String message) {
		// Authenticate before posting. Every network uses a different
		// authentication method.
		if (logIn(this.userName, this.password)) {
		    // Send the post data.
		    boolean result =  sendData(message.getBytes());
		    logOut();
		    return result;
		}
		return false;
	    }

	    abstract boolean logIn(String userName, String password);
	    abstract boolean sendData(byte[] data);
	    abstract void logOut();
	}
	
Classe que extendem a network

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
### Visual é melhor ;)

  ![image](https://user-images.githubusercontent.com/53872016/219984662-60278ddc-941c-4c9d-a5dd-937357ce1af4.png)

## State
  
   Este padrão comportamental permite que o objeto altere seu comportamento quando o seu estado inteiro muda. Neste caso, para cada estado possível é criado uma classe, sendo possível assim, extrair seus comportamentos para essa classe.
   
#### Exemplo

Neste exemplo, queremos aplicar o desconto à um orçamento. O valor desse desconto vai variar com o estado do orçamento: aprovado, em análise, reprovado ou finalizado.


     public abstract class SituacaoOrcamento {
   
        public BigDecimal calcularValorDescontoExtra(Orcamento orcamento) {

          return BigDecimal.ZERO;
        }

        public void aprovar(Orcamento orcamento) {
          throw new DomainException("Orcamento não pode ser aprovado");
        }

        public void reprovar(Orcamento orcamento) {
          throw new DomainException("Orcamento não pode ser reprovado");
        }

        public void finalizar(Orcamento orcamento) {
          throw new DomainException("Orcamento não pode ser finalizado");
        }

    }
A situação ordem implementa as situações possíveis de uma ordem, entretando nesse caso quando violado a ordem de estados  é lançado um exception

    public class Aprovado extends SituacaoOrcamento {


    public BigDecimal calcularValorDescontoExtra(Orcamento orcamento) {
      return orcamento.getValor().multiply(new BigDecimal("0.02"));
    }


    public void finalizar(Orcamento orcamento) {
      orcamento.setSituacao(new Finalizado());
    }

    }
   Aprovado pode ser calculado o valor de desconto e finalizado
   
     public class Reprovado extends SituacaoOrcamento {
  
	
    public void finalizar(Orcamento orcamento) {
      orcamento.setSituacao(new Finalizado());
    }

    }
  Reprovado não permite cálculos, apenas finalizar.
  
  
    public class Finalizado extends SituacaoOrcamento {

    }
    
  Finalizado não possui nenhum método.
  
    public class EmAnalise extends SituacaoOrcamento {
  
	
      public BigDecimal calcularValorDescontoExtra(Orcamento orcamento) {
        return orcamento.getValor().multiply(new BigDecimal("0.05"));
      }


      public void reprovar(Orcamento orcamento) {
        orcamento.setSituacao(new Reprovado());
      }

      public void aprovar(Orcamento orcamento) {
        orcamento.setSituacao(new Aprovado());
      }
	
    }
    
  Em análise o valor do cálculo altera-se e poderá ser aprovado ou reprovado.
  
      public class Orcamento {

        private BigDecimal valor;
        private int quantidadeItens;
        private SituacaoOrcamento situacao;


        public Orcamento(BigDecimal valor, int quantidadeItens) {
          this.valor = valor;
          this.quantidadeItens = quantidadeItens;
          this.situacao= new EmAnalise();
        }

        public void aplicarDescontoExtra() {
          BigDecimal valorDoDesontoExtra = this.situacao.calcularValorDescontoExtra(this);
          this.valor = this.valor.subtract(valorDoDesontoExtra);
        }
        //Gettrs and Settrs
      }
      
  Agora quando chamarmos o método para aplicar o desconto, o cálculo será realizado com base no tipo de estado do objeto.
  
#### Quando Utilizar?
  - Quando um objeto comporta-se de maneira diferente dependendo do seu estado, sendo a quantidade de estados grande.
  - Quando houver código duplicado parecidos baseado em estados.

### Visual é melhor ;)
![image](https://user-images.githubusercontent.com/53872016/219990351-769acd3c-c53d-4570-8c2a-c523e1a89252.png)

 
## Command

Encapsula uma solicitação como um objeto, desta forma permitindo que você parametrize clientes com diferentes solicitações, enfileire ou registre (log) solicitações e suporte operações que podem ser desfeitas. Uma das suas caracteristicas é o desaclopamento do código do objeto que faz a solicitação com o objeto que recebe a solicitação.

### Exemplo
	
	Primeiro criamos um Receiver (quem recebe o comando)
		
		public class SmartHouseLight{
			private Boolean isOn;
			private Integer intensity;
			public String name;
			
			SmartHouse(String name ){
			
				this.name =  name;
			}
			
			private String getPorweStatus(){
				return this.isOn ? "ON" : "OFF";
			}
			
			private Boolean on(){
				this.isOn=true;
				
				return this.isOn;
			
			}
			
			private Boolean off(){
				this.isOn=false;
				
				return this.isOn
			
			}
			
			
			private Integer increaseIntensity(){
			
				if(this.intensity >=100) {return  this.intensity}
				
				this.intensity+=1;
				return this.intensity;
			}
			
			private Integer decreaseIntensity(){
			
				if(this.intensity <= 0) {return  this.intensity}
				
				this.intensity-=1;
				return this.intensity;
			}

		}
	

	Agora vamos criar nossa interface Command:
	
		public Interface SmartHouseCommand{
			public void execute();
			public void undo();
		
		}
		
	Interface criada, vamos ao Commandle:
	
	 public class LightPowerCommand implements SmartHouseCommand{
	 	public SmarthHouseLight light;
		
		public  LightPowerCommand(SmarthHouseLight light){
			this.light = light;
		}
		
		@Override
		public void execute(){
			this.light.on();
		}
		
		@Override
		public void undo(){
			this.light.off();
		}
		
	 }
	 
	O Invoker:
	
		public class SmartHouseApp(){
			private Map<String, SmartHouseCommand> commands;
			
			public void addCommand(String key, SmartHouseCommand command){
				this.commands.put(key, command);
			}
			
			public void executeCommand(String key){
				commands.get(key).execute();
			}
			
			public void undoCommand(String key){
				commands.get(key).undo();
			}
			
			
		
		}
		
		
	E minha main:
	
	 public class Demo {
        	public static void main(String[] args) throws IOException {
		
			//Receiver
			SmartHouseLight bedroomLight = new SmartHouseLight('Luz Quarto');
			SmartHouseLight bathroomLight = new SmartHouseLight('Luz Banheiro);

			//Command
			LightPowerCommand bedroomLightCommand = new LightPowerCommand(bedroomLight);
			LightPowerCommand bathroomLightCommand = new LightPowerCommand(bathroomLight);

			lighhtPoweCommand.execute();
			lighhtPoweCommand.undo();
			
			//Ou podemos fazer assim - Invoker
			SmartHouseApp smartHouseApp = new SmartHouseApp();			
			smartHouseApp.addCommand('btn-1', bedroomLightCommand );
			smartHouseApp.addCommand('btn-2', bathroomLightCommand);

			smartHouseApp.executeCommand('btn-1');
			smartHouseApp.undoCommand('btn-1');
			
			smartHouseApp.executeCommand('btn-2');
			smartHouseApp.undoCommand('btn-2');

			
			
		}
	
### Quando Utilizar?
	- Desacoplar o objeto que envia a solicitação do objeto que recebe;
	- Tratar um comando como um objeto.
	- Permitir que ações sejam feitas e desfeitas.
	
	
### Visual é melhor ;)
![image](https://user-images.githubusercontent.com/53872016/220515841-4cf16a35-eaee-49be-ae06-cebfce49a4b1.png)



 
 
## Fontes:
  - <a href= "https://cursos.alura.com.br/course/introducao-design-patterns-java"> Design Patterns em Java I: boas práticas de programação</a>
  - <a href="https://refactoring.guru/design-patterns/">Refactoring Guru</a>
  - <a href="https://www.youtube.com/watch?v=-lRzadP9kJQ&list=PLbIBj8vQhvm0VY5YrMrafWaQY2EnJ3j8H&index=30"> Padrões de Projeto - Otávio Miranda</a>
