package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	private int N;
	private List<List<Integer>> soluzione;
	public List<List<Integer>> risolvi(int N){
		this.N = N;
		List<Integer> parziale = new ArrayList<Integer>();
		cerca(parziale, 0);
		
		this.soluzione= new ArrayList<>();
		return soluzione;
	}
	
	//cerca = true -> trovato!
	//cerca = false -> continua la ricerca!
	private void cerca(List<Integer>parziale, int livello) { //[0,6,4,7]
		if(livello==N) {
	//	System.out.println(parziale);
			this.soluzione.add( new ArrayList<>(parziale));
		// caso terminale
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la mossa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				if(posValida(parziale, colonna, livello)) {   //devo controllare se la posizione è valida
					parziale.add(colonna);			// se lo è, faccio la ricorsione
							 //[0,6,4,7, 1]
					cerca(parziale, livello+1);
					//l'aggiunta dell'1 nella soluzione parziale, è una prova! 
					//vedo cosa succede e poi lo tolgo
					
			//		boolean trovato = cerca(parziale, livello+1);
				
					parziale.remove((parziale.size()-1));
					
				}
			}
			
		}
	
	}
	
	private boolean posValida(List<Integer> parziale, int colonna, int livello) {
		//controlla se viene mangiata in verticale
		if(parziale.contains(colonna)) {
			return false;
		}
		//controlla le diagonali: confrontare posizione (livello, colonna) con (r,c) dekke regine esistenti
		//regina che stiamo posizionando ha  riga=livello
		for(int r = 0; r<livello; r++) { //livelloè anche = parziale.size()
			int c = parziale.get(r); //x questo su "risolvi" abbiamo messo arrayList e non LinkedList
			if(r+c == livello+colonna || r-c ==  livello-colonna) {
				return false;
			}
		}
		return true;
	}
	
}
