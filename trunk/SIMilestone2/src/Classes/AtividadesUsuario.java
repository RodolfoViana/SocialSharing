package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AtividadesUsuario {

	List<Atividade> listaAtividades;
	List<Long> dataCriacaoAtividades;

	public AtividadesUsuario() {
		listaAtividades = new ArrayList<Atividade>();
		dataCriacaoAtividades = new ArrayList<Long>();
	}

	public void adicionarAtividades(Atividade atividade) {
		if (!atividadeJaExiste(atividade)){
			System.out.println((atividade.getAtividade()));
			listaAtividades.add(atividade);
			Collections.sort(listaAtividades);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
//		if (!listaAtividades.contains(atividade) && !atividadeJaExiste(atividade)) {
//			System.out.println((atividade.getAtividade()));
//			listaAtividades.add(atividade);
//			Collections.sort(listaAtividades);
//			try {
//				Thread.sleep(10);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

	public String getCriacaoAtividades() {
		return formataArray2(dataCriacaoAtividades).toString();
	}

	public List<Atividade> getAtividades() {
		return (listaAtividades);
	}

	public boolean naoTemAtividades() {
		return listaAtividades.isEmpty();
	}

	private ArrayList<Long> formataArray2(List<Long> lista) {
		ArrayList<Long> temp = new ArrayList<Long>();

		for (int i = lista.size() - 1; i >= 0; i--) {
			temp.add(lista.get(i));
		}
		return temp;
	}

	private boolean atividadeJaExiste(Atividade atv) {
		System.out.println(atv.getAtividade());

		Iterator<Atividade> it = listaAtividades.iterator();

		while (it.hasNext()) {
			Atividade atvs = it.next();
			String atv1 = atv.getAtividade().trim();
			String atvs2 = atvs.getAtividade().trim();
			
			
			if (atvs.getAtividade().trim().equals(atv.getAtividade().trim())) {
				return true;
			}

		}
		
		return false;
	}

}
