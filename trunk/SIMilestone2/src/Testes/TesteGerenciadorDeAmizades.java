package Testes;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import Classes.GerenciadorAmizades;
import Classes.Usuario;

public class TesteGerenciadorDeAmizades {
	
	GerenciadorAmizades gerenciadorAmizades;
	Usuario usuario, usuario2, usuario3;
	
	@Before public void construtor() throws Exception{
		gerenciadorAmizades = new GerenciadorAmizades();
		
		usuario = new Usuario("Nome1", "login1", "endereco1");
		usuario2 = new Usuario("Nome2", "login2", "endereco2");
		usuario3 = new Usuario("Nome3", "login3", "endereco3");
	}
	
	@Test public void testaAdicionarAmigoException() throws Exception{
		gerenciadorAmizades.adicionarNovoAmigo(usuario);
		
		try {
			gerenciadorAmizades.adicionarNovoAmigo(usuario);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar amigo novamente", "Os usuários já são amigos", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.adicionarNovoAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar amigo null", "Usuario nao pode ser igual a null", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.adicionarAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar null", "Usuario nao pode ser igual a null", e.getMessage());
		}
	}
	
	@Test public void testaAdicionarProvavelAmigo(){
		try {
			gerenciadorAmizades.adicionarProvavelAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar provavelm amigo", "Usuario nao pode ser igual a null", e.getMessage());
		}
	}
	
	
	@Test public void testaEhMeuAmigo(){
		try {
			gerenciadorAmizades.ehMeuAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro no eh amigo", "Usuario nao pode ser igual a Null", e.getMessage());
		}
	}
	
	@Test public void testaAdicionarAmigo2Exception() throws Exception{
		gerenciadorAmizades.adicionarNovoAmigo(usuario);
		
		try {
			gerenciadorAmizades.adicionarAmigo(usuario);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar amigo novamente", "Os usuários já são amigos", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.adicionarAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar amigo null", "Usuario nao pode ser igual a null", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.adicionarAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Erro ao adicionar null", "Usuario nao pode ser igual a null", e.getMessage());
		}
	}
	
	@Test public void testaEhUmProvavelAmigo() throws Exception{
		Assert.assertFalse("Erro ao testar provavel amigo", gerenciadorAmizades.ehUmProvavelAmigo(usuario));
		gerenciadorAmizades.adicionarProvavelAmigo(usuario);
		Assert.assertTrue("Erro ao testar provavel amigo", gerenciadorAmizades.ehUmProvavelAmigo(usuario));
	}
	
	@Test public void testaEhUmProvavelAmigoException() throws Exception{
		
		try {
			Assert.assertFalse("Erro ao testar provavel amigo", gerenciadorAmizades.ehUmProvavelAmigo(null));
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Devia lancar erro", "Usuario nao pode ser igual a Null", e.getMessage());
		}
		
	}
	
	@Test public void testaRemoverAmigo() throws Exception{
		Assert.assertFalse("Erro no eh meu amigo", gerenciadorAmizades.ehMeuAmigo(usuario));
		gerenciadorAmizades.adicionarNovoAmigo(usuario);
		Assert.assertTrue("Erro no eh meu amigo", gerenciadorAmizades.ehMeuAmigo(usuario));
		
		gerenciadorAmizades.removerAmigo(usuario);
		Assert.assertFalse("Erro no eh meu amigo", gerenciadorAmizades.ehMeuAmigo(usuario));
		
	}
	
	@Test public void testaRemoverAmigoException(){
		
		try {
			gerenciadorAmizades.removerAmigo(usuario);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Usuario nao eh seu amigo", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.removerAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Usuario nao pode ser igual a Null", e.getMessage());
		}
		
	}
	
	@Test public void testaRemoverProvavelAmigo() throws Exception{
		Assert.assertFalse("Erro no eh meu amigo", gerenciadorAmizades.ehUmProvavelAmigo(usuario));
		gerenciadorAmizades.adicionarProvavelAmigo(usuario);
		Assert.assertTrue("Erro no eh meu amigo", gerenciadorAmizades.ehUmProvavelAmigo(usuario));
		
		gerenciadorAmizades.removerProvavelAmigo(usuario);
		Assert.assertFalse("Erro no eh meu amigo", gerenciadorAmizades.ehUmProvavelAmigo(usuario));
		
	}
	
	@Test public void testaRemoverProvavelAmigoException(){
		
		try {
			gerenciadorAmizades.removerProvavelAmigo(usuario);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Usuario nao eh seu provavel amigo", e.getMessage());
		}
		
		try {
			gerenciadorAmizades.removerProvavelAmigo(null);
			Assert.fail();
		} catch (Exception e){
			Assert.assertEquals("Usuario nao pode ser igual a Null", e.getMessage());
		}
		
	}
	
	@Test public void testaBuscaPerfil() throws Exception{
		gerenciadorAmizades.adicionarNovoAmigo(usuario);
		
		Assert.assertEquals(usuario, gerenciadorAmizades.buscaPerfil(usuario.getLogin()));
		Assert.assertEquals(null, gerenciadorAmizades.buscaPerfil("abc"));
	}
	
	@Test public void testaBuscarPerfilException(){
		
		try {
			gerenciadorAmizades.buscaPerfil("");
			Assert.fail();
		}	catch (Exception e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		
	}
	
	@Test public void testaDesfazerAmizade() throws Exception{
		Assert.assertFalse("Erro no eh meu amigo", gerenciadorAmizades.ehMeuAmigo(usuario));
		gerenciadorAmizades.adicionarNovoAmigo(usuario);
		Assert.assertTrue("Erro no eh meu amigo", gerenciadorAmizades.ehMeuAmigo(usuario));
		
		gerenciadorAmizades.desfazerAmizade(usuario);
		Assert.assertFalse("Erro no eh meu amigo", gerenciadorAmizades.ehMeuAmigo(usuario));
		
	}

}
