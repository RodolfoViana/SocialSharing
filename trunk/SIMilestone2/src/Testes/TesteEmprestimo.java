package Testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Classes.Emprestimo;
import Classes.Usuario;

public class TesteEmprestimo {
	
	Emprestimo emprestimo;
	Usuario usuario;
	
	@Before public void construtor() throws Exception{
		usuario = new Usuario("Nome do usuario", "login do usuario", "endereco do usuario");
		emprestimo = new Emprestimo(usuario, 5);
	}
	
	@Test public void testaConstrutorException(){
		
		try {
			emprestimo = new Emprestimo(null, 5);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Devia lancar erro", "Beneficiado nao pode ser igual a null", e.getMessage());
		}
		
		try {
			emprestimo = new Emprestimo(usuario, -8);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Devia lancar erro", "Duracao inválida", e.getMessage());
		}
	}
	
	@Test public void testaGetBeneficiado(){
		Assert.assertEquals("Erro no get usuario", usuario, emprestimo.getBeneficiado());
	}
	
	@Test public void testaGetListaDeUsuariosInteressados(){
		Assert.assertTrue("Erro no get lista de interessados", emprestimo.getListaDeUsuariosInteressados().isEmpty());
	}
	
	@Test public void testaRegistrarInteresse() throws Exception{
		Usuario usuario2 = new Usuario("Usuario 2", "login usuario 2", "endereco usuario 2");
		List<Usuario> lista = new ArrayList<Usuario>();
		
		lista.add(usuario2);
		
		Assert.assertTrue("Erro no get lista de interessados", emprestimo.getListaDeUsuariosInteressados().isEmpty());
		
		emprestimo.registrarInteresse(usuario2);
		Assert.assertFalse("Erro no get lista de interessados", emprestimo.getListaDeUsuariosInteressados().isEmpty());
		
		Assert.assertEquals("Erro na lista de interessados", lista, emprestimo.getListaDeUsuariosInteressados());
	}
	
	@Test public void testaRegistrarInteresseException() throws Exception{
		Usuario usuario2 = new Usuario("Usuario 2", "login usuario 2", "endereco usuario 2");
		List<Usuario> lista = new ArrayList<Usuario>();
		
		
		Assert.assertTrue("Erro no get lista de interessados", emprestimo.getListaDeUsuariosInteressados().isEmpty());
		
		lista.add(usuario2);
		
		Assert.assertTrue("Erro no get lista de interessados", emprestimo.getListaDeUsuariosInteressados().isEmpty());
		
		emprestimo.registrarInteresse(usuario2);
		Assert.assertFalse("Erro no get lista de interessados", emprestimo.getListaDeUsuariosInteressados().isEmpty());
		
		Assert.assertEquals("Erro na lista de interessados", lista, emprestimo.getListaDeUsuariosInteressados());
	
		
		try {
			emprestimo.registrarInteresse(usuario2);
			Assert.fail();

		} catch (Exception e){
			Assert.assertEquals("Devia lancar um erro", "O usuário já registrou interesse neste item", e.getMessage());
		}	
	}
	
	@Test public void testaAprovarEmprestimo() throws Exception{
		Assert.assertFalse("Erro ao aprovar emprestimo", emprestimo.emprestimoFoiAprovado());
		
		emprestimo.aprovarEmprestimo();
		Assert.assertTrue("Erro ao aprovar emprestimo", emprestimo.emprestimoFoiAprovado());
	}
	
	@Test public void testaRequisitarDevolucao() throws Exception{
		Assert.assertFalse("Erro ao requisitar devolucao", emprestimo.isRequisitarDevolucao());
		
		emprestimo.requisitarDevolucao();
		Assert.assertTrue("Erro ao requisitar devolucao", emprestimo.isRequisitarDevolucao());
	}
	
	@Test public void testaRequisitarDevolucaoException() throws Exception{
		Assert.assertFalse("Erro ao requisitar devolucao", emprestimo.isRequisitarDevolucao());
		
		emprestimo.requisitarDevolucao();
		Assert.assertTrue("Erro ao requisitar devolucao", emprestimo.isRequisitarDevolucao());
		
		try {
			emprestimo.requisitarDevolucao();
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Devia lancar erro", "Devolução já requisitada", e.getMessage());
		}
	}
	
	@Test public void testaCancelarRequisicaoEmprestimo(){
		String idEmprestimo = emprestimo.getIDRequisicao();
		
		Assert.assertEquals("Erro no id de requisicao", idEmprestimo, emprestimo.getIDRequisicao());
		Assert.assertTrue("Erro na requisicao de emprestimo", emprestimo.emprestimoFoiRequisitado());
		
		emprestimo.cancelarRequisicaoEmprestimo();
		
		Assert.assertEquals("Erro no id de requisicao", null, emprestimo.getIDRequisicao());
		Assert.assertFalse("Erro na requisicao de emprestimo", emprestimo.emprestimoFoiRequisitado());
		
	}
	
//	@Test public void testaTempoEmprestimoNaoExpiro() throws Exception{
//		emprestimo.aprovarEmprestimo();
//		
//		Assert.assertFalse("Erro no tempo de emprestimo", emprestimo.tempoEmprestimoNaoExpiro());
//	}
	
	

}
