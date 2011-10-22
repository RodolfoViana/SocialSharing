package Classes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que guarda as Mensagens de um Usuario
 * 
 * @author ARTHUR SENA, IGOR GOMES, RENAN PINTO, RODOLFO DE LIMA
 * 
 */

public class GerenciadorMensagens {

	private List<Mensagem> listaDeMensagens;

	/**
	 * Inicia os Atributos da Classe
	 */

	public GerenciadorMensagens() {
		listaDeMensagens = new LinkedList<Mensagem>();
	}

	/**
	 * Recupera a Lista com todas as Mensagens Recebida do Usuario
	 * 
	 * @return Uma Lista com todas as mensagens do Usuario
	 */

	public List<Mensagem> getListaDeMensagens() {
		return listaDeMensagens;
	}

	/**
	 * Adiciona mensagem
	 * 
	 * @param mensagem
	 *            mensagem a ser adicionada
	 */
	public void addMensagem(Mensagem mensagem) {
		this.getListaDeMensagens().add(mensagem);
	}

	private boolean assuntoExiste(String assunto) {
		Iterator<Mensagem> it = getListaDeMensagens().iterator();
		Mensagem msg;

		while (it.hasNext()) {
			msg = it.next();

			if (msg.getAssunto().equals(assunto)) {
				return true;
			}
		}
		return false;
	}

	private boolean assuntoExisteDestinatario(Usuario usuarioDestinatario,
			String assunto) {
		Iterator<Mensagem> it = usuarioDestinatario.getGerenciadorMensagens()
				.getListaDeMensagens().iterator();
		Mensagem msg;

		while (it.hasNext()) {
			msg = it.next();
			if (msg.getAssunto().equals(assunto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Responsavel por enviar mensagem
	 * 
	 * @param destinatario
	 *            Destinatario da mensagem
	 * @param assunto
	 *            Assundo da mensagem
	 * @param mensagem
	 *            Corpo da mensagem
	 * @return Id da mensagem
	 */
	public String enviarMensagem(Usuario destinatario, String assunto,
			String mensagem) {
		Iterator<Mensagem> it = getListaDeMensagens().iterator();
		Iterator<Mensagem> it3 = destinatario.getGerenciadorMensagens()
				.getListaDeMensagens().iterator();
		Mensagem msg, msg2;
		Mensagem msgReturn = null;

		if (assuntoExiste(assunto)
				&& assuntoExisteDestinatario(destinatario, assunto)) {

			while (it.hasNext()) {
				msg = it.next();
				if (msg.getAssunto().equals(assunto)) {
					msg.addMensagem(mensagem);
					msgReturn = msg;

				}
			}
		}

		else {

			if (assuntoExiste(assunto)) {

				while (it.hasNext()) {
					msg = it.next();
					if (msg.getAssunto().equals(assunto)) {
						msg.addMensagem(mensagem);
						msgReturn = msg;

					}
				}
			}

			if (assuntoExisteDestinatario(destinatario, assunto)) {

				while (it3.hasNext()) {
					msg2 = it3.next();
					if (msg2.getAssunto().equals(assunto)) {
						msg2.addMensagem(mensagem);
					}
				}
			}

			else {
				Mensagem msg3 = new Mensagem(destinatario, assunto, mensagem);
				this.addMensagem(msg3);
				destinatario.getGerenciadorMensagens().addMensagem(msg3);
				msgReturn = msg3;
			}
		}
		return msgReturn.getIdMensagem();
	}

	/**
	 * Responsavel por enviar mensagem
	 * 
	 * @param destinatario
	 *            Destinatario da mensagem
	 * @param assunto
	 *            Assundo da mensagem
	 * @param mensagem
	 *            Corpo da mensagem
	 * @param idRequisicaoEmprestimo
	 *            Id de requicao de emprestimo
	 * @return Id da mensagem
	 */
	public String enviarMensagem(Usuario destinatario, String assunto,
			String mensagem, String idRequisicaoEmprestimo) {
		Mensagem msgReturn = null;

		if (assuntoExiste(assunto)) {
			for (Mensagem msg : this.getListaDeMensagens()) {
				if (msg.getAssunto().equals(assunto)) {
					msg.addMensagem(mensagem);
					msgReturn = msg;
				}
			}
		} else {

			Mensagem msg = new Mensagem(destinatario, assunto, mensagem,
					idRequisicaoEmprestimo);
			this.addMensagem(msg);
			destinatario.getGerenciadorMensagens().addMensagem(msg);
			msgReturn = msg;

		}
		return msgReturn.getIdMensagem();
	}

	private String formatarRequisicoes(String requisicoes) {
		String retorno = "";
		for (int i = 0; i < requisicoes.split("; ").length; i++) {
			if (i == requisicoes.split("; ").length - 1) {
				retorno += requisicoes.split("; ")[i];
				break;
			}
			retorno += requisicoes.split("; ")[i] + "; ";

		}
		return retorno;
	}

	/**
	 * Reponsavel por retornar os topicos da mensagem
	 * 
	 * @param tipo
	 *            Tipo da mensagem
	 * @return Topicos das menagens
	 * @throws Exception
	 *             Caso o tipo informado seja invalido
	 */
	public String lerTopicos(String tipo) throws Exception {
		String repString = "";

		if (tipo == null || tipo.isEmpty()) {
			throw new Exception("Tipo inválido");
		}

		else if (!tipo.equals("negociacao") && !tipo.equals("offtopic")
				&& !tipo.equals("todos")) {
			throw new Exception("Tipo inexistente");
		}

		if (this.getListaDeMensagens().size() > 1) {

			for (int i = this.getListaDeMensagens().size() - 1; i >= 0; i--) {
				Mensagem msg = this.getListaDeMensagens().get(i);
				if (msg.getTipoDaMensagem().equals(tipo)
						|| tipo.equals("todos")) {
					repString += msg.getAssunto() + "; ";
				}

			}

			repString = formatarRequisicoes(repString);

		} else {
			for (int i = this.getListaDeMensagens().size() - 1; i >= 0; i--) {
				Mensagem msg = this.getListaDeMensagens().get(i);

				if (msg.getTipoDaMensagem().equals(tipo)
						|| tipo.equals("todos")) {
					repString += msg.getAssunto();

				}
			}
		}

		if (repString.equals("")) {
			repString = "Não há tópicos criados";
		}
		return repString;

	}

	/**
	 * Reponsavel por ler mensagens
	 * 
	 * @param resp
	 *            Verifica se o topico existe
	 * @param resp2
	 *            Verifica se tem permissao
	 * @param idTopico
	 *            Id do topico
	 * @return Corpo da mensagem encontrada
	 * @throws Exception
	 *             Caso algum parametro seja invalido
	 */
	public String lerMensagens(boolean resp, boolean resp2, String idTopico)
			throws Exception {
		String reString = "";
		Iterator<Mensagem> it = getListaDeMensagens().iterator();
		Mensagem msg;
		
				
		
		if (idTopico == null || idTopico.isEmpty()) {
			throw new Exception("Identificador do tópico é inválido");
		}
		
		while (it.hasNext()){
			msg = it.next();
			if (msg.getIdMensagem().equals(idTopico)) {
				reString += msg.getCorpoDaMensagem();
			}
		}
		if (resp2) {
			throw new Exception("Tópico inexistente");
		}
		if (resp) {
			throw new Exception(
					"O usuário não tem permissão para ler as mensagens deste tópico");
		}

		return reString;
	}

}
