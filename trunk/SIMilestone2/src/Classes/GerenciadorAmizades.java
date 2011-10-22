package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que guarda as amizades de um Usuario
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 *
 */

public class GerenciadorAmizades {

	private List<Usuario> listaDeAmigos;
	private List<Usuario> listaDeProvaveisAmigos;
	
	/**
	 * Inicia os Atributos da Classe
	 */
	
	public GerenciadorAmizades(){
		listaDeAmigos = new ArrayList<Usuario>();
		listaDeProvaveisAmigos = new ArrayList<Usuario>();
	}

	/**
	 * Recupera a Lista de Amigos do Usuario 
	 * @return Lista de Amigos do Usuario
	 */
	
	public List<Usuario> getListaDeAmigos() {
		return listaDeAmigos;
	}
	
	/**
	 * Recupera a Lista com os Amigos que enviaram convite pra Usuario
	 * @return A Lista com os provaveis Amigos do Usuario
	 */
	
	public List<Usuario> getListaDeProvaveisAmigos() {
		return listaDeProvaveisAmigos;
	}
	
	/**
	 * Adiciona um Usuario na Lista de Amigos de outro Usuario
	 * @param usr 
	 *          Usuario que sera adicionado na lista de amigos
	 * @throws Exception
	 *          Caso o usuario seja igual a null
	 */
	
	public void adicionarNovoAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a null");
		}
		else if(listaDeAmigos.contains(usr)){
			throw new Exception("Os usuários já são amigos");
		}
		
		listaDeAmigos.add(usr);
		listaDeProvaveisAmigos.remove(usr);
	}
	
	public void adicionarAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a null");
		}
		else if(listaDeAmigos.contains(usr)){
			throw new Exception("Os usuários já são amigos");
		}
		else if(!listaDeProvaveisAmigos.contains(usr)){
			throw new Exception("Requisição de amizade inexistente");
		}
		listaDeAmigos.add(usr);
		listaDeProvaveisAmigos.remove(usr);
	}
	
	/**
	 * Adiciona um Usuario na Lista de Provaveis Amigos de outro Usuario
	 * @param usr 
	 *          Usuario que sera adicionado na lista de provaveis amigos
	 * @throws Exception
	 *          Caso o usuario seja igual a null
	 */
	public void adicionarProvavelAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a null");
		}
		else if(listaDeAmigos.contains(usr)){
			throw new Exception("Os usuários já são amigos");
		}
		else if(listaDeProvaveisAmigos.contains(usr)){
			throw new Exception("Requisição já solicitada");
		}
		listaDeProvaveisAmigos.add(usr);
	}
	
	/**
	 * Diz se um Usuario eh amigo de Outro Usuario
	 * @param usr
	 *         Usuario que sera analizado
	 * @return
	 *        True, caso sejam amigos
	 *        False, caso contrario
	 * @throws Exception
	 *       Caso o Usuario seja igual a Null
	 */
	
	public boolean ehMeuAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		return listaDeAmigos.contains(usr);
	}
	
	/**
	 * Diz se um Usuario eh um provavel amigo de Outro Usuario
	 * @param usr
	 *         Usuario que sera analizado
	 * @return
	 *        True, caso sejam amigos
	 *        False, caso contrario
	 * @throws Exception
	 *       Caso o Usuario seja igual a Null
	 */
	
	public boolean ehUmProvavelAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		return listaDeProvaveisAmigos.contains(usr);
	}
	
	/**
	 * Remove um Usuario da sua lista de amigos
	 * @param usr
	 *          Usuario a ser removido
	 * @throws Exception
	 *         Caso Usuario seja igual a Null 
	 *         ou nao seja seu amigo
	 */
	
	public void removerAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		else if(!listaDeAmigos.contains(usr)){
			throw new Exception("Usuario nao eh seu amigo");
		}
		listaDeAmigos.remove(usr);
	}
	
	/**
	 * Remove um Usuario da sua lista de provaveis amigos
	 * @param usr
	 *          Usuario a ser removido
	 * @throws Exception
	 *         Caso Usuario seja igual a Null 
	 *         ou nao seja seu amigo
	 */
	public void removerProvavelAmigo(Usuario usr)throws Exception{
		if (usr==null){
			throw new Exception("Usuario nao pode ser igual a Null");
		}
		else if(!listaDeProvaveisAmigos.contains(usr)){
			throw new Exception("Usuario nao eh seu provavel amigo");
		}
		
		listaDeProvaveisAmigos.remove(usr);
	}
	
	
	/**
	 * Busca o perfil de um usuario na lista de amigos apartir de um login
	 * @param login Login do usuario a ser pesquisado
	 * @return Retorna o usuario caso seja encontrado ou null se nao achar nenhum usuario
	 * @throws Exception 
	 */
	public Usuario buscaPerfil(String login) throws Exception {
		if (!stringValida(login)) {
			throw new Exception("Login inválido");
		}
		for (Usuario usuario : getListaDeAmigos()) {
			if (usuario.getLogin().equalsIgnoreCase(login)) {

				return usuario;
			}
		}

		return null;
	}
	
	/**
	 * Recupera as Requisicoes de Amizades
	 * @return
	 *         As Requisicoes de Amizades
	 * @throws Exception
	 *        Caso nao haja nenhuma requisicao de Amizade
	 */
	
	public String getRequisicoesDeAmizade() throws Exception {

		if (getListaDeProvaveisAmigos().isEmpty()) {
			return ("Não há requisições");
		} else {
			String requisicoes = "";
			for (Usuario usr : getListaDeProvaveisAmigos()) {
				requisicoes += usr.getLogin() + "; ";
			}

			return formatarRequisicoes(requisicoes);
		}
	}
	
	/**
	 * Retorna a lista de amigos em forma de String
	 * @return Retorna a lista de amigos em forma de String
	 */
	public String stringDeAmigos(){
		if (getListaDeAmigos().isEmpty()){
			return "O usuário não possui amigos";
		}
		String resp = "";
		
		for (Usuario usr : getListaDeAmigos()){
			resp += usr.getLogin() + "; ";
		}
		
		return formatarRequisicoes(resp);
		
	}
	
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
	
	private boolean stringValida(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Desfaz a Amizade com um Amigo
	 * @param usuario
	 *          Usuario amigo
	 */
	public void desfazerAmizade (Usuario usuario){
		this.listaDeAmigos.remove(usuario);
	}
	
	/**
	 * Recupera o Amigo com mais alta Reputacao
	 */
	public Usuario amigoComReputacaoMaisAlta(){
		
		Usuario usuario = listaDeAmigos.get(0);
		for(Usuario usr: listaDeAmigos){
			if (usr.getReputacao()>usuario.getReputacao()){
				usuario = usr;
			}
		}return usuario;
		
	}
	
}
