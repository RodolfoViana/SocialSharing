package Classes;

/**
 * Classe que representa uma Atividade realizada por um Usuario
 * @author ARTHUR SENA, RODOLFO DE LIMA, RENAN PINTO, IGOR CRUZ
 *
 */
public class Atividade implements Comparable<Atividade>{
	
	private String atividade;
	private long criacaoAtividade;
	
	/**
	 * Construtor da Classe
	 * @param atividade
	 *         A string que representa a Atividade realizada
	 * @param criacaoAtividade
	 *        O momento em que aconteceu a Atividade
	 */
	
	public Atividade(String atividade, long criacaoAtividade){
		this.atividade = atividade;
		this.criacaoAtividade = criacaoAtividade;
	}
	
	/**
	 * Recupera a Atividade
	 * @return
	 *       Atividade
	 */
	public String getAtividade(){
		return atividade;
	}
	
	/**
	 * Recupera o momenteo em que aconteceu a Criacao da Atividade
	 * @return
	 *        Momento da criacao da Atividade
	 */
	
	public long getCriacaoAtividade(){
		return criacaoAtividade;
	}

	/**
	 * Compara duas Atividades
	 */
	@Override
	public int compareTo(Atividade atv) {
		if(atv.getCriacaoAtividade()>this.getCriacaoAtividade()){
			return 1;
		}
		else if(atv.getCriacaoAtividade()<this.getCriacaoAtividade()){
			return -1;
		}
		return 0;
	}
	
	/**
	 * Diz se uma atividade eh igual a outra
	 * @param atv
	 *      Atividade a ser comparada
	 * @return
	 *       True, caso sejam iguais
	 *       False, caso contrario
	 */
	public boolean equals(Atividade atv){
		try{
			String nome1, nome2;
			
			if(!atv.getAtividade().contains("amigos")){
				return false;
			}
			if(atv.getAtividade().equals(this.atividade)){
				return true;
			}
			
			nome1 = atv.getAtividade().split(" e ")[0];
			nome2 = atv.getAtividade().split(" e ")[1].split(" são ")[0].trim();
			
			if(atividade.contains(nome2) && atividade.contains(nome1)){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

}
