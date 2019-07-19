package com.smartcity.smartcity.algorithme;

import java.util.Arrays;

import com.smartcity.smartcity.algorithme.FileEnChaine;
import com.smartcity.smartcity.model.Plot;


public class CityCase {

	private ValCase[][] grille;

	private ValCase tetecherche;

	public CityCase() {

		super();
	}

	public CityCase(ValCase[][] grille, ValCase tetecherche) {

		super();

		this.grille = grille;
		this.tetecherche = tetecherche;
	}

	public boolean gauche() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cxg = cx - 1;

		if (cxg < 0) {

			return false;
		} else {
			if (grille[cxg][cy].isValider() == false) {

				return false;
			} else if (grille[cxg][cy].getVal() >= 0) {

				return false;
			} else {
				return true;
			}
		}
	}

	public ValCase aGauche() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cxg = cx - 1;

		return this.grille[cxg][cy];

	}

	public boolean droite() {

		int longueur = this.grille.length;
		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cxd = cx + 1;

		if (cxd >= longueur) {

			return false;
		} else {
			if (grille[cxd][cy].isValider() == false) {

				return false;
			} else if (grille[cxd][cy].getVal() >= 0) {

				return false;
			} else {
				return true;
			}
		}
	}

	public ValCase aDroite() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cxd = cx + 1;

		return this.grille[cxd][cy];

	}

	public boolean haut() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cyh = cy - 1;

		if (cyh < 0) {

			return false;
		} else {
			if (grille[cx][cyh].isValider() == false) {

				return false;
			} else if (grille[cx][cyh].getVal() >= 0) {

				return false;
			} else {
				return true;
			}
		}
	}

	public ValCase enHaut() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cyh = cy - 1;

		return this.grille[cx][cyh];

	}

	public boolean bas() {

		int hauteur = this.grille[0].length;
		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cyb = cy + 1;

		if (cyb >= hauteur) {

			return false;
		} else {
			if (grille[cx][cyb].isValider() == false) {

				return false;
			} else if (grille[cx][cyb].getVal() >= 0) {

				return false;
			} else {
				return true;
			}
		}
	}

	public ValCase enBas() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cyb = cy + 1;

		return this.grille[cx][cyb];

	}

	public void longueurParCasesDepuisRTB() {

		FileEnChaine<ValCase> file = new FileEnChaine<ValCase>();

		boolean testfin = false;
		int dmax = this.tetecherche.getDmax();
		int maxCase = this.tetecherche.getMaxCase();
		int debutX = this.tetecherche.getVx();
		int debutY = this.tetecherche.getVy();

		int max;
		if (dmax <= maxCase) {

			max = dmax;
		} else {

			max = maxCase;
		}

		file.inserer(this.tetecherche);

		while (testfin == false) {

			if (this.gauche() == true) {

				if ((this.tetecherche.getVal() + 1) > max) {

					this.aGauche().invValider();
				} else {

					this.aGauche().setVal(this.tetecherche.getVal() + 1);
					file.inserer(this.aGauche());
				}
			}

			if (this.droite() == true) {

				if ((this.tetecherche.getVal() + 1) > max) {

					this.aDroite().invValider();
				} else {

					this.aDroite().setVal(this.tetecherche.getVal() + 1);
					file.inserer(this.aDroite());
				}
			}

			if (this.haut() == true) {

				if ((this.tetecherche.getVal() + 1) > max) {

					this.enHaut().invValider();
				} else {

					this.enHaut().setVal(this.tetecherche.getVal() + 1);
					file.inserer(this.enHaut());
				}
			}

			if (this.bas() == true) {

				if ((this.tetecherche.getVal() + 1) > max) {

					this.enBas().invValider();
				} else {

					this.enBas().setVal(this.tetecherche.getVal() + 1);
					file.inserer(this.enBas());
				}
			}

			file.supprimer();

			if (file.getLongueurFile() > 0) {

				this.tetecherche = file.getTete().getValeur();
			}

			if (file.estVide() == true) {

				testfin = true;
			}
		}

		this.tetecherche = this.grille[debutX][debutY];

	}

	public int dParCasesMinDepuisRTB(Plot pt) {

		int maxCase = this.tetecherche.getMaxCase();
		int dmax = this.tetecherche.getDmax();
		int vx = this.tetecherche.getVx();
		int vy = this.tetecherche.getVy();
		int max;

		if (dmax <= maxCase) {

			max = dmax;
		} else {

			max = maxCase;
		}

		String abrev = pt.getPlotType().getPlotTypeAbrev();
		int px = pt.getPositionX();
		int py = pt.getPositionY();
		int d = max;

		this.tetecherche = this.grille[px][py];

		if (!(abrev.contentEquals("RTE") || abrev.contentEquals("RTB"))) {

			if (this.regardeG() == true) {

				if (this.aGauche().getVal() < d) {

					d = this.aGauche().getVal();
				}
			}
			if (this.regardeD() == true) {

				if (this.aDroite().getVal() < d) {

					d = this.aDroite().getVal();
				}
			}
			if (this.regardeH() == true) {

				if (this.enHaut().getVal() < d) {

					d = this.enHaut().getVal();
				}
			}
			if (this.regardeB() == true) {

				if (this.enBas().getVal() < d) {

					d = this.enBas().getVal();
				}
			}
		}

		this.tetecherche = this.grille[vx][vy];

		return d;
	}

	public boolean regardeG() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cxg = cx - 1;

		if (cxg < 0) {

			return false;
		} else {
			if (grille[cxg][cy].isValider() == false) {

				return false;
			} else if (grille[cxg][cy].getVal() >= 0) {

				return true;
			} else {
				return false;
			}
		}
	}

	public boolean regardeD() {

		int longueur = this.grille.length;
		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cxd = cx + 1;

		if (cxd >= longueur) {

			return false;
		} else {
			if (grille[cxd][cy].isValider() == false) {

				return false;
			} else if (grille[cxd][cy].getVal() >= 0) {

				return true;
			} else {
				return false;
			}
		}
	}

	public boolean regardeH() {

		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cyh = cy - 1;

		if (cyh < 0) {

			return false;
		} else {
			if (grille[cx][cyh].isValider() == false) {

				return false;
			} else if (grille[cx][cyh].getVal() >= 0) {

				return true;
			} else {
				return false;
			}
		}
	}

	public boolean regardeB() {

		int hauteur = this.grille[0].length;
		int cx = this.tetecherche.getVx();
		int cy = this.tetecherche.getVy();

		int cyb = cy + 1;

		if (cyb >= hauteur) {

			return false;
		} else {
			if (grille[cx][cyb].isValider() == false) {

				return false;
			} else if (grille[cx][cyb].getVal() >= 0) {

				return true;
			} else {
				return false;
			}
		}
	}

	public void AfficherD(Plot[][] tp) {

		int longueur = this.grille.length;
		int hauteur = this.grille[0].length;

		System.out.println();

		for (int y = 0; y < hauteur; y++) {

			for (int x = 0; x < longueur; x++) {

				String abrev = tp[x][y].getPlotType().getPlotTypeAbrev();

				if (!(abrev.contentEquals("RTE") || abrev.contentEquals("RTB"))) {

					System.out.print(" " + this.dParCasesMinDepuisRTB(tp[x][y]) + " ");
				} else {

					System.out.print(" . ");
				}

			}

			System.out.println();
		}
		System.out.println();
	}

	public void AfficherModeleVic() {

		System.out.println();
		int longueur = this.grille.length;
		int hauteur = this.grille[0].length;

		for (int y = 0; y < hauteur; y++) {

			for (int x = 0; x < longueur; x++) {

				if (this.grille[x][y].isValider() == true) {

					if (this.grille[x][y].getVal() < 0) {

						System.out.print(this.grille[x][y].getVal() + " ");
					} else {

						if (grille[x][y].getVal() < 10) {

							System.out.print(" " + grille[x][y].getVal() + " ");
						} else if (grille[x][y].getVal() < 100) {

							System.out.print(" " + grille[x][y].getVal());
						}
					}
				} else {

					System.out.print(" . ");
				}

			}
			System.out.println();
		}
		System.out.println();
	}

	public void setValxy(ValCase vc) {
		this.grille[vc.getVx()][vc.getVy()] = vc;
	}

	public ValCase[][] getGrille() {
		return grille;
	}

	public void setGrille(ValCase[][] grille) {
		this.grille = grille;
	}

	public ValCase getTetecherche() {
		return tetecherche;
	}

	public void setTetecherche(ValCase tetecherche) {
		this.tetecherche = tetecherche;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(grille);
		result = prime * result + ((tetecherche == null) ? 0 : tetecherche.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityCase other = (CityCase) obj;
		if (!Arrays.deepEquals(grille, other.grille))
			return false;
		if (tetecherche == null) {
			if (other.tetecherche != null)
				return false;
		} else if (!tetecherche.equals(other.tetecherche))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VilleCaseRepresentation [grille=" + Arrays.toString(grille) + ", tetecherche=" + tetecherche + "]";
	}
}
