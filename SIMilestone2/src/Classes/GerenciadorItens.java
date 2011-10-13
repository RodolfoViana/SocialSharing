package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que Gerencia os Itens do Usuario
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENNAN PINTO, IGOR GOMES
 *
 */
public class GerenciadorItens {
	
	private List<Item> listaMeusItens;
	private List<Item> itensPraEmprestar;
	private List<Item> itensPraDevolver;
	private List<Emprestimo> listaDeEmprestimosCompletados;
	
	/**
	 * Inicia os Atributos da Classe
	 */
	
	public GerenciadorItens(){
		listaMeusItens = new ArrayList<Item>();
		itensPraDevolver = new ArrayList<Item>();
		itensPraEmprestar = new ArrayList<Item>();
		listaDeEmprestimosCompletados = new ArrayList<Emprestimo>();
	}
	
	/**
	 * Adiciona um Item 
	 * @param it
	 *         Item a ser adicionado
	 * @return
	 *        ID do item
	 * @throws Exception
	 *        Caso o Item seja nulo
	 */
	public String adicionarItem(Item it)throws Exception{
		if (it==null){
			throw new Exception("Item nao pode ser igual a null");
		}
		listaMeusItens.add(it);
		itensPraEmprestar.add(it);
		return it.getID();
	}

	/**
	 * Recupera uma lista com todos os itens do Usuario
	 * @return
	 *        lista com todos os itens do Usuario
	 */
	public List<Item> getListaMeusItens() {
		return listaMeusItens;
	}
	

	/**
	 * Recupera uma lista com todos itens que podem ser emprestados
	 * @return
	 *         Uma lista com todos itens que podem ser emprestados
	 */
	public List<Item> getItensPraEmprestar() {
		return itensPraEmprestar;
	}
	
	/**
	 * Recupera a quantidade de Emprestimos Completados
	 * @return
	 *       Quantidade de Emprestimos Completados
	 */
	
	public int quantEmprestimosCompletados(){
		return this.listaDeEmprestimosCompletados.size();
	}

	/**
	 * Recupera os Itens que precisam ser devolvidos
	 * @return
	 *          Itens que precisam ser devolvidos
	 */
	
	public List<Item> getItensPraDevolver() {
		return itensPraDevolver;
	}


	
	/**
	 * Recupera um determinado Atributo do Item
	 * @param idItem
	 *        ID do Item
	 * @param atributo
	 *        Atributo do item
	 * @return
	 *        Atributo do Item
	 * @throws Exception
	 *         Caso Atributo seja inexistente ou Invalido
	 */
	public String getAtributoItem(String idItem, String atributo)throws Exception{
		if(!stringValida(atributo)){
            throw new Exception("Atributo inválido");
		}
		else if(atributo.equals("nome")){
            return buscarItemPorID(idItem).getNome();
		}
		else if(atributo.equals("descricao")){
            return buscarItemPorID(idItem).getDescricao();
		}
		else if(atributo.equals("categoria")){
            return buscarItemPorID(idItem).getCategoria();
		}
		else{
            throw new Exception("Atributo inexistente");
        }
	}
	
	public String stringDeItens(){
		if (getListaMeusItens().isEmpty()){
			 return "O usuário não possui itens cadastrados";
		}
		String resp = "";
		
		for (Item item : getListaMeusItens()){
			resp += item.getNome() + "; ";
		}
		
		
		return formatarRequisicoes(resp);
	}
	
	//TODO depois arrumar isso
	private String formatarRequisicoes(String requisicoes){
		String retorno = "";
		for (int i =0;i< requisicoes.split("; ").length;i++){
			if (i==requisicoes.split("; ").length-1){
				retorno +=requisicoes.split("; ")[i];
				break;
			}

			if (retorno.contains("; ")){
				retorno += requisicoes.split("; ")[i];
			}
			else{
				retorno += requisicoes.split("; ")[i] + "; ";

			}
			
			
		}return retorno;
	}
	
	/**
	 * Recupera a Quantidade de Itens do Usuario
	 * @return
	 *         Quantidade de Itens do Usuario
	 */
	
	public int getQuantidadeMeusItens(){
		return listaMeusItens.size();
	}
	
	/**
	 * Recupera os Emrpestimos do usuario
	 * @param usr1
	 *          Usuario participante do Emprestimo
	 * @param usr
	 * 			Usuario participante do Emprestimo
	 * @param tipo
	 * 		O parâmetro tipo representa o papel exercido pelo usuário no empréstimo
	 * @return
	 * 	   Recupera os Emprestimos do Usuario baseado
	 * @throws Exception
	 *         Caso alguns dos parametros seja invalido
	 * 
	 */
	public String getEmprestimo(Usuario usr1, Usuario usr, String tipo)throws Exception{
		if (!stringValida(tipo)){
			throw new Exception("Tipo inválido");
		}
		else if (tipo.equals("beneficiado")){
			String retorno = "";
			if (usr1==null){
				
				return ("Não há empréstimos deste tipo");
			}
			for (Item it: usr1.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().isRequisitarDevolucao() && (!it.getEmprestimo().isDevolucao()) && it.getEmprestimo().getBeneficiado().equals(usr)){
					retorno += usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return formatarRequisicoes(retorno);
		}
		else if(tipo.equals("emprestador")){
			String retorno = "";
			for (Item it: listaMeusItens){
				if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().isRequisitarDevolucao() && (!it.getEmprestimo().isDevolucao())){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return formatarRequisicoes(retorno);
		}
		else if(tipo.equals("todos")){
			String retorno = "";
			
			int cont = 0;
			for (Item it: listaMeusItens){
				if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().isRequisitarDevolucao()){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
					cont++;
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().tempoEmprestimoNaoExpiro() && (it.getEmprestimo().isDevolucao())){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
					cont++;
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().isRequisitarDevolucao() && it.getEmprestimo().tempoEmprestimoNaoExpiro()){
					retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Cancelado; ";
					cont++;
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().isRequisitarDevolucao() && (!it.getEmprestimo().isDevolucao())){
					if (cont==0){
						retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
					else{
						retorno += "; "+usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
				}
				
				else if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() && !it.getEmprestimo().tempoEmprestimoNaoExpiro() && (!it.getEmprestimo().isDevolucao())){
					if (cont==0){
						retorno += usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
					else{
						retorno += "; "+usr.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
						cont++;
					}
				}
			}
			if (usr1!=null){
				for (Item it: usr1.getGerenciadorItens().getListaMeusItens()){
					if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().isRequisitarDevolucao()){
						retorno += usr1.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
						cont++;
					}
					else if (it.getEmprestimo()!=null && it.getEmprestimo().isDevolucao() && !it.getEmprestimo().tempoEmprestimoNaoExpiro() && (it.getEmprestimo().isDevolucao())){
						retorno += usr1.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Completado; ";
						cont++;
					}
					
					else if (it.getEmprestimo()!=null && it.getEmprestimo().isRequisitarDevolucao() && it.getEmprestimo().tempoEmprestimoNaoExpiro()){
						retorno += usr1.getLogin()+ "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Cancelado; ";
						cont++;
					}
					
					else if (it.getEmprestimo()!=null && it.getEmprestimo().emprestimoFoiAprovado() /*&& !it.getEmprestimo().isRequisitarDevolucao()*/ && (!it.getEmprestimo().isDevolucao()) && it.getEmprestimo().getBeneficiado().equals(usr)){
						if (cont==0){
							retorno += usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
							cont++;
						}
						else{
							retorno += "; "+usr1.getLogin() + "-" + it.getEmprestimo().getBeneficiado().getLogin() + ":" + it.getNome() + ":Andamento; ";
							cont++;
						}
					}
				}
			}
			if (retorno.isEmpty()){
				return ("Não há empréstimos deste tipo");
			}
			return formatarRequisicoes(retorno);
		}
		else{
			throw new Exception("Tipo inexistente");
		}
	}
	
	/**
	 * Requisita um Emprestimo
	 * @param beneficiado
	 *           Usuario Beneficiado pelo Emprestimo
	 * @param idItem
	 *           ID do Item que sera requisitado
	 * @param dias
	 *           Dias que o Beneficiado passara com o Item
	 * @return
	 *          ID de requisicao de Emprestimo
	 * @throws Exception
	 *          Caso algum dos Parametros seja invalido
	 */
	
	public String requisitarEmprestimos(Usuario beneficiado,String idItem, int dias) throws Exception{
		if (buscarItemPorID(idItem).getEmprestimo()!=null && buscarItemPorID(idItem).getEmprestimo().getBeneficiado().equals(beneficiado) && buscarItemPorID(idItem).getEmprestimo().emprestimoFoiRequisitado()){
			throw new Exception("Requisição já solicitada");
		}

		return buscarItemPorID(idItem).criarRequisicaoEmprestimo(beneficiado, dias);
	}
	
	/**
	 * Aprova Um Emprestimo
	 * @param ehDonoDoItem
	 *           Diz se eh o Dono que esta aprovando o Emprestimo
	 * @param usuarioSaoAmigos
	 *           Diz se os Usuarios sao amigos
	 * @param requisicaoExiste
	 *           Diz se a requisicao Existe
	 * @param idRequisicaoEmprestimo
	 *           ID de Requisicao que sera efetivada como Emprestimo
	 * @return
	 *          ID do Emprestimo
	 * @throws Exception
	 *           Caso algums dos Parametros seja Invalido
	 */
	
	public String aprovarRequisicaoEmprestimo(boolean ehDonoDoItem, boolean usuarioSaoAmigos, boolean requisicaoExiste, String idRequisicaoEmprestimo) throws Exception{
		if (!stringValida(idRequisicaoEmprestimo)){
			throw new Exception("Identificador da requisição de empréstimo é inválido");
		}
		else if (!requisicaoExiste){
			throw new Exception("Requisição de empréstimo inexistente");
		}

		for (Item it: listaMeusItens){
			if (it.getEmprestimo()!=null && it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo) && !it.getEmprestimo().emprestimoFoiAprovado()){
				this.getItensPraEmprestar().remove(it);
				return it.getEmprestimo().aprovarEmprestimo();
			}
			else if(it.getEmprestimo()!=null && it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo) && it.getEmprestimo().emprestimoFoiAprovado()){
				throw new Exception("Empréstimo já aprovado");
			}	
		}
		
		if (!requisicaoExiste){
			throw new Exception("Requisição de empréstimo inexistente");
		}
		
			throw new Exception("O empréstimo só pode ser aprovado pelo dono do item");
		
	}
	
	private boolean stringValida(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Item buscarItemPorID(String id) {
		for (Item it : getListaMeusItens()) {
			if (it.getID().equals(id)) {
				return it;
			}
		}
		return null;
	}
	
	/**
	 * Busca um Item que esta Emprestado
	 * @param idEmpretimo
	 *            ID do Emprestimo do Item
	 * @return
	 *          Item emprestado
	 */
	
	public Item buscarItemIdEmprestimo(String idEmpretimo){
		
		for (Item it : getListaMeusItens()){
			if (it.getEmprestimo() != null){
				if (it.getEmprestimo().getIDEmprestimo().equals(idEmpretimo)){
					return it;
				}
			}
			
		}
		
		return null;
		
	}
	
	
	/**
	 * Confirma o Termino do Emprestimo
	 * @param item
	 *            Item cujo emprestimo foi terminado
	 */
	
	public void confirmarTerminoEmprestimo(Item item){
		this.getItensPraEmprestar().add(item);	
	}

	
	/**
	 * Metodo pra simular a Passagem do Tempo no sistema
	 * @param dias
	 *         Dias passados
	 */
	public void incrementarDias(int dias){
		for (Item it: listaMeusItens){
			if (it.getEmprestimo()!=null){
				it.getEmprestimo().adicionarDias(dias);
			}
		}
	}
	
	/**
	 * Busca um Item do Usuario
	 * @param chave
	 * 			O parâmetro chave é a String a ser localizada no espaço de um atributo especificado.
	 * @param atributo
	 *          Atributo a ser buscado
	 * @param tipoOrdenacao
	 *          Tipo de Ordenação que deve ser feita
	 * @param criterioOrdenacao
	 *          O criterio de ordena que deve ser feito
	 * @return
	 *          Itens encontrados
	 */
	public String buscarItemCadastrado(String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao){
		String resp = "";
		
		for (Item item : this.getListaMeusItens()){
			if (!item.pesquisa(chave, atributo).equals("")){
				resp += item.pesquisa(chave, atributo);
			}
		}
		
		return resp;
	}
	
	/**
	 * Apaga um Item do Usuario
	 * @param item
	 *        Item a ser apagado
	 * @throws Exception
	 *        Caso o Item nao pertenca ao Usuario
	 *        Caso o Item ja esteja emprestado
	 */
	public void apagarItem(Item item) throws Exception{
		if(!getListaMeusItens().contains(item)){
			throw new Exception("O usuário não tem permissão para apagar este item");
		}
		else if (this.getListaMeusItens().contains(item) && !this.getItensPraEmprestar().contains(item)){
			throw new Exception ("O usuário não pode apagar este item enquanto estiver emprestado");
			
		} 
		else {
			this.getListaMeusItens().remove(item);
			this.getItensPraEmprestar().remove(item);
		}
	}
	
	/**
	 * Adciona um Emprestimo Completado
	 * @param emp
	 *         Emprestimo que foi completado
	 */
	public void addEmprestimoCompletado(Emprestimo emp){
		this.listaDeEmprestimosCompletados.add(emp);
	}

}
