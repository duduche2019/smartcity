package com.smartcity.smartcity.algorithme;

public class Cellule<T> {

	// Pour que cela fonctionne quelque soit le type de cellule

	// une cellule a une valeur et pointe vers cellule suivante
	private T valeur;
	private Cellule<T> suivant;

	public Cellule(T val) {

		this.valeur = val;
		this.suivant = null;
	}

	public Cellule(T val, Cellule<T> cel) {

		this.valeur = val;
		this.suivant = cel;
	}

	// Pour acceder a la valeur d'une cellule
	public T getValeur() {

		return this.valeur;
	}

	// Pour acceder pointeur vers la cellule suivante
	public Cellule<T> getSuivant() {

		return this.suivant;
	}

	// Pour modifier la valeur d'une cellule
	public void setValeur(T val) {

		this.valeur = val;
	}

	// pour modifier le pointage vers la cellule suivante
	public void setSuivant (Cellule<T> cel) {

		this.suivant= cel;
	}
}
