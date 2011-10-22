package Classes;


/**
 * 
 * Classe responsavel por se comunicar com gerenciador de usuario. (Funciona como fachada)
 * 
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR GOMES
 * @version 1.0
 *
 */

public class Fachada {

	GerenciadorUsuarios gerenciadorUsu;
	
	/**
	 * Inicializa gerenciador de usuarios
	 */
	public Fachada() {
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
		return getGerenciadorUsuarios().buscarDonoItem(idItem)
				.getGerenciadorItens().getAtributoItem(idItem, atributo);
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
		Usuario usuario = null;
		Usuario usuario2 = null;
		boolean usuariosSaoAmigos = true;
		
		boolean ehDonoDoItem = true;
		try {
			usuario = this.getGerenciadorUsuarios().buscarUsuarioIdEmprestimo(idRequisicaoEmprestimo);
			usuario2 = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);

			usuariosSaoAmigos = usuario.getGerenciadorAmizades().ehMeuAmigo(usuario2);
			ehDonoDoItem = getGerenciadorUsuarios().buscarUsuarioEmprestador(idSessao).equals(getGerenciadorUsuarios().buscarUsuarioPorID(idSessao));

		} catch (Exception e){
			
			
		}
		String resposta = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorItens().aprovarRequisicaoEmprestimo(ehDonoDoItem,usuariosSaoAmigos,this.getGerenciadorUsuarios().requisicaoEmprestimoExiste(idRequisicaoEmprestimo),idRequisicaoEmprestimo);
		try{
			getGerenciadorUsuarios().adicionarAtividadesUsuario(idSessao, usuario, getGerenciadorUsuarios().buscarItemIdEmprestimo(resposta));
		}catch(Exception e){
			
		}
		return resposta;
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
		boolean resp1 = false;
		boolean resp2 = false;
		
		if (!this.getGerenciadorUsuarios().msgExiste(idTopico)){
			resp2 = true;
		}
		
		if (!this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(getGerenciadorUsuarios().buscarDestinatario(idTopico)) && !this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(getGerenciadorUsuarios().buscarRemetente(idTopico))){
			resp1 = true;
		}
		
		return this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getGerenciadorMensagens().lerMensagens(resp1,resp2,idTopico);
	}
	
	public void requisitarDevolucao(String idSessao, String idEmprestimo) throws Exception{
		
		this.getGerenciadorUsuarios().requisitarDevolucao(idSessao, idEmprestimo);
	}
		
	public void adicionarDias(int dias){
	
		this.getGerenciadorUsuarios().simularPassagemDoTempo(dias);
		
	}
	
	public void registrarInteresse(String idSessao, String idItem) throws Exception{
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		if (this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).equals(this.getGerenciadorUsuarios().buscarDonoItem(idItem))){
			throw new Exception ("O usuário não pode registrar interesse no próprio item");
		}
		Item item = this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem);
		if (item.getEmprestimo() == null){
			throw new Exception("O usuário não tem permissão para registrar interesse neste item");
		}
		item.getEmprestimo().registrarInteresse(usuario);	
		getGerenciadorUsuarios().adicionarAtividadesUsuario(idSessao, item, "interesse");
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
		Usuario usuario = this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		Item item = this.getGerenciadorUsuarios().buscarDonoItem(idItem).getGerenciadorItens().buscarItemPorID(idItem);
		usuario.getGerenciadorItens().apagarItem(item);
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param categoria
	 * @return
	 * @throws Exception
	 */
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
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		if (!stringValida(nomeItem)){
			throw new Exception ("Nome inválido");
		}
		
		if (!stringValida(descricaoItem)){
			throw new Exception ("Descrição inválida");
		}
		
		String nome1 = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getNome();
		String atividade = nome1 + " precisa do item " + nomeItem;
		
		long criacao = System.currentTimeMillis();
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).addAtividade(new Atividade(atividade,criacao));
		
		String idPublicacaoPedido = idSessao; 
		getGerenciadorUsuarios().getPedidos().add(new PedidoItem(atividade,idPublicacaoPedido,nomeItem,descricaoItem));
		return idPublicacaoPedido;
	}
	
	public void oferecerItem (String idSessao, String idPublicacaoPedido, String idItem) throws Exception{
		this.getGerenciadorUsuarios().buscarUsuarioPorID(idSessao);
		
		if (!stringValida(idPublicacaoPedido)){
			throw new Exception ("Identificador da publicação de pedido é inválido");
		}
		
		try {
			getGerenciadorUsuarios().buscarUsuarioPorID(idPublicacaoPedido);
		} catch (Exception e){
			throw new Exception ("Publicação de pedido inexistente");
		}
		
		getGerenciadorUsuarios().buscarItemPorID(idItem);
		
		String assunto = "O usuário " + getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getNome() + " ofereceu o item " + getGerenciadorUsuarios().buscarItemPorID(idItem).getNome(); 
		String mensagem = "Item oferecido: " + getGerenciadorUsuarios().buscarItemPorID(idItem).getNome() + " - " + getGerenciadorUsuarios().buscarItemPorID(idItem).getDescricao();
		String login = getGerenciadorUsuarios().buscarUsuarioPorID(idPublicacaoPedido).getLogin();
		
		
		getGerenciadorUsuarios().enviarMensagem(idSessao, login, assunto, mensagem);
		
	}
	
	public void rePublicarPedido (String idSessao, String idPublicacaoPedido) throws Exception{

		PedidoItem pedido =getGerenciadorUsuarios().buscarPedido(idPublicacaoPedido);
		
		String nome1 = getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).getNome();
		String atividade = pedido.getPedido();
		
		long criacao = System.currentTimeMillis();
		
		getGerenciadorUsuarios().buscarUsuarioPorID(idSessao).addAtividade(new Atividade(atividade,criacao));
	}	
	
	private boolean stringValida(String string){
        if (string == null || string.isEmpty()){
            return false;
        }
        return true;
    }
}
