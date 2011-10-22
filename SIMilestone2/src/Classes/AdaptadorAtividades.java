package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class AdaptadorAtividades {
	
	public static void main(String[] args) {
		List<Atividade> x = new ArrayList<Atividade>();
		
		x.iterator();
	}
	
	private void imprimir(List<Atividade> atividades){
		for(Atividade i : atividades){
			System.out.println(i.getAtividade());
		}
		System.out.println("\n");
	}
	
	public String adaptar(List<Atividade> atividades){
		if(atividades.isEmpty()){
			return "Não há atividades";
		}
		Collections.sort(atividades);

		String resposta = "";
		
		int cont = 0;
		for(Atividade atv : atividades){
			if(cont==atividades.size()-1){
				resposta += atividades.get(cont).getAtividade();
			}
			else{
				resposta += atividades.get(cont).getAtividade()+"; ";
			}
			cont++;
		}
		return resposta;
		
	}

}
