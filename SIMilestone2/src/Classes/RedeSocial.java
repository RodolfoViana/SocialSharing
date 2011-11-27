package Classes;


/**
 * 
 * Classe responsavel por se comunicar com gerenciador de usuario. (Funciona como fachada)
 * 
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0
 *
 */

public class RedeSocial {

	GerenciadorUsuarios gerenciadorUsu;
	
	/**
	 * Inicializa gerenciador de usuarios
	 */
	public RedeSocial() {
		gerenciadorUsu = new GerenciadorUsuarios();
	}
	
	/**
	 * Zera o sistema
	 */
	public void zerarSistema() {
		gerenciadorUsu = new GerenciadorUsuarios();
	}
	
	/**
	 * Responsavel por encerrar o sistema
	 */
	public void encerrarSistema() {
		// TODO tem que salvar os dados dos usuarios em algum local
	}
	
	/**
	 * Responsavel por retornar o gerenciador de usuario
	 * @return Gerenciador de usuario
	 */
	public GerenciadorUsuarios getGerenciadorUsuarios() {
		return gerenciadorUsu;
	}
	
	/**
	 * Responsavel por aprovar amizade entre dois usuarios
	 * @param idSessao Sessao do usuario 
	 * @param login Login do usuario
	 * @throws Exception Caso idSessao ou login sejam invalidos
	 */
	public void aprovarAmizade(String idSessao, String login) throws Exception {
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades()
				.adicionarAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorLogin(login));
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorLogin(login)
				.getGerenciadorAmizades()
				.adicionarNovoAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));
		this.getGerenciadorUsuarios().adicionarAtividadesUsuario(idSessao, login, "amizade");

	}
	
	/**
	 * Responsavel pelas requisicoes de amizade entre dois usuarios
	 * @param idSessao IdSessao do usuario
	 * @return IdRequisicao de amizade
	 * @throws Exception Caso a idSessao seja invalida
	 */
	public String getRequisicoesDeAmizade(String idSessao) throws Exception {
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades().getRequisicoesDeAmizade();
	}
	
	/**
	 * Responsavel por verificar se dois usuarios sao amigos
	 * @param idSessao IdSessao do usuario
	 * @param login Login do usuario
	 * @return true se forem amigos ou false caso contrario
	 * @throws Exception Caso idSessao ou login sejam invalidos
	 */
	public String ehAmigo(String idSessao, String login) throws Exception {
		if (this.getGerenciadorUsuarios()
				.buscarUsuarioPorID(idSessao)
				.getGerenciadorAmizades()
				.ehMeuAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorLogin(login))) {
			return "true";
		} else {
			return "false";
		}

	}
	
	/**
	 * Responsavel por requisitar amizade entre dois usuarios
	 * @param idSessao IdSessao do usuario
	 * @param login Login do usuario
	 * @throws Exception Caso idSessao ou login sejam invalidos
	 */
	public void requisitarAmizade(String idSessao, String login)
			throws Exception {
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios()
				.buscarUsuarioPorLogin(login)
				.getGerenciadorAmizades()
				.adicionarProvavelAmigo(
						getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));
	}

	public String localizarUsuario(String idSessao, String chave, String atributo) throws Exception {
		return getGerenciadorUsuarios().localizarUsuario(idSessao, chave,
				atributo);
	}
	
	public String localizarUsuario(String idSessao)throws Exception{
		return this.getGerenciadorUsuarios().localizarUsuario(idSessao);
	}

	public String getAtributoItem(String idItem, String atributo)throws Exception {
		return this.getGerenciadorUsuarios().getAtributoItem(idItem, atributo);		
	}

	public String cadastrarItem(String idSessao, String nome, String descricao, String categoria) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		String resposta = getGerenciadorUsuarios().cadastrarItem(usuario, nome, descricao, categoria);
		getGerenciadorUsuarios().adicionarAtividadesUsuario(idSessao, new Item(nome, descricao, categoria), "item");
		
		return resposta;
	}

	public void criarUsuario(String login, String nome, String endereco)throws Exception {
		getGerenciadorUsuarios().criarUsuario(login, nome, endereco);
	}

	public String abrirSessao(String login) throws Exception {
		Usuario usr = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		return getGerenciadorUsuarios().abrirSessao(usr);
	}

	public String getAtributoUsuario(String login, String atributo) throws Exception {
		return getGerenciadorUsuarios().getAtributoUsuario(login, atributo);
	}
	
	public String getAmigos(String idSessao) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		return getGerenciadorUsuarios().getAmigos(usuario);
	}

	public String getAmigos(String idSessao, String login) throws Exception {		
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		return getGerenciadorUsuarios().getAmigos(usuario2);
	}

	public String getItens(String idSessao) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		return getGerenciadorUsuarios().getItens(usuario);
	}

	public String getItens(String idSessao, String login) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = getGerenciadorUsuarios().buscarUsuarioPorLogin(login);
		
		return getGerenciadorUsuarios().getItens(usuario, usuario2);
	}
	
	public String requisitarEmprestimo(String idSessao, String idItem, int dias) throws Exception {
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = getGerenciadorUsuarios().buscarItemPorID(idItem);
		
		return getGerenciadorUsuarios().requisitarEmprestimo(usuario, item, dias);
	}
	
	public String getEmprestimos(String idSessao, String tipo)throws Exception{
		return this.getGerenciadorUsuarios().getEmprestimos(idSessao, tipo);
	}
	
	public String aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo)throws Exception{
		return this.getGerenciadorUsuarios().aprovarEmprestimo(idSessao, idRequisicaoEmprestimo);
	}
	
	
	public void devolverItem(String idSessao, String idEmprestimo) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario usuario2 = this.getGerenciadorUsuarios().buscarUsuarioBeneficiador(idEmprestimo);
		Item item = this.getGerenciadorUsuarios().buscarItemIdEmprestimo(idEmprestimo);
		
		getGerenciadorUsuarios().devolverItem(usuario, usuario2, item);	
	}
	
	public void confirmarTerminoEmprestimo(String idSessao, String idEmprestimo) throws Exception{
		Usuario usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios().buscarUsuarioBeneficiador(idEmprestimo);
		Item item = this.getGerenciadorUsuarios().buscarItemIdEmprestimo(idEmprestimo);
		
		getGerenciadorUsuarios().confirmarTerminoEmprestimo(usuario2, item);
		getGerenciadorUsuarios().adicionarAtividadesUsuario(idSessao, item, "terminoEmprestimo");
	}
	
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem) throws Exception{
		return getGerenciadorUsuarios().enviarMensagem(idSessao, destinatario, assunto, mensagem);
	}
	
	public String enviarMensagem (String idSessao, String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception{
		return this.getGerenciadorUsuarios().enviarMensagem(idSessao, destinatario, assunto, mensagem, idRequisicaoEmprestimo);
		
	}
	
	public String lerTopicos (String idSessao, String tipo) throws Exception{	
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().lerTopicos(tipo);
	}
	
	public String lerMensagens (String idSessao, String idTopico) throws Exception{
		return this.getGerenciadorUsuarios().lerMensagens(idSessao, idTopico);
	}
	
	public void requisitarDevolucao(String idSessao, String idEmprestimo) throws Exception{
		this.getGerenciadorUsuarios().requisitarDevolucao(idSessao, idEmprestimo);
	}
		
	public void adicionarDias(int dias){
		this.getGerenciadorUsuarios().simularPassagemDoTempo(dias);
	}
	
	public void registrarInteresse(String idSessao, String idItem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios().registrarInteresse(usuario, idSessao, idItem);
	}
	
	public String pesquisarItem (String idSessao, String chave, String atributo, String tipoOrdenacao, String criterioOrdenacao) throws Exception{
		return this.getGerenciadorUsuarios().pesquisarItem(this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao) ,chave, atributo, tipoOrdenacao, criterioOrdenacao);
	}
	
	public void desfazerAmizade (String idSessao, String loginAmigo) throws Exception{
		Usuario	usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Usuario	usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorLogin(loginAmigo);
		this.getGerenciadorUsuarios().desfazerAmizade(usuario, usuario2);
	} 
	
	public void apagarItem (String idSessao, String idItem) throws Exception{
		this.getGerenciadorUsuarios().apagarItem(idSessao, idItem);
	}
	
	public String getRanking(String idSessao, String categoria) throws Exception{
		return this.getGerenciadorUsuarios().getRanking(idSessao, categoria);
	}
	
	public String historicoAtividades(String idSessao) throws Exception{
		return getGerenciadorUsuarios().historicoAtividades(idSessao);
	}
	
	public String historicoAtividadesConjunto(String idSessao) throws Exception{	
		return this.getGerenciadorUsuarios().historicoAtividadesConjunto(idSessao);
	}
	
	public String publicarPedido (String idSessao, String nomeItem, String descricaoItem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		return this.getGerenciadorUsuarios().publicarPedido(usuario, nomeItem, descricaoItem);
	}
	
	public void oferecerItem (String idSessao, String idPublicacaoPedido, String idItem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios().oferecerItem(usuario, idPublicacaoPedido, idItem);
	}
	
	public void rePublicarPedido (String idSessao, String idPublicacaoPedido) throws Exception{
		PedidoItem pedido = getGerenciadorUsuarios().buscarPedido(idPublicacaoPedido);
		Usuario usuario = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios().rePublicarPedido(usuario, pedido);
	}
	
	public String requisitarTrocaDeItem (String idSessao, String idItemOferecido, String idItemPedido) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = this.getGerenciadorUsuarios().buscarItemPorID(idItemOferecido);
		Item item2 = this.getGerenciadorUsuarios().buscarItemPorID(idItemPedido);
		
		return this.getGerenciadorUsuarios().requisitarTrocaDeItem(usuario, item, item2);
	} 
	
	public void aprovarTroca (String idSessao, String idPedido) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		this.getGerenciadorUsuarios().buscarItemPorIDPedido(idPedido);
		this.getGerenciadorUsuarios().aprovarTroca(usuario, idPedido);	
		
	}
	
	
}
