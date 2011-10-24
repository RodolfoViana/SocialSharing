package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Classe criada para guardar as atividades dos usuarios
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR CRUZ
 *
 */
public class AtividadesUsuario {

	List<Atividade> listaAtividades;
	List<Long> dataCriacaoAtividades;

	/**
	 * Construtor da classe
	 */
	public AtividadesUsuario() {
		listaAtividades = new ArrayList<Atividade>();
		dataCriacaoAtividades = new ArrayList<Long>();
	}

	/**
	 * Adiciona uma atividade
	 * @param atividade Atividade que vai ser adicionada
	 */
	public void adicionarAtividades(Atividade atividade) {
		if (!atividadeJaExiste(atividade)){
			listaAtividades.add(atividade);
			Collections.sort(listaAtividades);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Recupera a criacao da atividade
	 * @return Criacao da atividade
	 */
	public String getCriacaoAtividades() {
		return formataArray2(dataCriacaoAtividades).toString();
	}

	/**
	 * Recupera a lista de atividades
	 * @return Lista de atividades
	 */
	public List<Atividade> getAtividades() {
		return (listaAtividades);
	}

	private ArrayList<Long> formataArray2(List<Long> lista) {
		ArrayList<Long> temp = new ArrayList<Long>();

		for (int i = lista.size() - 1; i >= 0; i--) {
			temp.add(lista.get(i));
		}
		return temp;
	}

	private boolean atividadeJaExiste(Atividade atv) {
		Iterator<Atividade> it = listaAtividades.iterator();

		while (it.hasNext()) {
			Atividade atvs = it.next();
			if (atvs.getAtividade().trim().equals(atv.getAtividade().trim())) {
				return true;
			}

		}
		
		return false;
	}

	/**
	 * Adiciona uma atividade
	 * @param atividade1 Atividade 
	 * @param criacao Criacao da atividade
	 */
	public void adicionarAtividades(String atividade1, long criacao) {
		Atividade atividade = new Atividade(atividade1, criacao);
		
		if (!atividadeJaExiste(atividade)){
			listaAtividades.add(atividade);
			Collections.sort(listaAtividades);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
