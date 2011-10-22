package Classes;

public class PedidoItem {
	
	String pedido;
	String idPedido;
	String descricaoItem;
	String nomeIt;
	
	public PedidoItem(String pedido, String idPedido, String nomeit, String descricaoItem){
		this.idPedido = idPedido;
		this.pedido = pedido;
		this.nomeIt = nomeit;
		this.descricaoItem = descricaoItem;
	}
	
	public String getPedido(){
		return pedido;
	}
	
	public String getIdPedido(){
		return idPedido;
	}
	
}
