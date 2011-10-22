package Classes;

import java.util.List;
import java.util.Random;


/**
 * Esta Classe representa um Usuario da Rede Social
 * 
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0
 * 
 */

public class Usuario {
	
	private String nome;
	private String login;
	private String end;
	private String ID;
	
	private GerenciadorAmizades gerenciaAmizade;
	private GerenciadorMensagens gerenciaMensagens;
	private GerenciadorItens gerenciaItens;
	
	private AtividadesUsuario atividades;


	/**
	 * Inicia os Atributos da Classe
	 * @param nome 
	 *           Nome do Usuario
	 * @param login 
	 *           Login do Usuario
	 * @param senha 
	 *           Senha do Usuario
	 * @param end
	 *           Endereco do Usuario
	 * @throws Exception
	 *           Lanca excecao quando qualquer um dos parametros for Invalido
	 */
	
	public Usuario(String nome, String login, String end)throws Exception{
		if (!stringValida(nome)){
			throw new Exception("Nome inválido");
		}
		else if (!stringValida(login)){
			throw new Exception("Login inválido");
		}
		else if(!stringValida(end)){
			throw new Exception("Endereco inválido");
		}
		
		this.nome = nome;
		this.login = login;
		this.end = end;
		this.ID = gerarID();
		gerenciaAmizade = new GerenciadorAmizades();
		gerenciaItens = new GerenciadorItens();
		gerenciaMensagens = new GerenciadorMensagens();
	}
	
	/**
	 * Recupera o Perfil do Usuario
	 * @return
	 *        Perfil do Usuario
	 */
	public String visualizarPerfil(){
		return nome + " - " + this.end;
	}
	
	/**
	 * Recupera o ID do Usuario
	 * @return
	 *         ID do Usuario
	 */
	public String getID(){
		return ID;
	}
	
	
	private String gerarID(){
		this.ID = getNome() + "-" + (new Random()).nextInt(1000);
		return this.ID;
	}
	
	
	/**
	 * Recupera o Endereco do Usuario
	 * @return 
	 *        Endereco do Usuario
	 */
	
	public String getEndereco(){
		return end;
	}

	/**
	 * Recupera o Login
	 * @return Login do Usuario
	 */
	
	public String getLogin() {
		return login;
	}
	
	/**
	 * Recupera o Nome
	 * @return Nome do Usuario
	 */
	
	public String getNome() {
		return nome;
	}
		
	/**
	 * Recupera o Gerenciador de Amizades do Usuario
	 * @return 
	 *      O gerenciador de amizades do usuario
	 */
	
	public GerenciadorAmizades getGerenciadorAmizades(){
		return gerenciaAmizade;
	}
	
	/**
	 * Recupera o Gerenciador de itens do Usuario
	 * @return 
	 *      O gerenciador de itens do usuario
	 */
	
	public GerenciadorItens getGerenciadorItens(){
		return gerenciaItens;
	}
	
	/**
	 * Recupera o Gerenciador de Mensagens do Usuario
	 * @return 
	 *      O gerenciador de mensagens do usuario
	 */
	
	public GerenciadorMensagens getGerenciadorMensagens(){
		return gerenciaMensagens;
	}
	
	/**
	 * Diz se um Usuario eh igual ou nao a outro Usuario
	 * @param usr
	 *        Usuario a ser comparado
	 * @return
	 *        True, caso os Usuarios sejam iguais
	 *        False, caso contrario
	 */
	public boolean equals(Usuario usr){
		if (!(usr instanceof Usuario)){
			return false;
		}
		
		else if(usr.getLogin().equals(this.login) && usr.getEndereco().equals(this.getEndereco()) && usr.getNome().equals(this.getNome())){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	private boolean stringValida(String string){
        if (string == null || string.isEmpty()){
            return false;
        }
        return true;
    }
	
	
	/**
	 * Recupera a Reputacao do Usuario, ou seja, um numero que
	 * representa o numero de emprestimos completados pelo Usuario
	 * @return
	 *        Reputacao do Usuario
	 */
	public int getReputacao(){
		return getGerenciadorItens().quantEmprestimosCompletados();
	}
	
	public void criarHistoricoAtividades(){
		atividades = new AtividadesUsuario();
	}
	
	public List<Atividade> getHistoricoAtividades(){
//		if(atividades.naoTemAtividades()){
//			return "Não há atividades";
//		}
		return atividades.getAtividades();
	}
	
	public String getDataCriacaoAtividades(){
		return atividades.getCriacaoAtividades();
	}
	
	public void addAtividade(Atividade atividade){
		atividades.adicionarAtividades(atividade);
	}
	
	
	
}