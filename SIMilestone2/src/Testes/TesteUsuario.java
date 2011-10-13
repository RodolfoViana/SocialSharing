package Testes;


import Classes.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteUsuario {
	
	Usuario usuario;
	
	@Before public void construtor() throws Exception{
		usuario = new Usuario("Teste do nome", "teste do login", "endereco do usuario");
	}
	
	@Test public void testaVisualizarPerfil(){
		Assert.assertEquals("Erro no visualizar perfil", "Teste do nome - endereco do usuario", usuario.visualizarPerfil());
	}
	
	@Test public void testaEquals() throws Exception{
		Usuario usuario2 = new Usuario("Nome de teste", "login2 ", "endereco 2");
		Usuario usuario3 = new Usuario("Nome3", "login3 ", "endereco 3");
		Usuario usuario4 = new Usuario("Nome de teste", "login3 ", "endereco 2");
		Object obj = new Object();
		
		Assert.assertTrue("Erro no equals", usuario.equals(usuario));
		Assert.assertFalse("Erro no equals", usuario2.equals(usuario3));
		Assert.assertFalse("Erro no equals", usuario3.equals(usuario4));
		Assert.assertFalse("Erro no equals", usuario2.equals(usuario4));
		Assert.assertFalse("Erro no equals", usuario2.equals(obj));
	}
	
	@Test public void testaExceptionConstrutor(){
		
		try {
			@SuppressWarnings("unused")
			Usuario usuario5 = new Usuario("", "login teste", "endereco teste");
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro devia dar nome invalido", "Nome inválido", e.getMessage());
		}
		
		try {
			@SuppressWarnings("unused")
			Usuario usuario5 = new Usuario("nome teste", "", "endereco teste");
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro devia dar login invalido", "Login inválido", e.getMessage());
		}
		
		try {
			@SuppressWarnings("unused")
			Usuario usuario5 = new Usuario("nome teste", "login teste", "");
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro devia dar endereco invalido", "Endereco inválido", e.getMessage());
		}
		
	}
	

}
