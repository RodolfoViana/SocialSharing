package Classes;

/**
 * Classe que Representa um Pedido de um Item
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENNAN PINTO, IGOR CRUZ
 *
 */

public class PedidoItem {
	
	String pedido;
	String idPedido;
	String descricaoItem;
	String nomeIt;
	
	/**
	 * Construtor da Classe
	 * @param pedido
	 *        O pedido feito
	 * @param idPedido
	 *        O id do Pedido
	 * @param nomeit
	 *        O nome do Item
	 * @param descricaoItem
	 *        A descricao do Item
	 */
	
	public PedidoItem(String pedido, String idPedido, String nomeit, String descricaoItem){
		this.idPedido = idPedido;
		this.pedido = pedido;
		this.nomeIt = nomeit;
		this.descricaoItem = descricaoItem;
	}
	
	/**
	 * Recupera o Pedido
	 * @return
	 *       Pedido
	 */
	public String getPedido(){
		return pedido;
	}
	
	/**
	 * Recupera o ID do Pedido
	 * @return
	 *        ID do Pedido
	 */
	public String getIdPedido(){
		return idPedido;
	}
	
}
