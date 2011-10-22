package Classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsavel por Gerenciar os Usuarios do Sistema
 * @author ARTHUR SENA, RODOLFO DE LIMA, IGOR GOMES, RENNAN PINTO
 */

public class GerenciadorUsuarios {

	List<Usuario> listaDeUsuarios;
	List<Usuario> listaDeUsuariosLogados;
	List<PedidoItem> listaPedidos; 
	
	/**
	 * Inicia os Atributos da Classe
	 */
	public GerenciadorUsuarios() {
		listaDeUsuarios = new ArrayList<Usuario>();
		listaDeUsuariosLogados = new ArrayList<Usuario>();
		listaPedidos= new ArrayList<PedidoItem>();
	}
	
	/**
	 * Lista de Pedidos
	 * @return Recupera a lita de pedidos
	 */
	public List<PedidoItem> getPedidos(){
		return listaPedidos;
	}
	
	/**
	 * Recupera a Lista de Usuarios cadastrados no Sistema
	 * @return
	 *       Lista de Usuarios cadastrados no Sistema
	 */
	public List<Usuario> getListaUsuarios(){
		return listaDeUsuarios;
	}
	
	/**
	 * Recupera a Lista de Usuarios logados no Sistema
	 * @return
	 *        Lista de Usuarios logados no Sistema
	 */
	public List<Usuario> getListaUsuariosLogados(){
		return listaDeUsuariosLogados;
	}
	
	/**
	 * Recupera um deteminado atributos do Usuario
	 * @param login
	 *          Login do Usuario
	 * @param atributo
	 *          Atributo do Usuario
	 * @return
	 * 			Atributo a ser retornado
	 * @throws Exception
	 *         Caso login esteja vazio ou seja inexistente
	 *         Caso Atributo esteja vazio ou seja inexistente
	 */
	 public String getAtributoUsuario(String login, String atributo)throws Exception{
         if (!stringValida(login)){
                 throw new Exception("Login inválido");
         }
         else if(!stringValida(atributo)){
                 throw new Exception("Atributo inválido");
         }
         else if (!loginEhUsado(login)){
             throw new Exception("Usuário inexistente");
         }
         Usuario usr = buscarUsuarioPorLogin(login);
         
         
         if(atributo.equals("nome")){
                 return usr.getNome();
         }
         
         else if(atributo.equals("endereco")){
                 return usr.getEndereco();
         }
         
         else if(atributo.equals("login")){
                 return usr.getLogin();
         }
         else{
                 throw new Exception("Atributo inexistente");
         }
	 }
	
	 /**
	  * Cria e Cadastra um Usuario no Sistema
	  * @param login
	  *        Login do Usuario
	  * @param nome
	  *        Nome do Usuario
	  * @param endereco
	  *        Endereco do Usuario
	  * @throws Exception
	  *        Caso login ou nome ou endereco estejam vazios.
	  *        Caso login ja seja usado.
	  */
	public void criarUsuario(String login, String nome, String endereco)throws Exception{
        Usuario usr = new Usuario(nome, login, endereco);
        if (loginEhUsado(login)){
                throw new Exception("Já existe um usuário com este login");
        }
        
        listaDeUsuarios.add(usr);
}

	/**
	 * Localiza um Usuario no Sistema
	 * @param idSessao
	 *         ID da Sessao do Usuario no sistema
	 * @param chave
	 *         
	 * @param atributo
	 * @return
	 *      Usuario 
	 * @throws Exception
	 */
	public String localizarUsuario(String idSessao, String chave, String atributo) throws Exception {

		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		} else if (buscarUsuarioPorID(idSessao) == null) {
			throw new Exception("Sessão inexistente");
		} else if (!stringValida(chave)) {
			throw new Exception("Palavra-chave inválida");
		} else if (!stringValida(atributo)) {
			throw new Exception("Atributo inválido");
		}

		else if (atributo.equals("nome")) {
			return buscarPerfisDeUsuarios(idSessao, chave, atributo);
		} else if (atributo.equals("endereco")) {
			return buscarPerfisDeUsuarios(idSessao, chave, atributo);
		} else {
			throw new Exception("Atributo inexistente");
		}
	}
	
	/**
	 * Localiza usuario recebendo o id como parametro
	 * @param id ID do usuario
	 * @return String Usuario
	 * @throws Exception Caso a sessao seja invalida ou inexistente
	 */
	public String localizarUsuario (String id) throws Exception{
		if (!stringValida(id)) {
			throw new Exception("Sessão inválida");
		} else if (buscarUsuarioPorID(id) == null) {
			throw new Exception("Sessão inexistente");
		}
		
		List<Usuario> listUsuarios = new ArrayList<Usuario>();
		for (int i = 0; i<=listaDeUsuarios.size() - 1; i++) {
			Usuario usr = listaDeUsuarios.get(i);
			if(!listUsuarios.contains(usr) && !usr.equals(buscarUsuarioPorID(id))){
				listUsuarios.add(usr);
			}
		}
		if (listUsuarios.isEmpty()) {
			return "Nenhum usuário encontrado";
		}
		
		GeocodificaEnderecos geoc = new GeocodificaEnderecos();
		
		double distancia;
		Usuario usrLogado = null;
		List<Double> listaDistancia = new ArrayList<Double>();
		try {
			usrLogado = this.buscarUsuarioPorID(id);
		} catch (Exception e) {
		}
		
		for(Usuario usr:listUsuarios){
			distancia = geoc.calculaDistancia(usrLogado, usr);
			listaDistancia.add(distancia);
		}
		
		Usuario[] listaUsuariosOrdenadoDistancia = new Usuario[listUsuarios.size()];
		Usuario[] x = new Usuario[listaDistancia.size()];
		int cont2 = 0;
		
		for(int i = 0; i<x.length;i++){
			int indice = recuperaIndiceDoMenorDaLista(listaDistancia);
			listaDistancia.remove(indice);
			listaUsuariosOrdenadoDistancia[cont2]=(listUsuarios.remove(indice));
			cont2++;

		}
		String resposta = "";
		for(int i = 0 ;i <listaUsuariosOrdenadoDistancia.length;i++){
			if(i==listaUsuariosOrdenadoDistancia.length-1){
				resposta +=listaUsuariosOrdenadoDistancia[i].visualizarPerfil();
			}
			else{
				resposta +=listaUsuariosOrdenadoDistancia[i].visualizarPerfil() + "; ";
			}
		}
		return resposta;
	}
	
	/**
	 * Busca usuario por destinatario
	 * @param destinatario Login do destinatario
	 * @return Usuario destinatario
	 * @throws Exception Caso seja um usuario invalido
	 */
	public Usuario buscarUsuarioDestinatario(String destinatario) throws Exception{
		if (!stringValida(destinatario)){
			throw new Exception ("Destinatário inválido");
		}
		
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getLogin().equals(destinatario)) {
				return usr;
			}
		}
		throw new Exception("Destinatário inexistente");
	}
	
	/**
	 * Busca um Usuario atraves do Login do mesmo
	 * @param login
	 *           Login do Usuario
	 * @return
	 *          Usuario encontrao
	 * @throws Exception
	 * 			Caso o Login seja Invalido ou inexistente
	 */
	public Usuario buscarUsuarioPorLogin(String login) throws Exception {
		if (!stringValida(login)) {
			throw new Exception("Login inválido");
		}else if (!loginEhUsado(login)) {
			throw new Exception("Usuário inexistente");
		}
		
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getLogin().equals(login)) {
				return usr;
			}
		}
		
		throw new Exception("Login inexistente");
		
	}

	private boolean stringValida(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Diz se o Login ja eh ultilizado por outro Usuario
	 * @param login
	 *         Login do Usuario
	 * @return
	 *       True, caso afirmativo
	 *       False, caso negativo
	 * @throws Exception
	 *      Caso o loing seja invalido
	 */
	public boolean loginEhUsado(String login) throws Exception {
		if (!stringValida(login)) {
			throw new Exception("Login inválido");
		}
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}

	private String buscarPerfisDeUsuarios(String id, String chave,
			String atributo) throws IOException {
		String listaUsuarios = "";
		List<Usuario> listUsuarios = new ArrayList<Usuario>();
		int cont = 0;
		for (int i = 0; i<=listaDeUsuarios.size() - 1; i++) {
			Usuario usr = listaDeUsuarios.get(i);
			if (!(usr.getID().equals(id))) {
				if (atributo.equals("nome")
						&& usr.getNome().toLowerCase()
								.contains(chave.toLowerCase())) {
					if (cont == 0) {
						listaUsuarios += usr.visualizarPerfil();
						listUsuarios.add(usr);
					} else {
						listaUsuarios += "; " +usr.visualizarPerfil() ;

					}
					cont++;
					if(!listUsuarios.contains(usr)){
						listUsuarios.add(usr);
					}
				} else if (atributo.equals("endereco")
						&& usr.getEndereco().toLowerCase()
								.contains(chave.toLowerCase())) {
					if (cont == 0) {
						listaUsuarios += usr.visualizarPerfil();
						
					} else {
						listaUsuarios += "; " + usr.visualizarPerfil();
					}
					cont++;
					if(!listUsuarios.contains(usr)){
						listUsuarios.add(usr);
					}
				}
			}
		}
		if (listaUsuarios.equals("")) {
			return "Nenhum usuário encontrado";
		}

		GeocodificaEnderecos geoc = new GeocodificaEnderecos();
		
		double distancia;
		Usuario usrLogado = null;
		List<Double> listaDistancia = new ArrayList<Double>();
		try {
			usrLogado = this.buscarUsuarioPorID(id);
		} catch (Exception e) {
		}
		
		for(Usuario usr:listUsuarios){
			distancia = geoc.calculaDistancia(usrLogado, usr);
			listaDistancia.add(distancia);
		}
		
		
		Usuario[] listaUsuariosOrdenadoDistancia = new Usuario[listUsuarios.size()];
		Usuario[] x = new Usuario[listaDistancia.size()];
		int cont2 = 0;
		
	
		
		for(int i = 0; i<x.length;i++){
			int indice = recuperaIndiceDoMenorDaLista(listaDistancia);
			listaDistancia.remove(indice);
			listaUsuariosOrdenadoDistancia[cont2]=(listUsuarios.remove(indice));
			cont2++;

		}

		String resposta = "";
		
		for(int i = 0 ;i <listaUsuariosOrdenadoDistancia.length;i++){
			if(i==listaUsuariosOrdenadoDistancia.length-1){
				resposta +=listaUsuariosOrdenadoDistancia[i].visualizarPerfil();
			}
			else{
				resposta +=listaUsuariosOrdenadoDistancia[i].visualizarPerfil() + "; ";
			}
		}
		return resposta;
	}
	
	private int recuperaIndiceDoMenorDaLista(List<Double> lista){
		int indice = 0;
		double menorDalista = Double.MAX_VALUE;
		for(int i = 0  ; i< lista.size();i++){
			if(lista.get(i)<menorDalista){
				indice = i;
				menorDalista = lista.get(i);
			}
		}return indice;
		
	}
	
	/**
	 * Busca um Usuario atraves do ID de Sessao do mesmo
	 * @param idSessao
	 *         ID de Sessao do Usuario
	 * @return
	 *        Usuario 
	 * @throws Exception
	 *        Caso o ID de sessao seja invalido
	 */
	public Usuario buscarUsuarioPorID(String idSessao) throws Exception {
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		for (Usuario usr : listaDeUsuariosLogados) {
			if (usr.getID().equals(idSessao)) {
				return usr;
			}
		}
		throw new Exception("Sessão inexistente");
	}
	
	/**
	 * Recupera a Quantidade de Usuarios logados no sistema
	 * @return
	 *        Quantidade de Usuarios logados no sistema
	 */
	public int itensDosUsuariosLogados(){
		int cont = 0;
		for (Usuario usr: listaDeUsuariosLogados){
			cont+= (usr.getGerenciadorItens().getQuantidadeMeusItens());
		}return cont;
	}
	
	/**
	 * Recupera o Dono do item
	 * @param idItem
	 *          ID do Item
	 * @return
	 *         Usuario dono do Item
	 * @throws Exception
	 *         Caso o ID do Item seja invalido
	 */
	public Usuario buscarDonoItem(String idItem) throws Exception {
		if (!stringValida(idItem)) {
			throw new Exception("Identificador do item é inválido");
		}
		for (Usuario usr : listaDeUsuariosLogados) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.getID().equals(idItem)) {
					return usr;
				}
			}
		}
		throw new Exception("Item inexistente");
	}
	
	/**
	 * Recupera o dono do Item
	 * @param item
	 *        Item 
	 * @return
	 *       Usuario dono do Item
	 * @throws Exception
	 *        caso o Item seja inexistente
	 */
	public Usuario buscarDonoItem(Item item) throws Exception {
		
		for (Usuario usr : listaDeUsuariosLogados) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.equals(item)) {
					return usr;
				}
			}
		}
		throw new Exception("Item inexistente");
	}
	
	/**
	 * Recupera um Item atraves do ID do mesmo
	 * @param idItem
	 *          ID do Item
	 * @return
	 *          Item encontrado
	 * @throws Exception
	 *          Caso o parametro seja invalido
	 */
	public Item buscarItemPorID(String idItem) throws Exception{
		if (!stringValida(idItem)) {
			throw new Exception("Identificador do item é inválido");
		}
		for (Usuario usr : listaDeUsuariosLogados) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.getID().equals(idItem)) {
					return it;
				}
			}
		}
		throw new Exception("Item inexistente");
	}
	
	/**
	 * Recupera os emprestimos
	 * @param idSessao Sessao do usuario
	 * @param tipo Informa o tipo de emprestimo
	 * @return Usuario 
	 * @throws Exception Caso a sessaos seja invalida ou o tipo
	 */
	public String getEmprestimos(String idSessao, String tipo) throws Exception {	
		
		return buscarUsuarioPorID(idSessao).getGerenciadorItens().getEmprestimo(buscarUsuarioEmprestador(idSessao),
						buscarUsuarioPorID(idSessao), tipo);
	}
	
	/**
	 * Busca o usuario apartir do id de sessao
	 * @param idSessao ID de sessao do usuario
	 * @return Usuario 
	 * @throws Exception Caso a ID de sessao seja invalida
	 */
	public Usuario buscarUsuarioEmprestador(String idSessao) throws Exception{
		
		for (Usuario usr: listaDeUsuarios){
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getBeneficiado().equals(buscarUsuarioPorID(idSessao))){
						
						return usr;
					}
				}
			}
		}return null;
	}
	
	
	public Usuario buscarUsuarioBeneficiador(String idEmprestimo) throws Exception{
		Usuario usuario = null;
		
		if (!stringValida(idEmprestimo)){
			throw new Exception("Identificador do empréstimo é inválido");
		}
		
		for (Usuario usr: listaDeUsuarios){
			
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDEmprestimo().equals(idEmprestimo)){
						usuario = usr;
						break;
					}
				}
			}
		}
		
		if (usuario == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		return usuario;
	}
	
	public Usuario buscarUsuarioPorRequisicaoEmprestimo(String idRequisicaoEmprestimo) throws Exception{
		
		if (!stringValida(idRequisicaoEmprestimo)){
			throw new Exception("Identificador da requisição de empréstimo é inválido");
		}
		
		Usuario usuario = null;
		
		for (Usuario usr: listaDeUsuarios){
			
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo)){
						usuario = usr;
						break;
					}
				}
			}
		}
		
		if (usuario == null){
			throw new Exception("Requisição de empréstimo inexistente");
		}
		
		return usuario;
	}
	
	public Item buscarItemEmprestador(String idEmprestimo) throws Exception{
		Usuario usr = buscarUsuarioBeneficiador(idEmprestimo);
		
		if (usr != null){
			usr.getGerenciadorItens().confirmarTerminoEmprestimo(buscarItemEmprestador(idEmprestimo));
		}
		
		
		return null;
		
		
	}
	
	public Item buscarItemIdEmprestimo(String idEmpretimo) throws Exception{
		Item item = null;
		
		for (Usuario usr: listaDeUsuarios){
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDEmprestimo().equals(idEmpretimo)){
						item = it;
						break;
					}	
				}
			
			}
		}
		
		if (item == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		return item;
		
	}
	
	public Usuario buscarUsuarioIdEmprestimo(String idRequisicaoEmprestimo){
		for (Usuario usr : listaDeUsuarios) {
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()) {
				if (it.getEmprestimo().getIDRequisicao()
						.equals(idRequisicaoEmprestimo)) {
					return it.getEmprestimo().getBeneficiado();
				}
			}
		}
		return null;
	}
	
	public boolean requisicaoEmprestimoExiste(String idRequisicaoEmprestimo) throws Exception{
		for (Usuario usr: listaDeUsuarios){
			for (Item it : usr.getGerenciadorItens().getListaMeusItens()){
				if (it.getEmprestimo() != null){
					if (it.getEmprestimo().getIDRequisicao().equals(idRequisicaoEmprestimo)){
						return true;
					}
				}
			}
		}return false;
	}
	
	public Usuario buscarDestinatario(String idTopico){
		for (Usuario usr: listaDeUsuarios){
			for (Mensagem msg : usr.getGerenciadorMensagens().getListaDeMensagens()){
				if (msg.getIdMensagem().equals(idTopico) && msg.getDestinatario().equals(usr)){
					return msg.getDestinatario();
				}
			}
		}return null;
	}
	
	public Usuario buscarRemetente(String idTopico){
		for (Usuario usr: listaDeUsuarios){
			for (Mensagem msg : usr.getGerenciadorMensagens().getListaDeMensagens()){
				if (msg.getIdMensagem().equals(idTopico) && !msg.getDestinatario().equals(usr)){
					return usr;
				}
			}
		}return null;
	}
	
	public boolean msgExiste(String idTopico){
		for (Usuario usr: listaDeUsuarios){
			for (Mensagem msg : usr.getGerenciadorMensagens().getListaDeMensagens()){
				if (msg.getIdMensagem().equals(idTopico)){
					return true;
				}
			}
		}return false;
	}
	
	public void simularPassagemDoTempo(int dias){
		for (Usuario usr: listaDeUsuarios){
			usr.getGerenciadorItens().incrementarDias(dias);
		}
	}
	public void desfazerAmizade(Usuario usuario, Usuario usuario2) throws Exception{
		if (!usuario.getGerenciadorAmizades().ehMeuAmigo(usuario2)){
			throw new Exception("Amizade inexistente");
		}
		usuario.getGerenciadorAmizades().desfazerAmizade(usuario2);
		usuario2.getGerenciadorAmizades().desfazerAmizade(usuario);
		
		for(Item it : usuario.getGerenciadorItens().getListaMeusItens()){
			if(it.getEmprestimo()!=null && it.getEmprestimo().getBeneficiado().equals(usuario2) && !it.getEmprestimo().emprestimoFoiAprovado()){
				it.acabarEmprestimo();
			}
		}
		
		for(Item it : usuario2.getGerenciadorItens().getListaMeusItens()){
			if(it.getEmprestimo()!=null && it.getEmprestimo().getBeneficiado().equals(usuario) && !it.getEmprestimo().emprestimoFoiAprovado()){
				it.acabarEmprestimo();
			}
		}
	}
	
	public String getRanking(String idSessao, String categoria) throws Exception{
		buscarUsuarioPorID(idSessao);
		String retorno = "";
		if(!stringValida(categoria)){
			throw new Exception("Categoria inválida");
		}
		else if(categoria.equals("amigos")){
			List<Usuario> listaUsuarioReputacao = new ArrayList<Usuario>();
			listaUsuarioReputacao.add(buscarUsuarioPorID(idSessao));
			for(Usuario usuarios: listaDeUsuarios){
				if(buscarUsuarioPorID(idSessao).getGerenciadorAmizades().ehMeuAmigo(usuarios)){
					listaUsuarioReputacao.add(usuarios);
				}
			}
			
			while(!listaUsuarioReputacao.isEmpty()){
				Usuario usr = usuarioComMaisAltaReputacaoDaLista(listaUsuarioReputacao);
				retorno+=usr.getLogin() + "; ";
				listaUsuarioReputacao.remove(usr);
			}
			

			return formatarRequisicoes(retorno);
		}
		else if(categoria.equals("global")){
			List<Usuario> listaUsuarioReputacao = new ArrayList<Usuario>();
			
			for(Usuario usuarios: listaDeUsuarios){
				listaUsuarioReputacao.add(usuarios);
			}
			
			while(!listaUsuarioReputacao.isEmpty()){
				Usuario usr = usuarioComMaisAltaReputacaoDaLista(listaUsuarioReputacao);
				retorno+=usr.getLogin() + "; ";
				listaUsuarioReputacao.remove(usr);
			}
			
			
			return formatarRequisicoes(retorno);
			
		}
		else{
			throw new Exception("Categoria inexistente");
		}
	}
	
	private Usuario usuarioComMaisAltaReputacaoDaLista(List<Usuario> lista){
		Usuario usuario = lista.get(0);
		for(Usuario usr: lista){
			if (usr.getReputacao()>usuario.getReputacao()){
				usuario = usr;
			}
		}return usuario;
	}
	
	//TODO dpois arruma isso
	private String formatarRequisicoes(String requisicoes){
		String retorno = "";
		for (int i =0;i< requisicoes.split("; ").length;i++){
			if (i==requisicoes.split("; ").length-1){
				retorno +=requisicoes.split("; ")[i];
				break;
			}
			retorno += requisicoes.split("; ")[i] + "; ";
		}return retorno;
	}
	
	/**
	 * Cadastra um Item do Usuario
	 * @param usuario
	 *       Usuario dono do Item a ser cadastrado
	 * @param it
	 *        Item a ser cadastrado
	 * @return
	 *       ID do Item
	 * @throws Exception
	 */
	public String cadastrarItem(Usuario usuario, String nome, String descricao, String categoria) throws Exception {
		
		return usuario.getGerenciadorItens().adicionarItem(new Item(nome, descricao, categoria));
	}
		

	/**
	 * Abre uma Sessao para o usuario
	 * @param usr
	 *         Usuario que sera aberta sessao
	 * @return
	 *        ID da sessa do Usuario
	 */
	public String abrirSessao(Usuario usr) {
		this.getListaUsuariosLogados().add(usr);
		usr.criarHistoricoAtividades();
		return usr.getID();
		
	}
	
	/**
	 * Recupera os Amigos do Usuarios
	 * @param usuario
	 *        Usuario passado como parametro
	 * @return
	 *        Amigos do Usuario
	 */
	public String getAmigos(Usuario usuario) {
		return usuario.getGerenciadorAmizades().stringDeAmigos();
		
	}
	
	/**
	 * Recupera os Itens do Usuarios
	 * @param usuario
	 *        Usuario possuidor dos Itens
	 * @return
	 *      Itens do Usuario em formato de String
	 */
	public String getItens(Usuario usuario) {
		return usuario.getGerenciadorItens().stringDeItens();
		
	}
	
	public String getItens(Usuario usuario, Usuario usuario2) throws Exception {
		
		if (!usuario.getGerenciadorAmizades().ehMeuAmigo(usuario2)) {
			throw new Exception("O usuário não tem permissão para visualizar estes itens");
		}
		
		if (usuario2.getGerenciadorItens().getListaMeusItens().isEmpty()) {
			return "O usuário não possui itens cadastrados";
		}
		
		return usuario2.getGerenciadorItens().stringDeItens();
		
	}

	public String requisitarEmprestimo(Usuario usuario, Item item, int dias) throws Exception {
		Usuario usuario2 = buscarDonoItem(item);
		
		if ( usuario2.getGerenciadorAmizades().ehMeuAmigo(usuario)){
			String idRequisicaoEmprestim = usuario2.getGerenciadorItens().requisitarEmprestimos(usuario, item.getID(), dias);
			
			String assunto = "Empréstimo do item " + item.getNome() + " a " + usuario.getNome();
			String mensagem = usuario.getNome() + " solicitou o empréstimo do item " + item.getNome();
			usuario.getGerenciadorMensagens().enviarMensagem(usuario2, assunto, mensagem, idRequisicaoEmprestim);
							
			return idRequisicaoEmprestim;
		}
		throw new Exception("O usuário não tem permissão para requisitar o empréstimo deste item");
		
	}
	
	/**
	 * Devolve um item
	 * @param usuario
	 *         Usuario Emprestador
	 * @param usuario2
	 *          Usuario Beneficiado
	 * @param item
	 *         Item a ser devolvido
	 * @throws Exception
	 *         Caso o item ja tenha sido devolvido
	 *         
	 */
	public void devolverItem(Usuario usuarioEmprestador, Usuario usuarioBeneficiado, Item item) throws Exception {

		if (item.getEmprestimo().isDevolvido()){
			throw new Exception("Item já devolvido");
		}
		
		if (usuarioBeneficiado.equals(usuarioEmprestador)){
			throw new Exception("O item só pode ser devolvido pelo usuário beneficiado");
		}
		

		if (!item.getEmprestimo().getBeneficiado().equals(usuarioEmprestador)){
            throw new Exception("O item só pode ser devolvido pelo usuário beneficiado");
            }
		
		item.getEmprestimo().setDevolvido(true);
	
	}
	/**
	 * Confirma o termino de um Emprestimo
	 * @param usuario2
	 * @param item
	 * @throws Exception
	 */
	public void confirmarTerminoEmprestimo(Usuario usuario2, Item item) throws Exception {
		
		if (!usuario2.getGerenciadorItens().getListaMeusItens().contains(item)){
			throw new Exception("O término do empréstimo só pode ser confirmado pelo dono do item");
		}
		
		if (item.getEmprestimo().isDevolucao()){
			throw new Exception("Término do empréstimo já confirmado");
		}
		
		item.getEmprestimo().setDevolucao(true);
		usuario2.getGerenciadorItens().confirmarTerminoEmprestimo(item);
		
		if(item.getEmprestimo().foiCompletado()){
			usuario2.getGerenciadorItens().addEmprestimoCompletado(item);
		}
		
		String assunto = "O item " + item.getNome() + " do usuário " + usuario2.getNome() + " está disponível";
		String mensagem = "Agora você pode requisitar o empréstimo do " + item.getNome();
		
		for (Usuario usuario : item.getEmprestimo().getListaDeUsuariosInteressados() ){
			String destinatario = usuario.getLogin();
		
			enviarMensagem(usuario2.getID(), destinatario, assunto, mensagem);
		}
	
		
		
	}
	/**
	 * Envia uma msg pra um destinatario
	 * @param idSessao
	 *         ID do remetente da msg
	 * @param destinatario
	 *        Login do destinatario da msg
	 * @param assunto
	 *       Assunto da msg
	 * @param mensagem
	 *       corpo da mensagem
	 * @return
	 *       ID da msg
	 * @throws Exception
	 *       Caso algum dos parametros seja invalido
	 */
	
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem) throws Exception{
		
		
		if (!stringValida(idSessao)) {
			throw new Exception("Sessão inválida");
		}
		
		if (!stringValida(destinatario)){
			throw new Exception ("Destinatário inválido");
		}
		
		try {
			this.buscarUsuarioPorID(idSessao);
		} catch (Exception e){
			throw new Exception ("Sessão inexistente");
		}
		
		try {
			this.buscarUsuarioPorLogin(destinatario);
		} catch (Exception e){
			throw new Exception ("Destinatário inexistente");
		}
		
		if (!stringValida(assunto)){
			throw new Exception("Assunto inválido");
		}
		
		if (!stringValida(mensagem)){
			throw new Exception("Mensagem inválida");
		}
		return this.buscarUsuarioPorID(idSessao).getGerenciadorMensagens().enviarMensagem(buscarUsuarioPorLogin(destinatario), assunto, mensagem);
	}

	private String enviarMensagem(Usuario usuario, Usuario usuario2,
			String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception {
		
		if (!stringValida(mensagem)){
			throw new Exception("Mensagem inválida");
		}
		
		if (!stringValida(assunto)){
			throw new Exception("Assunto inválido");
		}
		
		return usuario.getGerenciadorMensagens().enviarMensagem(usuario2, assunto, mensagem, idRequisicaoEmprestimo);
	}
	
	/**
	 * Requisita devolucao do Item
	 * @param idSessao
	 *       ID de sessao do Usuario
	 * @param idEmprestimo
	 *           Id de Emprestimo
	 * @throws Exception
	 *             Caso algum parametro seja invalido
	 */
	public void requisitarDevolucao(String idSessao, String idEmprestimo) throws Exception{
		
    	buscarUsuarioPorID(idSessao);
    	boolean testa = false;
			
		if (!stringValida(idEmprestimo)){
			throw new Exception("Identificador do empréstimo é inválido");
		}
		
		if ( buscarUsuarioBeneficiador(idEmprestimo) == null){
			throw new Exception("Empréstimo inexistente");
		}
		
		try {
			if (!testa){
				testa = (! buscarUsuarioBeneficiador(idEmprestimo).equals( buscarUsuarioPorID(idSessao)) && ! buscarUsuarioIdEmprestimo(idEmprestimo).equals(buscarUsuarioPorID(idSessao)));
			}
		}catch (Exception e){
			throw new Exception("O usuário não tem permissão para requisitar a devolução deste item");
		}	
		
		if ( buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().isDevolucao()){
			throw new Exception ("Item já devolvido");
		}
		
		if ( buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().isDevolvido()){
			throw new Exception ("Item já devolvido");
		}
		
		 buscarUsuarioPorID(idSessao).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().requisitarDevolucao();
		
		try {
			String assunto = "Empréstimo do item " +  buscarUsuarioBeneficiador(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getNome() + " a " +  buscarUsuarioBeneficiador(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getBeneficiado().getNome();
			String mensagem =  buscarDonoItem( buscarUsuarioBeneficiador(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getID()).getNome() + " solicitou a devolução do item " +  buscarUsuarioBeneficiador(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getNome();
			String destinatario =  buscarUsuarioBeneficiador(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getBeneficiado().getLogin();
			
			enviarMensagem(idSessao, destinatario, assunto, mensagem,  buscarUsuarioBeneficiador(idEmprestimo).getGerenciadorItens().buscarItemIdEmprestimo(idEmprestimo).getEmprestimo().getIDRequisicao());
		} catch (Exception e){
			System.out.println(e.getLocalizedMessage());
		}
	}
	/**
	 * Eniva uma Mensagem para outro Usuario sobre um pedido de requisicao e emrpestimo
	 * @param idSessao
	 * @param destinatario
	 * @param assunto
	 * @param mensagem
	 * @param idRequisicaoEmprestimo
	 * @return
	 * @throws Exception
	 */
	public String enviarMensagem(String idSessao, String destinatario,
			String assunto, String mensagem, String idRequisicaoEmprestimo)
			throws Exception {
		Usuario usuario = buscarUsuarioPorID(idSessao);
		Usuario usuario2 = buscarUsuarioDestinatario(destinatario);

		if (!stringValida(mensagem)) {
			throw new Exception("Mensagem inválida");
		}

		if (!stringValida(assunto)) {
			throw new Exception("Assunto inválido");
		}

		buscarUsuarioPorRequisicaoEmprestimo(idRequisicaoEmprestimo);

		if (!buscarUsuarioPorRequisicaoEmprestimo(idRequisicaoEmprestimo).equals(
				buscarUsuarioPorID(idSessao))
				&& !this.buscarUsuarioIdEmprestimo(idRequisicaoEmprestimo)
						.equals(buscarUsuarioPorID(idSessao))) {
			throw new Exception("O usuário não participa deste empréstimo");
		}

		return enviarMensagem(usuario, usuario2, assunto, mensagem,
				idRequisicaoEmprestimo);
	}
	
	/**
	 * Pesquisa um Item do Usuario
	 * @param usuario2
	 * @param chave
	 * @param atributo
	 * @param tipoOrdenacao
	 * @param criterioOrdenacao
	 * @return
	 * @throws Exception
	 */
	public String pesquisarItem(Usuario usuario2, String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao)throws Exception{
		if (!stringValida(chave)) {
			throw new Exception("Chave inválida");
		}
		
		if (!stringValida(atributo)) {
			throw new Exception("Atributo inválido");
		}
		
		if (!atributoValido(atributo)) {
			throw new Exception("Atributo inexistente");
		}
		
		if (!stringValida(tipoOrdenacao)) {
			throw new Exception("Tipo inválido de ordenação");
		}
		
		if (!tipoOrdenacaoValido(tipoOrdenacao)) {
			throw new Exception("Tipo de ordenação inexistente");
		}
		
		
		if (!stringValida(criterioOrdenacao)) {
			throw new Exception("Critério inválido de ordenação");
		}
		
		if (!criterioOrdenacaoValido(criterioOrdenacao)) {
			throw new Exception("Critério de ordenação inexistente");
		}
		String resposta = "";
		
		if(criterioOrdenacao.equals("dataCriacao")){
			if (tipoOrdenacao.equals("crescente")){
				for (Usuario usuario : usuario2.getGerenciadorAmizades().getListaDeAmigos()){
					if (resposta.equals("")){
						resposta += usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
						
					} else if (!resposta.equals("") && !usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao).equals("")){
						resposta += "; " + usuario.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
				}
			} else {
				for (int i = usuario2.getGerenciadorAmizades().getListaDeAmigos().size() - 1; i >= 0; i--){
					if (resposta.equals("")){
						resposta += usuario2.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
						
					} else if (!resposta.equals("") && !usuario2.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao).equals("")){
						resposta += "; " + usuario2.getGerenciadorAmizades().getListaDeAmigos().get(i).getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
				}
			}
		}
		else if(criterioOrdenacao.equals("reputacao")){
			if (tipoOrdenacao.equals("crescente")){
				
				List<Usuario> lista = new ArrayList<Usuario>();
				for(Usuario usr: usuario2.getGerenciadorAmizades().getListaDeAmigos()){
					lista.add(usr);
				}
				while(!lista.isEmpty()){
					
					Usuario usr = usuarioComMenorReputacaoDaLista(lista);
					if (resposta.equals("")){
						resposta += usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);}
					else{
						resposta += "; "+usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
					lista.remove(usr);
				}
			}
			else if(tipoOrdenacao.equals("decrescente")){
				
				List<Usuario> lista = new ArrayList<Usuario>();
				for(Usuario usr: usuario2.getGerenciadorAmizades().getListaDeAmigos()){
					lista.add(usr);
				}
				
				while(!lista.isEmpty()){
					Usuario usr = usuarioComMaisAltaReputacaoDaLista(lista);
					
					if (resposta.equals("")){
						resposta += usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);}
					else{
						resposta += "; "+usr.getGerenciadorItens().buscarItemCadastrado(chave, atributo, tipoOrdenacao, criterioOrdenacao);
					}
					lista.remove(usr);
				}
			}
		}
		
		if(resposta.isEmpty()){
			return  "Nenhum item encontrado";
		}
		
		return resposta;
	}
	
	public String historicoAtividades(String idSessao)throws Exception{
//		System.out.println(buscarUsuarioPorID(idSessao).getHistoricoAtividades().get(0).getAtividade());
		return (new AdaptadorAtividades()).adaptar(this.buscarUsuarioPorID(idSessao).getHistoricoAtividades());
	}
	
	public void adicionarAtividadesUsuario(String idSessao, String login, String tipoAtividade) throws Exception{
		String nome1 = buscarUsuarioPorID(idSessao).getNome();
		
		String nome2 = buscarUsuarioPorLogin(login).getNome();
		
		if(tipoAtividade.equals("amizade")){
			String atividade1 =  nome1 +" e " + nome2 + " são amigos agora";
			long criacao = System.currentTimeMillis();
			buscarUsuarioPorID(idSessao).addAtividade(new Atividade(atividade1,criacao));
			
			
			
			long criacao2 = System.currentTimeMillis();
			String atividade2 = nome2 +" e " + nome1 + " são amigos agora";
			
			
			
			buscarUsuarioPorLogin(login).addAtividade(new Atividade(atividade2, criacao2));
		}
		
		
		
	}
	
	public void adicionarAtividadesUsuario(String idSessao, Usuario usr, Item it) throws Exception{
		String nome1 = buscarUsuarioPorID(idSessao).getNome();
		
		String nome2 = usr.getNome();
		
		String nomeItem  = it.getNome();
		
		String atividade = nome1 + " emprestou " + nomeItem + " a " + nome2; 
		long criacao = System.currentTimeMillis();
		buscarUsuarioPorID(idSessao).addAtividade(new Atividade(atividade,criacao));
		
	}
	
	public PedidoItem buscarPedido(String idSessao){
		for(PedidoItem pedido: listaPedidos){
			if(pedido.getIdPedido().equals(idSessao)){
				return pedido;
			}
		}return null;
	}
	
	
	public void adicionarAtividadesUsuario(String idSessao, Item it, String tipoAtividade) throws Exception{
		
		if(tipoAtividade.equals("item")){
			String nome1 = buscarUsuarioPorID(idSessao).getNome();
			String atividade1 =  nome1 + " cadastrou " + it.getNome();
			
			long criacao = System.currentTimeMillis();
			buscarUsuarioPorID(idSessao).addAtividade(new Atividade(atividade1, criacao));
		}
		else if(tipoAtividade.equals("interesse")){
			String nome1 = buscarUsuarioPorID(idSessao).getNome();
			String atividade1 =  nome1 + " tem interesse pelo item " + it.getNome() + " de " + buscarDonoItem(it).getNome();
		
			long criacao = System.currentTimeMillis();
			buscarUsuarioPorID(idSessao).addAtividade(new Atividade(atividade1, criacao));
//			System.out.println(buscarUsuarioPorID(idSessao).getHistoricoAtividades().get(1).getAtividade());
		}
		else if(tipoAtividade.equals("terminoEmprestimo")){
			String nome1 = buscarUsuarioPorID(idSessao).getNome();
			String atividade1 =  nome1 + " confirmou o término do empréstimo do item " + it.getNome();
			
			long criacao = System.currentTimeMillis();
			buscarUsuarioPorID(idSessao).addAtividade(new Atividade(atividade1, criacao));		}
		
		
		
			
	}
	
	public String historicoAtividadesConjunto(String idSessao) throws Exception{
		Usuario usr = this.buscarUsuarioPorID(idSessao);
		List<Atividade> atv = usr.getHistoricoAtividades();
		
	
		List<Atividade> atvGeral = new ArrayList<Atividade>();;
	
		if(!atv.isEmpty()){
			for(Atividade atividades:atv){
				atvGeral.add(atividades);
			}
		}
		

		for(Usuario amigos:usr.getGerenciadorAmizades().getListaDeAmigos()){
			for(Atividade atividades:amigos.getHistoricoAtividades()){
				if(!amigos.getHistoricoAtividades().isEmpty() && !atividades.getAtividade().contains(usr.getNome())){
					atvGeral.add(atividades);
				}
				else if(!amigos.getHistoricoAtividades().isEmpty() && atividades.getAtividade().contains(usr.getNome()) && atividades.getAtividade().contains("emprestou")){
					atvGeral.add(atividades);
				}
				
				else if(!amigos.getHistoricoAtividades().isEmpty() && atividades.getAtividade().contains(usr.getNome()) && atividades.getAtividade().contains("interesse")){
					atvGeral.add(atividades);
				}
			}
		}
		
		eliminaAtividade(atvGeral);
		
		Collections.sort(atvGeral);
		return (new AdaptadorAtividades()).adaptar(atvGeral);
		
	}
	
	
	private List<Atividade> eliminaAtividade(List<Atividade> atv){
		List<Atividade> retorno = new ArrayList<Atividade>();
		boolean contem = false;
		for(Atividade atividade1: atv){
			for(Atividade atividade2: retorno){
				if(atividade1.equals(atividade2)){
					contem = true;
					break;
				}
			}
			if(!contem){
				retorno.add(atividade1);
			}
		}

		
		return retorno;
	}
	
//	private String removePontoVirgula(String str){
//		
//		String temporaria = "";
//		str = str.replaceAll("; ", ";");
//		
//		for(int i = 0 ; i< str.split(";").length;i++){
//			if(i!=str.split(";").length-1){
//				temporaria += str.split(";")[i] + "; ";
//			} else{
//				temporaria += str.split(";")[i];
//			}
//		}return temporaria;
//		
//	}
//	
//	private String formataString(String str){
//		return str.replace("[", "").replace("]", "").replaceAll(",", ";");
//	}
//	
//	private String formataString2(String str){
//		return str.replace("[", "").replace(",", ";").replaceAll(",", ";");
//	}
	
	private Usuario usuarioComMenorReputacaoDaLista(List<Usuario> lista){
		Usuario usuario = lista.get(0);
		for(Usuario usr: lista){
			if (usr.getReputacao() < usuario.getReputacao()){
				usuario = usr;
			}
		}return usuario;
	}
	
	private boolean atributoValido(String atributo){
		
		if (atributo.equals("nome") || atributo.equals("descricao") || atributo.equals("categoria")){
			return true;
		}
		
		return false;
	}
	
	private boolean tipoOrdenacaoValido(String tipoOrdenacao){
		
		if (tipoOrdenacao.equals("crescente") || tipoOrdenacao.equals("decrescente")){
			return true;
		}
		
		return false;
	}
	
	private boolean criterioOrdenacaoValido(String criterioOrdenacao){
		
		if (criterioOrdenacao.equals("dataCriacao") || criterioOrdenacao.equals("reputacao")){
			return true;
		}
		return false;
	}
	
}
