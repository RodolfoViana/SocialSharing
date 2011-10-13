package Testes;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Classes.Emprestimo;
import Classes.GerenciadorMensagens;
import Classes.Mensagem;
import Classes.Usuario;

public class TesteGerenciadorDeMensagem {
	
	Mensagem mensagem, mensagem2, mensagem3;
	GerenciadorMensagens gerenciadorMensagens, gerenciadorMensagens2;
	Usuario usuario, usuario2, usuario3;
	Emprestimo emprestimo;
	
	@Before public void construtor() throws Exception{
		gerenciadorMensagens = new GerenciadorMensagens();
		gerenciadorMensagens2 = new GerenciadorMensagens();
		
		usuario = new Usuario("Nome1", "login1", "endereco1");
		usuario2 = new Usuario("Nome2", "login2", "endereco2");
		usuario3 = new Usuario("Nome3", "login3", "endereco3");
		
		
		emprestimo = new Emprestimo(usuario3, 10);
		
		mensagem = new Mensagem(usuario, "Assundo1", "Mensagem1");
		mensagem2 = new Mensagem(usuario3, "Assundo2", "Mensagem2", emprestimo.getIDRequisicao());
		mensagem3 =  new Mensagem(usuario2, "Assundo3", "Mensagem3");
	}
	
	@Test public void testaListaDeMensagens(){
		List<Mensagem> lista = new ArrayList<Mensagem>();
		
		Assert.assertTrue("Erro na lista de mensagem", gerenciadorMensagens.getListaDeMensagens().isEmpty());
		
		gerenciadorMensagens.addMensagem(mensagem);
		lista.add(mensagem);
		Assert.assertFalse("Erro na lista de mensagem", gerenciadorMensagens.getListaDeMensagens().isEmpty());
		
		Assert.assertEquals("Erro na lista de mensagem", lista, gerenciadorMensagens.getListaDeMensagens());
	}
	
	@Test public void testaEnviarMensagem(){
		gerenciadorMensagens2.enviarMensagem(usuario2, "Assundo da mensagem", "Adiciona mensagem");
		gerenciadorMensagens2.enviarMensagem(usuario, "Assundo da mensagem", "Adiciona mensagem");
	}
	
	

}
