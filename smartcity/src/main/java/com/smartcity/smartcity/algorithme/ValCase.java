package com.smartcity.smartcity.algorithme;


public class ValCase {
	
	private boolean valider;

	private int val;
	
	private int vx;
	
	private int vy;
	
	private int dmax;
	
	private int maxCase;
	
	
	
	public ValCase() {
		
		super();
		
	}
	
	
	public ValCase (int dmax,int maxCase) {
		
		super();
		
		if (this.dmax <= this.maxCase) {
			
			this.maxCase= maxCase;
			this.dmax= dmax;
		}
		else {
			
			this.maxCase= maxCase;
			this.dmax= maxCase;
		}
	}


	/*
	public ValCase(int val) {
		
		super();
		
		this.valider = true;
		this.val = val;
	}*/

	
	public ValCase(int val, int dmax, int maxCase) {
		
		this(dmax, maxCase);
		
		this.val = val;
		
		if (this.val > this.dmax) {
			
			this.valider= false;
		}
		else {
		
			this.valider= true;
		}
	}
	
	
	
	public boolean invValider() {

		if (this.valider = false) {

			this.valider = true;
		} else {

			this.valider = false;
		}

		return this.valider;
	}

	/*
	public void changerValCase(int val) {

		this.valider = true;
		this.val = val;
	}*/
	
	

	

	public static void AfficherModele (ValCase[][] grille) {
		
		System.out.println();
		int longueur= grille.length;
		int hauteur= grille[0].length;
		
		
		for (int y= 0; y< hauteur; y++) {
			
			for (int x= 0; x< longueur; x++) {
				
				if (grille[x][y].isValider() == true) {
					
					if (grille[x][y].getVal()< 0) {
						
						System.out.print(grille[x][y].getVal()+" ");
					}
					else {
						
						if (grille[x][y].getVal() < 10) {
							
							System.out.print(" "+grille[x][y].getVal()+" ");
						}
						else if (grille[x][y].getVal() < 100) {
							
							System.out.print(" "+grille[x][y].getVal());
						}
					}
				}
				else {
					
					System.out.print("   ");
				}
				
			}
			System.out.println();
		}
		System.out.println();
	}
	
	

	public boolean isValider() {
		return valider;
	}

	public void setValider(boolean valider) {
		this.valider = valider;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public int getMaxCase() {
		return maxCase;
	}

	public void setMaxCase(int maxCase) {
		this.maxCase = maxCase;
	}

	public int getDmax() {
		return dmax;
	}

	public void setDmax(int dmax) {
		this.dmax = dmax;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dmax;
		result = prime * result + maxCase;
		result = prime * result + val;
		result = prime * result + (valider ? 1231 : 1237);
		result = prime * result + vx;
		result = prime * result + vy;
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
		ValCase other = (ValCase) obj;
		if (dmax != other.dmax)
			return false;
		if (maxCase != other.maxCase)
			return false;
		if (val != other.val)
			return false;
		if (valider != other.valider)
			return false;
		if (vx != other.vx)
			return false;
		if (vy != other.vy)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "ValCase [valider=" + valider + ", val=" + val + ", vx=" + vx + ", vy=" + vy + ", maxCase=" + maxCase
				+ ", dmax=" + dmax + "]";
	}
	
	
}
