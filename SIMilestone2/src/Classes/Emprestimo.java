package Classes;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * Classe que representa um Emprestrimo criado por um Usuario
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENNAN PINTO, IGOR GOMES
 *
 */

public class Emprestimo {
	
	private Usuario beneficiado;
	private int duracao;
	private String idRequisicao;
	private String idEmprestimo;
	private boolean requisicaoEmprestimo;
	private boolean emprestimoAprovado;
	private boolean devolucao;
	private boolean devolvido;
	private boolean requisitarDevolucao;
	private List<Usuario> listaDeUsuariosInteressados;
	private String dataDeDevolucao;
	private GregorianCalendar calendario;
	private boolean isCancelado;
	private boolean isCompletado;
	private int metodoFoiChamado = 0;
	
	/**
	 * Inicia os atributos da classe
	 * @param beneficiado
	 *           Usuario Beneficiado do emprestimo
	 * @param duracao
	 * 			Tempo de duracao, em dias, do emprestimo
	 * @throws Exception
	 *         Caso a duracao seja menor ou igual a zero 
	 *         Caso o Usuario beneficiado seja igual a null
	 */
	public Emprestimo(Usuario beneficiado, int duracao)throws Exception{
		if (beneficiado==null){
			throw new Exception("Beneficiado nao pode ser igual a null");
		}
		else if(duracao<=0){
			throw new Exception("Duracao inválida");
		}
		
		this.listaDeUsuariosInteressados = new ArrayList<Usuario>();
		this.beneficiado = beneficiado;
		this.duracao = duracao;
		requisicaoEmprestimo  = true;
		emprestimoAprovado = false;
		devolucao = false;
		devolvido = false;
		requisitarDevolucao = false;
	}
	
	/**
	 * Recupera o Usuario beneficiado
	 * @return
	 *       Usuario beneficiado
	 */
	
	public Usuario getBeneficiado(){
		return beneficiado;
	}
	
	/**
	 * Recupera o ID de Requisicao de Emprestimo
	 * @return
	 *        ID de Requisicao de Emprestimo
	 */
	
	public String getIDRequisicao(){
		return idRequisicao;
	}
	
	/**
	 * Recupera o ID de Emprestimo
	 * @return
	 *        ID de Emprestimo
	 */
	
	public String getIDEmprestimo(){
		return idEmprestimo;
	}
	
	/**
	 * Diz se o emprestimo foi ou nao aprovado
	 * @return
	 *       Se o emprestimo foi ou nao aprovado
	 */
	public boolean emprestimoFoiAprovado(){
		return emprestimoAprovado;
	}
	
	/**
	 * Diz se o Item emprestado foi devolvido
	 * @return
	 *        Se o Item emrpestado foi devolvido
	 */
	public boolean isDevolucao() {
		return devolucao;
	}

	/**
	 * Altera a Devolucao do Item
	 * @param devolucao
	 *           Boolean que diz se o item foi ou nao devolvido
	 */
	public void setDevolucao(boolean devolucao) {
		if(devolucao){
			this.isCompletado = ((!isRequisitarDevolucao()));
		}
		this.devolucao = devolucao;		
	}

	/**
	 * Diz se o Item emprestado foi devolvido
	 * @return
	 *        Se o Item emrpestado foi devolvido
	 */
	public boolean isDevolvido() {
		return devolvido;
	}
	
	/**
	 * Altera a Devolucao do Item
	 * @param devolucao
	 *           Boolean que diz se o item foi ou nao devolvido
	 */
	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

	public String aprovarEmprestimo() throws Exception{
		emprestimoAprovado = true;
		requisicaoEmprestimo = false;
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.add(Calendar.DATE, this.duracao);
		this.dataDeDevolucao = DateFormat.getDateInstance().format(calendario.getTime());
		
		return gerarIDEmprestimo();
	}
	
	/**
	 * Gera o ID de Requisicao de Emprestimo
	 * @return
	 *        ID de Requisicao de Emprestimo
	 */
	public String gerarIDRequisicao(){
		idRequisicao = beneficiado.getID() + "Requisicao" + (new Random()).nextInt(1000);
		
		return idRequisicao;
	}
	
	/**
	 * Gera o ID de Emprestimo
	 * @return
	 *        ID de Emprestimo
	 */
	private String gerarIDEmprestimo(){
		idEmprestimo = beneficiado.getID() + "Emprestimo" + (new Random()).nextInt(1000);
		
		return idEmprestimo;
	}
	
	/**
	 * Requisita a Devolucao do Item
	 * @throws Exception
	 *            Caso a devolucao ja tenha sido requisitada
	 */
	public void requisitarDevolucao() throws Exception {
		if(requisitarDevolucao){
			throw new Exception ("Devolução já requisitada");
		}
		if(calendario==null){
			calendario = new  GregorianCalendar();
		}
		
		this.requisitarDevolucao = true;
	}
			
	/**
	 * Informa se foi requisitado a devolucao do Item
	 * @return
	 *       True, caso foi requisitado a devolucao
	 *       False, caso contrario
	 */
	public boolean isRequisitarDevolucao() {
		return requisitarDevolucao;
	}
	
	/**
	 * Retorna a lista de Usuarios interessados no item
	 * @return
	 *          lista de Usuarios interessados no item
	 */
	public List<Usuario> getListaDeUsuariosInteressados() {
		return listaDeUsuariosInteressados;
	}

	/**
	 * Registra interesse em pedir emprestado o Item
	 * @param usuario
	 *         Usuario interessado no Item
	 * @throws Exception
	 *         Caso o usuario ja tenha registrado interesse 
	 */
	public void registrarInteresse (Usuario usuario) throws Exception{
		if (getListaDeUsuariosInteressados().contains(usuario)){
			throw new Exception("O usuário já registrou interesse neste item");
		} else {
			this.listaDeUsuariosInteressados.add(usuario);
		}
	}
	
	/**
	 * Ve se o Tempo de Emprestimo nao Expiro
	 * @return
	 *        True, caso tenha expirado
	 *        False, caso contrario
	 */
	public boolean tempoEmprestimoNaoExpiro(){
		boolean retorno = false;
		String[] data1 = this.dataDeDevolucao.split("/");
		String[] dataHJ = DateFormat.getDateInstance().format(this.calendario.getTime()).split("/");
		
		if (this.metodoFoiChamado >= 1){
			return isCancelado;
		}
		
		if(this.dataDeDevolucao.equals(DateFormat.getDateInstance().format(this.calendario.getTime()))){
			retorno= false;
		}
		
		else if (data1[2].equals(dataHJ[2])){
			if (data1[1].equals(dataHJ[1])){
				if (Integer.parseInt(data1[0])>=(Integer.parseInt(dataHJ[0]))){
					retorno = true;
				}
				else{
					retorno = false;
				}
			}
		}
		
		else if (data1[2].equals(dataHJ[2])){
			
			if (Integer.parseInt(data1[1])>=(Integer.parseInt(dataHJ[1]))){
				retorno =true;
			}
			else{
				retorno = false;
			}
		}
		
		else if(Integer.parseInt(data1[2])>=(Integer.parseInt(dataHJ[2]))){
			retorno =true;
		}
		else{
			retorno = false;
		}
		
		this.isCancelado = retorno;
		this.metodoFoiChamado++;
		return retorno;
	}
	
	/**
	 * Adiciona a passagem do tempo
	 * @param dias
	 *           Dias a serem adicionas
	 */
	public void adicionarDias(int dias){
		if (calendario==null){
			calendario = new GregorianCalendar();			
		}
		calendario.add(Calendar.DATE, dias);
	}
	
	/**
	 * Requisita o emprestimo
	 */
	public void requisitarEmprestimo(){
		requisicaoEmprestimo = true;
	}
	
	/**
	 * Diz se o emprestimo foi ou nao requisitado por um Usuario
	 * @return
	 *       True, caso o emprestimo tenha sido requisitado
	 *       False, caso contrario
	 */
	public boolean emprestimoFoiRequisitado(){
		return requisicaoEmprestimo;
	}
	
	/**
	 * Diz se o Emprestimo foi ou nao completado
	 * @return True, caso o emprestimo foi completado
	 *         False, caso contrario
	 */
	public boolean foiCompletado(){
		return isCompletado;
	}

	/**
	 * Cancela um pedido de requisicao de emprestimo
	 */
	public void cancelarRequisicaoEmprestimo(){
		requisicaoEmprestimo = false;
		idEmprestimo = "";
	}
}

