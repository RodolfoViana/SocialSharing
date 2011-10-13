package Testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Classes.Emprestimo;
import Classes.Mensagem;
import Classes.Usuario;

public class TesteMensagem {
	
	Mensagem mensagem, mensagem2;
	Usuario usuario, usuario2;
	
	@Before public void construtor() throws Exception{
		usuario = new Usuario("Nome do usuario", "login do usuario", "endereco do usuario");
		usuario2 = new Usuario("Nome do usuario 2", "login do usuario 2", "endereco do usuario 2");
		Emprestimo emprestimo = new Emprestimo(usuario, 10);
		
		mensagem = new Mensagem(usuario, "Assundo da mensagem", "Mensagem de teste");
		mensagem2 = new Mensagem(usuario2, "Assundo da segunda mensagem", "Mensagem teste 2", emprestimo.getIDRequisicao());
	}
	
	@Test public void testaTipoMensagem(){
		Assert.assertEquals("Erro no tipo da mensagem", "offtopic", mensagem.getTipoDaMensagem());
		Assert.assertEquals("Erro no tipo da mensagem", "negociacao", mensagem2.getTipoDaMensagem());
		
	}
	
	@Test public void testaDestinatario(){
		Assert.assertEquals("Erro no destinatario", usuario, mensagem.getDestinatario());
		Assert.assertEquals("Erro no destinatario", usuario2, mensagem2.getDestinatario());
	}
	
	@Test public void testaCorpoMensagem(){
		Assert.assertEquals("Erro na mensagem", "Mensagem de teste", mensagem.getCorpoDaMensagem());
		Assert.assertEquals("Erro na mensagem", "Mensagem teste 2", mensagem2.getCorpoDaMensagem());
	}
	
	
	@Test public void testaAssuntoMensagem(){
		Assert.assertEquals("Erro na mensagem", "Assundo da mensagem", mensagem.getAssunto());
		Assert.assertEquals("Erro na mensagem", "Assundo da segunda mensagem", mensagem2.getAssunto());
	}
	
	@Test public void testaAddMensagem(){
		Assert.assertEquals("Erro na mensagem", "Mensagem de teste", mensagem.getCorpoDaMensagem());
		Assert.assertEquals("Erro na mensagem", "Mensagem teste 2", mensagem2.getCorpoDaMensagem());
		
		mensagem.addMensagem("Adicionando mensagem");
		mensagem2.addMensagem("Adicionando na segunda mensagem");
		
		Assert.assertEquals("Erro na mensagem", "Mensagem de teste; Adicionando mensagem", mensagem.getCorpoDaMensagem());
		Assert.assertEquals("Erro na mensagem", "Mensagem teste 2; Adicionando na segunda mensagem", mensagem2.getCorpoDaMensagem());
	}

}
