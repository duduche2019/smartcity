package com.smartcity.smartcity.algorithme;

public class FileEnChaine<T> {

	// Notre File pourra contenir n'importe quel type

	// une file comporte toujours une tete a droite et
	// sa queue en fin de file a gauche
	Cellule<T> tete;
	Cellule<T> queue;

	public FileEnChaine() {

		this.tete = null;
		this.queue = null;
	}

	public Cellule<T> getTete() {

		return this.tete;
	}

	public boolean estVide() {

		boolean rep = false;

		if (this.tete == null && this.queue == null) {

			rep = true;
		}

		return rep;
	}

	public Cellule<T> getQueue() {

		return this.queue;
	}

	public int getLongueurFile() {

		int rep = 0;

		if (this.estVide() == true) {

			return rep;
		} else {

			Cellule<T> ref = this.tete;
			boolean testfin1 = false;
			rep++;

			while (testfin1 == false) {

				if (ref.getSuivant() != null) {

					ref = ref.getSuivant();
					rep++;
				} else {

					testfin1 = true;
				}
			}

			return rep;
		}
	}

// Attention la tete est cette fois-ci a gauche et la queue en fin de liste
	public void afficherFile() {

		System.out.println();
		System.out.println("Voici la File (tete a gauche, queue a droite): ");
		System.out.println("----------------------------------------------");

		if (this.estVide() == true) {

			System.out.println();
		} else {

			Cellule<T> ref = this.tete;

			for (int i = 1; i <= this.getLongueurFile(); i++) {

				System.out.print(ref.getValeur() + " ");

				if (ref.getSuivant() != null) {

					ref = ref.getSuivant();
				}
			}
		}
		System.out.println();
		System.out.println();
	}

	// On insere toujours par la queue
	public void inserer(T val) {

		if (this.estVide() == true) {

			Cellule<T> cel = new Cellule<T>(val);
			this.tete = cel;
			this.queue = cel;
		}

		else {

			// attention on insere a gauche

			Cellule<T> anciennequeue = this.queue;
			Cellule<T> nouvellequeue = new Cellule<T>(val);
			anciennequeue.setSuivant(nouvellequeue);
			this.queue = nouvellequeue;
		}
	}

	public T supprimer() {

		T rep = null;

		if (this.estVide() == true) {

			return rep;
		}

		// seulement si tete et queue se confondent
		else if (this.getLongueurFile() == 1) {

			rep = this.tete.getValeur();
			this.queue = null;
			this.tete = null;

			return rep;
		}

		else {

			// si tete et queue sont different
			// on pointe vers l'element a gauche de la tete
			rep = this.tete.getValeur();
			Cellule<T> nouvelletete = this.tete.getSuivant();
			this.tete.setSuivant(null);
			this.tete = nouvelletete;

			return rep;
		}
	}
}
