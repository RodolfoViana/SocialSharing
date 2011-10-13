package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AtividadesUsuario {
	
	List<String> listaAtividades;
	
	public AtividadesUsuario(){
		listaAtividades = new LinkedList<String>();
	}

	public void adicionarAtividades(String atividade){
		listaAtividades.add(atividade);
	}
	
	public String getAtividades(){
		return formataArray(listaAtividades).toString();
	}
	
	public boolean naoTemAtividades(){
		return listaAtividades.isEmpty();
	}
	
	public static void main(String[] args) {
	
		
		//String x = "[Steven Paul Jobs e Mark Zuckerberg são amigos agora][William Henry Gates III e Mark Zuckerberg são amigos agora][Mark Zuckerberg e William Henry Gates III são amigos agora, Mark Zuckerberg e Steven Paul Jobs são amigos agora]";
		
		String x = "Mark Zuckerberg e William Henry Gates III são amigos agora;Mark Zuckerberg e Steven Paul Jobs são amigos agora;";

		String z = "";
		int cont  = 0;
		for(String i : x.split(";")){
			if(cont!=x.split(";").length-1){
				z+=i+"; ";
			}
			else{
				z+=i;
			}
			cont++;
		
		}
		System.out.println(z);

	}
	
	
	private ArrayList<String> formataArray(List<String> lista){
		
		ArrayList<String> temp = new ArrayList<String>();
		
		for(int i = lista.size()-1; i>=0;i--){
			temp.add(lista.get(i));
		}
		return temp;
		
	}
	

}
