package Classes;

import java.util.Random;

/**
 * Classe que representa um Item do Sistema
 * @author ARTHUR SENA, RODOLFO DE LIMA, IGOR GOMES, RENNAN PINTO
 */
public class Item {
	
	private String nome;
	private String descricao;
    private String categoria;
    private String outrasCategorias = "";
    private String idItem;
    private String idPedido;
    private Emprestimo emprestimo;
    
    /**
     * Inicia os Atributos das Classes
     * @param nome
     *         Nome do Item
     * @param descricao
     *          Descricao do Item
     * @param categoria
     *           Categoria do Item
     * @throws Exception
     *           Caso algum parametro seja invalido
     */
    public Item(String nome, String descricao, String categoria) throws Exception{
    	if (!stringValida(nome)){
			throw new Exception("Nome inválido");
		}
		else if (!stringValida(descricao)){
			throw new Exception("Descricao inválida");
		}
		else if (!stringValida(categoria)){
			throw new Exception("Categoria inválida");
		}
		else if(!categoriaValida(categoria)){
			throw new Exception("Categoria inexistente");
		}
    	
    	this.nome = nome;
    	this.descricao = descricao;
    	this.categoria = categoria;
    	this.idItem = gerarID();
    }
    
    /**
     * Recupera o nome do item
     * @return
     *        Nome do Item
     */
    public String getNome(){
    	return nome;
    }
    
    /**
     * Recupera a descricao do Item
     * @return
     *       Descricao do Item
    */   
    public String getDescricao(){
    	return descricao;
    }
    
    /**
     * Recupera as outras categorias do item
     * @return String com as categorias
     */
    public String getOutrasCategorias(){
    	return outrasCategorias;
    }
    
    /**
     * Recupera a Categoria do Item
     * @return
     *       Categoria do Item
     */
    public String getCategoria(){
    	return categoria;
    }
    
    /**
     * Recupera o ID do Item
     * @return
     *        ID do Item
     */ 
    public String getID(){
    	return idItem;
    }
    
    
    private String gerarID(){
    	idItem = getNome() + "-" + (new Random()).nextInt(1000);
		return idItem;
	}
    
    public void setID(String novoID){
    	this.idItem = novoID;
    }
    
    /**
     * Cria uma Requisicao de Emprestimo do Item
     * @param beneficiado
     *         Usuario que quer o item emprestado
     * @param duracao
     *          Tempo que ele pretende passa com o Item
     * @return
     *         ID de requisicao de Item
     * @throws Exception
     */
    public String criarRequisicaoEmprestimo(Usuario beneficiado, int duracao) throws Exception{
    	emprestimo = new Emprestimo(beneficiado, duracao);
    	emprestimo.requisitarEmprestimo();
    	return emprestimo.gerarIDRequisicao();
    }
    
    /**
     * Recupera o Emprestimo do Item
     * @return
     *        Emprestimo do Item
     */
    public Emprestimo getEmprestimo(){
    	return emprestimo;
    }
    
    /**
     * Diz se um Item eh igual a otro
     * @param it
     * @return True, caso seja igual e False, caso contrario
     */
    public boolean equals(Item it){
    	if (!(it instanceof Item)){
    		return false;
    	}
    	
    	return it.getID().equals(this.idItem);
    }
    
    /**
     * Recupera o ID pedido
     * @return Id pedido
     */
    public String getIdPedido() {
		return idPedido;
	}

    /**
     * Atribui um valor para o ID pedido
     * @param idPedido Id pedido
     */
	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}
	
	/**
	 * Gera um ID do pedido
	 * @return Sring com o ID do pedido
	 */
	public String gerarIdPedido(){
		this.idPedido = getNome() + "- Pedido - " + (new Random()).nextInt(1000);
		return idPedido;
	}
	
	public String gerarIdPedido(String nome2, String nome3, String nome4,
			String nome5) {
		String resposta = nome2 + "-" + nome3 + "-" + nome4 + "-" + nome5;
		this.idPedido = resposta;
		return idPedido;	
	}

	private boolean stringValida(String string){
        if (string==null || string.isEmpty()){
            return false;
        }
        return true;
    }
	
    private boolean categoriaValida(String categoria){
    	boolean resp = false;
    	if (categoria.toLowerCase().equals("livro") || categoria.toLowerCase().equals("filme") || categoria.toLowerCase().equals("jogo")){
    		resp = true;
    	}
    	else if(categoria.split(",").length>1){
    		
    		for(String categorias : categoria.split(",")){
    			outrasCategorias += categorias.trim() + "/";
    		}

    		resp = true;
    	}
    	return resp;
    }
    
    /**
     * Pesquisa um Item do Usuario
     * @param chave
     *       O parâmetro chave é a String a ser localizada no espaço de um atributo especificado.
     * @param atributo
     *        Atributo do Item
     * @return
     * 		Itens encontrados
     */
    public String pesquisa(String chave, String atributo){
    	
    	if (atributo.equals("nome")){
    		String[] resp = this.getNome().split(" ");
    		
    		for ( String string : resp ){
    			if (string.equalsIgnoreCase(chave)){
    				return this.getNome();
    			}    			
    		}
    	} else if (atributo.equals("descricao")){
    		String[] resp = this.getDescricao().split(" ");
    		
    		for ( String string : resp){
    			if (string.equalsIgnoreCase(chave)){
    				return this.getNome();
    			}
    		}
    	} else if (atributo.equals("categoria")){
    		if (this.getCategoria().equalsIgnoreCase(chave)){
    			
    			return this.getNome();
    		}
    		
    		if(!this.getOutrasCategorias().isEmpty()){
    			
    			for(String categorias : outrasCategorias.split("/")){
        			if(categorias.equalsIgnoreCase(chave)){
        				return this.getNome();
        			}
        		}
    		}
    		
    		
    		
    	} 	
    	return "";
    }
    
    
    /**
     * Finaliza o Emprestimo
     */
    public void acabarEmprestimo(){
    	emprestimo = null;
    }

	
}
