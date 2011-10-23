package Classes;

import java.util.Scanner;

/**
 * Classe Principal do Sistema
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 * @version 1.0
 */


public class MainTeste {
	
	private static Fachada rede = new Fachada();
	private static String idSessao = null;
	public static void main(String[] args) {
		
		while (true){
			imprimirMenuPrincipal();
			String entrada = recebeEntrada();
			
			if (entrada.equals("1")){
				fazerLogin();
			}
			else if(entrada.equals("2")){
				cadastrarUsuario();
			}
			else if(entrada.equals("3")){
				System.out.println("Sistema finalizado com Sucesso!");
				break;
			}
			else{
				System.out.println("Opcao Invalida!");
			}
		}
		
	}
	//---------------------#REALIZA LOGIN DO USUARIO#--------------------------
	private static void fazerLogin(){
		System.out.print("Digite seu Login: ");
		String login = recebeEntrada();
		try{
			idSessao=rede.abrirSessao(login);
			menuDoUsuario(login);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	//---------------------------------------------------------------------------
//	
//	
	private static void menuDoUsuario(String login) throws Exception{
		System.out.println("Ola sr(a): " + rede.getAtributoUsuario(login, "nome") + "\n"); 
		
		while (true){
			opcoesDoUsuario();
			System.out.println("\n");
			String entrada = recebeEntrada();
			
			if (entrada.equals("1")){
				cadastrarItem();
			}
			else if(entrada.equals("2")){
				System.out.println("Voce deseja localizar atraves de um Atributou ou a partir do Endereco?\n");
				System.out.println("1) Atributos\n 2) Endereco");
				String opcao = recebeEntrada();
				if(opcao.equals("1")){
					localizarUsuario();
					
				}
				else if(opcao.equals("2")){
					localizarUsuarioEndereco();
				}
				else{
					System.out.println("Opcao Invalida!");
				}
			}
			else if(entrada.equals("3")){
				visualizarPerfil();
			}
			else if(entrada.equals("4")){
			      requisicaoDeAmizade();
			}
			else if(entrada.equals("5")){
				enviarMensagem();
			}
			else if(entrada.equals("6")){
				lerMensagem();
			}
			else if(entrada.equals("7")){
				visualizarPerfilDosAmigos();
			}
			else if(entrada.equals("8")){
				fazerEmprestimo();
			}
			else if(entrada.equals("9")){
				verRankingUsuarios();
			}
			else if(entrada.equals("10")){
				aprovarEmprestimo();
			}
			else if(entrada.equals("11")){
				aprovarAmizade();
			}
			
			else if(entrada.equals("12")){
				requisitarDevolucaoItem();
			}
			
			else if(entrada.equals("13")){
				registrarInteresseItem();
			}
			
			else if(entrada.equals("14")){
				pesquisarItem();
			}
			else if(entrada.equals("15")){
				apagarItem();
			}
			else if(entrada.equals("16")){
				desfazerAmizade();
			}
			else if(entrada.equals("17")){
				historicoAtividades();
			}
			else if(entrada.equals("18")){
				historicoAtividadesConjunto();
			}
			else if(entrada.equals("19")){
				publicarPedido();
			}
			else if(entrada.equals("20")){
				oferecerItem();
			}
			else if(entrada.equals("21")){
				idSessao = null;
				System.out.println("Usuario saiu com sucesso!");
				break;
			}
			else{
				System.out.println("\nOpcao Invalida!!!\n");
			}
		}
	}
	
	private static void oferecerItem(){
		System.out.print("Digite o ID da publicacao do pedido: ");
		String idPublicacaoPedido = recebeEntrada();
		
		System.out.print("Digite o ID do Item: ");
		String idItem = recebeEntrada();
		try {
			rede.oferecerItem(idSessao, idPublicacaoPedido, idItem);
			System.out.println("Item oferecido com Sucesso!!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void publicarPedido(){
		System.out.print("Digite o Nome do Item: ");
		String nomeItem = recebeEntrada();
		
		System.out.println("Digite a Descricao do Item");
		String descricaoItem = recebeEntrada();
		try{
			String id = rede.publicarPedido(idSessao, nomeItem, descricaoItem);
			System.out.println("ID da Publicacao: " + id);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void historicoAtividadesConjunto(){
		try{
			System.out.println(rede.historicoAtividadesConjunto(idSessao));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void historicoAtividades(){
		try{
			System.out.println(rede.historicoAtividades(idSessao));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void visualizarPerfil(){
		String amigos;
		try {
			amigos = rede.getAmigos(idSessao);
			String itens = rede.getItens(idSessao);
			System.out.println("Amigos: " + amigos + "\n");
			System.out.println("Itens: " + itens + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
//	------------------------#IMPRIMI O PERFIL DOS AMIGOS DO USUARIO#------------------------
	private static void visualizarPerfilDosAmigos(){
		
		System.out.print("Digite o Login do Usuario que vc quer ver o perfil: ");
		String login = recebeEntrada();
		
		try {
			System.out.println("Amigos: " + rede.getAmigos(idSessao, login));
			System.out.println("Itens: " + rede.getItens(idSessao, login));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void localizarUsuarioEndereco(){
		try{
			System.out.println(rede.localizarUsuario(idSessao));			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	//---------------------------#LOCALIZA UM USUARIO#-------------------------------------
	private static void localizarUsuario(){

		System.out.print("Digite a Palvra-Chave: ");
		String chave = recebeEntrada();
		
		System.out.print("Digite o Atributo: ");
		String atributo = recebeEntrada();
		
		try {
			String resposta = rede.localizarUsuario(idSessao, chave, atributo);
			System.out.println("Usuarios Localizados: " + resposta + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//-----------------------#IMPRIMI MENU PRINCIPAL#----------------------------
	
	private static void imprimirMenuPrincipal(){
		System.out.println("#---------BEM VINDO A NOSSA REDE SOCIAL--------#\n" +
		           " O que voce deseja fazer?                     \n" +
		           " 1) Fazer Login\n 2) Quero Me Cadastrar\n 3) Sair"   );
	}
//	
//	//---------------------------------------------------------------------------
//	
	//-------------------------#RECEBE UMA ENTRADA VIA TECLADO#------------------
	
	private static String recebeEntrada(){
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine().trim();
		return entrada;
	}
	//---------------------------------------------------------------------------
	
	//-----------------------#CADASTRAR UM USUARIO NO SISTEMA#----------------------
	
	private static void cadastrarUsuario(){
		String nomeCompleto ,login, endereco;
		System.out.println("----------#BEM-VINDO AO SISTEMA DE CADASTRO#----------\n"+
				            "AVISO: Nao se preocupe, seus dados pessoais sao sigilosos e ficarao guardados conosco!");
		
		System.out.print("\nNome completo: ");
		nomeCompleto = recebeEntrada();
		
		System.out.print("Seu Login: ");
		login = recebeEntrada();
		
		System.out.print("Endereco: ");
		endereco = recebeEntrada();
		
		try{
			rede.criarUsuario(login, nomeCompleto, endereco);
			System.out.println("Usuario Cadastrado com Sucesso!!!");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	//----------------------------CADASTRAR UM ITEM NO SISTEMA------------------------------------------------
	
	private static void cadastrarItem(){
		String nome, descricao, categoria;
				
		System.out.print("Digite o nome do item: ");
		nome = recebeEntrada();
		
		System.out.print("Digite a descicao do item: ");
		descricao = recebeEntrada();
		
		System.out.print("Digite a categoria do item: ");
		categoria = recebeEntrada();
		
		try {
			String idItem = rede.cadastrarItem(idSessao, nome, descricao, categoria);
			System.out.println("ID do Item cadastrado: "+idItem);
			System.out.println("Item cadastrado com Sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
//---------------------------------------------------------------------------

	//----------------------Requisita Uma Amizade ha um USUARIO-----------------
	private static void requisicaoDeAmizade(){
		System.out.print("Digite o login de quem voce deseja a Amizade: ");
		String loginAmigo = recebeEntrada();
		try {
			rede.requisitarAmizade(idSessao, loginAmigo);
			System.out.println("Um Pedido de Amizade foi enviado para " + loginAmigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//--------------------------Envia uma Mensagem para um Usuario------------------	
	private static void enviarMensagem(){
		String destinatario, assunto, mensagem;
		
		System.out.print("Digite o Login do Destinatario: ");
		destinatario =  recebeEntrada();
		
		System.out.print("Digite o Assunto da Mensagem: ");
		assunto = recebeEntrada();
		
		System.out.print("Digite a Mensagem: ");
		mensagem = recebeEntrada();
		
		String idMensagem;
		try {
			idMensagem = rede.enviarMensagem(idSessao, destinatario, assunto, mensagem);
			System.out.println("idMensagem: " + idMensagem);
			System.out.println("\nMensagem enviada com Sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//------------------------Requisita Um Emprestimo----------------
	private static void fazerEmprestimo(){
		String idItem;
		int dias;
		System.out.println("Digite o ID do Item a ser Emprestado: ");
		idItem = recebeEntrada();
		
		System.out.println("Digite a Quantade de dias que vc quer passar com o Item: ");
		dias = Integer.parseInt(recebeEntrada());
		String idRequisicao;
		try {
			idRequisicao = rede.requisitarEmprestimo(idSessao, idItem, dias);
			System.out.println("ID de Requisicao de Emprestimo: " + idRequisicao);
			System.out.println("O Emprestimo foi requisitado!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void lerMensagem(){
		System.out.print("Digite o ID da Mensagem que vc quer Ler: ");
		String idTopico = recebeEntrada();
		try {
			System.out.println(rede.lerMensagens(idSessao, idTopico));
			while(true){
				System.out.println("Voce deseja Responder esta Mensagem: \n1)SIM 2)NAO");
				String opcao = recebeEntrada();
				
				if(opcao.equals("1")){
					System.out.print("Digite a Resposta: ");
					String msg = recebeEntrada();
					
					System.out.print("Digite o Login do Destinatario: ");
					String destinatario = recebeEntrada();
					
					System.out.print("Digite o Assunto da Mensagem: ");
					String assunto = recebeEntrada();
					try {
						String idMesg=rede.enviarMensagem(msg, destinatario, assunto, msg);
						System.out.println("ID da mensagem: " + idMesg);
						System.out.println("\nMensagem enviada com Sucesso!!!\n");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				else if(opcao.equals("2")){
					break;
				}
				else{
					System.out.println("Opcao Invalida!!!\n");
				}
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	}
	
	//-----------------------APROVA UM EMPRESTIMO------------------
	private static void aprovarEmprestimo(){
		System.out.print("Digite o ID da requisicao de Emprestimo a ser Aprovada: ");
		String idRequisicaoEmprestimo = recebeEntrada();
		try {
			String idEmprestimo = rede.aprovarEmprestimo(idSessao, idRequisicaoEmprestimo);
			System.out.println("ID do Emprestimo: "+idEmprestimo);
			System.out.println("\nEmprestimo Aprovado com Sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//-----------RETORNA O RANKING DOS USUARIOS BASEADO NA REPUTACAO DE CADA UM--------------------------
	private static void verRankingUsuarios() {
		
		System.out.println("Digite a Categoria: ");
		String categoria = recebeEntrada();
		try {
			System.out.println("\n--------RANKING-------\n");
			System.out.println(rede.getRanking(idSessao, categoria));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//-------------------APROVA UMA AMIZADE--------------------------------------
	private static void aprovarAmizade(){
		System.out.println("Digite o Login do Usuario: ");
		String login = recebeEntrada();
		try {
			rede.aprovarAmizade(idSessao, login);
			System.out.println("\nAmizade Aprovada com sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//------------------------Requisita a Devolucao de Um Item----------------------------
	private static void requisitarDevolucaoItem(){
		System.out.print("Digite o Id do Emprestimo: ");
		String idEmprestimo = recebeEntrada();
		try{
			rede.requisitarDevolucao(idSessao, idEmprestimo);
			System.out.println("Requisicao Requisitada com Sucesso!!!\n");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//-----------------------REGISTRA INTERESSE EM UM DETERMINADO ITEM----------------------
	private static void registrarInteresseItem(){
		System.out.print("Digite o ID do item que vc esta Interessado: ");
		String idItem = recebeEntrada();
		try {
			rede.registrarInteresse(idSessao, idItem);
			System.out.println("Registrado com Sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//---------------------------PESQUISA UM DETERMINADO ITEM-------------------------------
	private static void pesquisarItem(){
		String chave, atributo,criterioOrdenacao,tipoOrdenacao;
		
		
		System.out.print("Digite a Chave do Item: ");
		chave = recebeEntrada();
		
		System.out.println("Digite o Atributo a ser pesquisado: ");
		atributo = recebeEntrada();
		
		System.out.println("Digite o Tipo de Ordenacao: ");
		tipoOrdenacao = recebeEntrada();
		
		System.out.println("Digite o Criterio de Ordenacao: ");
		criterioOrdenacao = recebeEntrada();
		
		String resposta;
		try {
			resposta = rede.pesquisarItem(idSessao, chave, atributo, tipoOrdenacao, criterioOrdenacao);
			System.out.println("ITENS PESQUISADOS: \n");
			System.out.println(resposta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//--------------------APAGA UM ITEM DO USUARIO---------------------------------------
	private static void apagarItem(){
		System.out.print("Digite o ID do Item a ser apagado: ");
		String idItem = recebeEntrada();
		
		try {
			rede.apagarItem(idSessao, idItem);
			System.out.println("Item Apagado com Sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//------------------------DESFAZ A AMIZADE COM OUTRO USUARIO-----------------------------
	private static void desfazerAmizade(){
		
		System.out.print("Digite o login do Amigo: ");
		String loginAmigo = recebeEntrada();
		
		try {
			rede.desfazerAmizade(idSessao, loginAmigo);
			System.out.println("Amizade desfeita com sucesso!!!\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//-----------------------#IMPRIMI MENU Usuario#----------------------------
	
	private static void opcoesDoUsuario(){
		System.out
				.print("O que deseja fazer?\n "
						+ "1) Cadastrar novo item\n 2) Localizar Usuario\n 3) Visualizar Meu Perfil\n 4) Adicionar Amigo\n 5)Enviar Mensagens\n 6)Ler Mensagens\n 7)Visualizar Perfil dos Amigos\n 8)Fazer Emprestimo\n 9)Ver Ranking dos Usuarios \n 10)Aprovar Emprestimo\n 11)Aprovar Amizade\n 12)Requisitar Devolucao\n 13)Registrar Interesse em Itens\n 14)Pesquisa de Itens \n 15)Apagar Itemm\n 16)Desfazer Amizade\n 17)Historico Atividades\n 18)Historico Atividades Conjunto\n 19)Publicar Pedido\n 20)Oferecer Item\n 21)Deslogar");
	}
	
	
}
