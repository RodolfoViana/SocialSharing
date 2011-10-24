//package Testes;
//
//import junit.framework.Assert;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import Classes.Emprestimo;
//import Classes.GerenciadorItens;
//import Classes.Item;
//import Classes.Usuario;
//
//public class TesteGerenciadorDeItem {
//	
//	GerenciadorItens gerenciadorItens, gerenciadorItens2;
//	Item item, item2, item3, item4;
//	Emprestimo emprestimo, emprestimo2;
//	Usuario usuario, usuario2, usuario3;
//	
//	@Before public void construtor() throws Exception{
//		gerenciadorItens = new GerenciadorItens();
//		gerenciadorItens2 = new GerenciadorItens();
//		
//		item = new Item("Nome do item", "descricao do item", "livro");
//		item2 = new Item("Nome do item2", "descricao do item2", "filme");
//		item3 = new Item("Nome do item3", "descricao do item3", "jogo");
//		item4 = new Item("Nome do item4", "descricao do item4", "filme");
//
//		usuario = new Usuario("nome usuario1", "login usuario1", "endereco usuario1");
//		usuario2 = new Usuario("nome usuario2", "login usuario2", "endereco usuario2");
//		usuario3 = new Usuario("nome usuario3", "login usuario3", "endereco usuario3");
//		
//		emprestimo = new Emprestimo(usuario, 10);
//		emprestimo2 = new Emprestimo(usuario2, 5);
//		
//	}
//	
//	@Test public void testaAdicionarItem() throws Exception{
//		Assert.assertTrue(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getItensPraDevolver().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getListaMeusItens().isEmpty());
//		
//		
//		gerenciadorItens.adicionarItem(item);
//		Assert.assertFalse(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertEquals(1, gerenciadorItens.getQuantidadeMeusItens());
//		
//		gerenciadorItens.adicionarItem(item2);
//		Assert.assertFalse(gerenciadorItens.getListaMeusItens().isEmpty());
//		Assert.assertEquals(2, gerenciadorItens.getQuantidadeMeusItens());
//		
//	}
//	
//	@Test public void testaGetAtributoItem() throws Exception{
//		Assert.assertTrue(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getItensPraDevolver().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getListaMeusItens().isEmpty());
//		
//		gerenciadorItens.adicionarItem(item);
//		gerenciadorItens.adicionarItem(item2);
//		gerenciadorItens.adicionarItem(item3);
//		gerenciadorItens.adicionarItem(item4);
//		
//		String iditem1 = item.getID();
//		String iditem2 = item2.getID();
//		String iditem3 = item3.getID();
//		String iditem4 = item4.getID();
//		
//		Assert.assertFalse(gerenciadorItens.getListaMeusItens().isEmpty());
//		Assert.assertEquals(4, gerenciadorItens.getQuantidadeMeusItens());
//		
//		Assert.assertEquals(item.getNome(), gerenciadorItens.getAtributoItem(iditem1, "nome"));
//		Assert.assertEquals(item2.getDescricao(), gerenciadorItens.getAtributoItem(iditem2, "descricao"));
//		Assert.assertEquals(item3.getCategoria(), gerenciadorItens.getAtributoItem(iditem3, "categoria"));
//		Assert.assertEquals(item4.getDescricao(), gerenciadorItens.getAtributoItem(iditem4, "descricao"));
//	}
//	
//	@Test public void testaStringDeItens() throws Exception{
//		Assert.assertTrue(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getItensPraDevolver().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getListaMeusItens().isEmpty());
//		
//		gerenciadorItens.adicionarItem(item);
//		gerenciadorItens.adicionarItem(item2);
//		
//		Assert.assertFalse(gerenciadorItens.getListaMeusItens().isEmpty());
//		Assert.assertEquals(2, gerenciadorItens.getQuantidadeMeusItens());
//		
//		Assert.assertEquals("Nome do item; Nome do item2", gerenciadorItens.stringDeItens());
//	}
//	
//	@Test public void testaRequisitarEmprestimos() throws Exception{
//		Assert.assertTrue(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getItensPraDevolver().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getListaMeusItens().isEmpty());
//		
//		gerenciadorItens.adicionarItem(item);
//		gerenciadorItens.adicionarItem(item2);
//		
//		Assert.assertEquals(2, gerenciadorItens.getItensPraEmprestar().size());
//
//		gerenciadorItens.requisitarEmprestimos(usuario, item.getID(), 10);
//	}
//	
//	@Test public void testaAprovarRequisicaoEmprestimo() throws Exception{
//		
//		Assert.assertTrue(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getItensPraDevolver().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getListaMeusItens().isEmpty());
//		
//		gerenciadorItens.adicionarItem(item);
//		gerenciadorItens.adicionarItem(item2);
//		
//		Assert.assertEquals(2, gerenciadorItens.getItensPraEmprestar().size());
//
//		String idRequisicaoEmprestimo = gerenciadorItens.requisitarEmprestimos(usuario, item.getID(), 10);
//		
//		gerenciadorItens.aprovarRequisicaoEmprestimo(true, true, true, idRequisicaoEmprestimo);
//		
//		Assert.assertEquals(1, gerenciadorItens.getItensPraEmprestar().size());
//		
//	}
//	
//	@Test public void testaConfirmarTerminoEmprestimo() throws Exception{
//		
//		Assert.assertTrue(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getItensPraDevolver().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getListaMeusItens().isEmpty());
//		
//		gerenciadorItens.adicionarItem(item);
//		gerenciadorItens.adicionarItem(item2);
//		
//		Assert.assertEquals(2, gerenciadorItens.getItensPraEmprestar().size());
//
//		String idRequisicaoEmprestimo = gerenciadorItens.requisitarEmprestimos(usuario, item.getID(), 10);
//		
//		gerenciadorItens.aprovarRequisicaoEmprestimo(true, true, true, idRequisicaoEmprestimo);
//		
//		Assert.assertEquals(1, gerenciadorItens.getItensPraEmprestar().size());
//		
//		gerenciadorItens.confirmarTerminoEmprestimo(item);
//		
//		Assert.assertEquals(2, gerenciadorItens.getItensPraEmprestar().size());
//	}
//	
//	@Test public void testaApagarItem() throws Exception{
//		
//		Assert.assertTrue(gerenciadorItens.getItensPraEmprestar().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getItensPraDevolver().isEmpty());
//		Assert.assertTrue(gerenciadorItens.getListaMeusItens().isEmpty());
//		
//		gerenciadorItens.adicionarItem(item);
//		gerenciadorItens.adicionarItem(item2);
//		
//		Assert.assertEquals(2, gerenciadorItens.getItensPraEmprestar().size());
//		
//		gerenciadorItens.apagarItem(item);
//		
//		Assert.assertEquals(1, gerenciadorItens.getItensPraEmprestar().size());
//
//		
//	}
//	
//	
//}