package sessió1;

import Keyboard.*;
import java.util.Random;

public class sessio1 {

	private static int llegirValor(int minim, int maxim, String text) {
		int num;
		System.out.print(text + "entre [" + minim + "," + maxim + "]:");
		num = Keyboard.readInt();
		while (num < minim || num > maxim) {
			System.out.println("Torna a escriure un valore entre [" + minim + "," + maxim + "].");
			num = Keyboard.readInt();
		}
		return num;
	}

	private static void visualitzar(int[][] taulell, int numFiles) {
		for (int i = 0; i < numFiles; i++) {
			for (int j = 0; j < taulell[i].length; j++) {
				if (taulell[i][j] == 0) {
					System.out.print("X ");
				} else {
					System.out.print(taulell[i][j] + " ");
				}
			}
			System.out.println("");
		}
	}
	
	private static int[][] crearTaulell() {

		int[][] taulell = new int[12][9];
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < taulell[i].length; j++) {
				if(i<4){
				//per fer proves
				taulell[i][j] = 2;
				//taulell[i][j]= rand.nextInt(9)+1;
				}
				else {
					taulell[i][j]= 0;
				}
			}
		}
		return taulell;
	}

	private static void buidaComponent(int[][] taulell, int fila, int col) {
		taulell[fila][col] = 0;

	}

	private static boolean parellaHoritzontal(int[][] taulell, int fila1, int columna1, int fila2, int columna2) {
	    if (taulell[fila1][columna1] != taulell[fila2][columna2] && taulell[fila1][columna1] + taulell[fila2][columna2] != 10) {
	        return false;
	    }

	    if (fila1 == fila2) {
	        for (int j = columna1 + 1; j < columna2; j++) {
	            if (taulell[fila1][j] != 0) {
	                return false;
	            }
	        }
	    } else if (fila1 + 1 == fila2) {
	        int ultimNoEmparellat1 = quinUltimNoEmparellat(taulell, fila1);
	        int primerNoEmparellat2 =quinPrimerNoEmparellat(taulell, fila2);
	        if (ultimNoEmparellat1 != columna1 || primerNoEmparellat2 != columna2) {
	            return false;
	        }
	    } else {
	        return false;
	    }

	    return true;
	}
	
	//funció per trobar el ultim nombre no emparellat d'una fila, és a dir, seria 4 --> 7594XXXX
	private static int quinUltimNoEmparellat(int[][] taulell, int fila) {
	    int ultimNoEmparellat = taulell[fila].length - 1;
	    while (ultimNoEmparellat >= 0 && taulell[fila][ultimNoEmparellat] == 0) {
	        ultimNoEmparellat--;
	    }
	    return ultimNoEmparellat;
	}
	//funció per trobar el primer nombre no emparellat d'una fila, és a dir, seria 4 --> XXXXX4566
	private static int quinPrimerNoEmparellat(int[][] taulell, int fila) {
	    int primerNoEmparellat = 0;
	    while (primerNoEmparellat < taulell[fila].length && taulell[fila][primerNoEmparellat] == 0) {
	        primerNoEmparellat++;
	    }
	    return primerNoEmparellat;
	}



	private static boolean parellaVertical(int[][] taulell, int fil1, int col1, int fil2, int col2) {
	    if (taulell[fil1][col1] != taulell[fil2][col2] && taulell[fil1][col1] + taulell[fil2][col2] != 10) {
	        return false;
	    }

	    if (col1 == col2) {
	        for (int i = fil1 + 1; i < fil2; i++) {
	            if (taulell[i][col1] != 0) {
	                return false;
	            }
	        }
	    } else {
	        return false;
	    }

	    return true;
	}


	private static boolean parellaDiagonal(int[][] taulell, int fil1, int col1, int fil2, int col2) {
	    if (taulell[fil1][col1] != taulell[fil2][col2] && taulell[fil1][col1] + taulell[fil2][col2] != 10) {
	        return false;
	    }
	    //valor absolut important per el funcionament en els dos sentits
	    if (Math.abs(fil1 - fil2) != Math.abs(col1 - col2)) {
	        return false;
	    }

	    int filIncrement, colIncrement;
	    if (fil2 > fil1) {
	        filIncrement = 1;
	    } else {
	        filIncrement = -1;
	    }
	    if (col2 > col1) {
	        colIncrement = 1;
	    } else {
	        colIncrement = -1;
	    }

	    int i = fil1 + filIncrement;
	    int j = col1 + colIncrement;

	    while (i != fil2 || j != col2) {
	        if (taulell[i][j] != 0) {
	            return false;
	        }
	        i += filIncrement;
	        j += colIncrement;
	    }

	    return true;
	}





	private static void afegir(int[][] taulell, int numFiles) {
	    int numCopiar = 0, files = numFiles;
	    int[] numGuardat = new int[36];

	    // Copiar los números que no son 0 en el vector, añadirlos después en el
	    // comando afegir.
	    for (int i = 0; i < numFiles && numCopiar < 36; i++) {
	        for (int j = 0; j < 9 && numCopiar < 36; j++) {
	            if (taulell[i][j] != 0) {
	                numGuardat[numCopiar] = taulell[i][j];
	                numCopiar++;
	            }
	        }
	    }

	    // Añadir los números al final de la matriz, creando una matriz de 8x9.
	    int index = 0;
	    for (int i = numFiles; i < numFiles + 4; i++) {
	        for (int j = 0; j < 9; j++) {
	            taulell[i][j] = numGuardat[index];
	            index = (index + 1) % numCopiar;
	        }
	    }


	}
			
		

	

	private static boolean isBuida(int[][] taulell, int fila) {
	    for (int j = 0; j < 9; j++) {
	        if (taulell[fila][j] != 0) {
	            return false;
	        }
	    }
	    return true;
	}


	private static void eliminarFila(int[][] taulell, int fila) {
	    // Mou totes les files a dalt
		
	    for (int i = fila; i < taulell.length - 1; i++) {
	        taulell[i] = taulell[i + 1];
	    }
	    // Omple la última fila amb 0s
	    for (int j = 0; j < taulell[0].length; j++) {
	        taulell[taulell.length - 1][j] = 0;
	    }
	}



	private static boolean isBuit(int[][] taulell, int numFiles) {
		int i = 0;
		while (i < numFiles) {
			int j = 0;
			while (j < taulell[i].length) {
				if (taulell[i][j] != 0) {
					return false;
				}
				j++;
			}
			i++;
		}
		return true;
	}
	
	public static void copy(int[][] t, int[][] tcop) {
	    
	    for(int i = 0; i < tcop.length; i++) {
	        for(int j = 0; j < tcop[i].length; j++) {
	            tcop[i][j] = 0;
	        }
	    }

	    for(int i = 0; i < 4; i++) {
	        for(int j = 0; j < t[i].length; j++) {
	            tcop[i][j] = t[i][j];
	        }
	    }
	}



	
	
	
	public static void main(String[] args) {
	    System.out.println("Anem a jugar a NumberMatch.");
	    System.out.println("Les regles son sencilles. Has de dir en quina [Fila] i [Columna] estan els numeros que vols lligar.");
	    System.out.println("Taulell creat.");
	    System.out.println("================");
	    int files = 4;
	    int[][] taulell = crearTaulell();
	    int[][] tcop = new int[12][9];
	    copy(taulell,tcop);
	    visualitzar(taulell, files);
	    boolean acabar = true;
	    boolean guanyat = false; // Añade esta línea
	    int cops = 2;
	    char sn;
	    while (acabar == true && isBuit(taulell, files) == false) {
	        int sumaCops = 4;

	        System.out.println("Que vols fer");
	        System.out.println("************");
	        System.out.println("1.- Aparellar");
	        System.out.println("2.- Posar més números. Màxim 2 cops. Et queden " + cops + " cops");
	        System.out.println("3.- Acabar");

	        int valor = llegirValor(1, 3, "Escull una acció ");
	        while (valor < 1 || valor > 3) {
	            System.out.println("Torna a escriure un valore entre [1,3].");
	            valor = Keyboard.readInt();
	        }

	        if (valor == 1) {
	            int fil1, col1, fil2, col2;
	            int fil1aux, col1aux, fil2aux, col2aux;
	            do {
	                System.out.println("En quina fila està el primer valor?");
	                fil1aux = Keyboard.readInt();
	                while (fil1aux < 0 || fil1aux > files) {
	                    System.out.println("Valor incorrecte, escriu un valor dins de la matriu.");
	                    fil1aux = Keyboard.readInt();
	                }
	                System.out.println("I en quina columna?");
	                col1aux = Keyboard.readInt();
	                while (col1aux < 0 || col1aux > 8) {
	                    System.out.println("Valor incorrecte, escriu un valor dins de la matriu.");
	                    col1aux = Keyboard.readInt();
	                }
	                System.out.print("En quina fila està el segon valor?");
	                fil2aux = Keyboard.readInt();
	                while (fil2aux < 0 || fil2aux > files) {
	                    System.out.println("Valor incorrecte, escriu un valor dins de la matriu.");
	                    fil2aux = Keyboard.readInt();
	                }
	                System.out.println("I en quina columna?");
	                col2aux = Keyboard.readInt();
	                while (col2aux < 0 || col2aux > 8) {
	                    System.out.println("Valor incorrecte, escriu un valor dins de la matriu.");
	                    col2aux = Keyboard.readInt();
	                }
	                if(fil1aux == fil2aux && col1aux == col2aux) {
	                    System.out.println("No pots emparellar números en la mateixa posició!");
	                }
	                if(taulell[fil1aux][col1aux] == 0 || taulell[fil2aux][col2aux] == 0) {
	                    System.out.println("Els números escollits no poden ser valors=0!");
	                }
	                if(fil1aux<fil2aux||fil1aux==fil2aux&&col1aux<col2aux) {
	                	fil1=fil1aux;
	                	fil2=fil2aux;
	                	col1=col1aux;
	                	col2=col2aux;
	                }else {
	                	fil1=fil2aux;
	                	fil2=fil1aux;
	                	col1=col2aux;
	                	col2=col1aux;
	                }
	                		
	                		
	                		
	                		
	                		
	                		
	            } while (taulell[fil1][col1] == 0 || taulell[fil2][col2] == 0 || (fil1 == fil2 && col1 == col2));

	            if (taulell[fil1][col1] + taulell[fil2][col2] == 10 || taulell[fil1][col1] == taulell[fil2][col2]) {
	                if (parellaHoritzontal(taulell, fil1, col1, fil2, col2) == true
	                        || parellaVertical(taulell, fil1, col1, fil2, col2) == true
	                        || parellaDiagonal(taulell, fil1, col1, fil2, col2) == true) {
	                    buidaComponent(taulell, fil1, col1);
	                    buidaComponent(taulell, fil2, col2);
	                    visualitzar(taulell, files);
	                } else {
	                    System.out.println("No fan parella, escull uns altres valors.");
	                    visualitzar(taulell, files);
	                }
	            } else {
	                System.out.println("No fan parella, escull uns altres valors.");
	                visualitzar(taulell, files);
	            }
	            if (isBuida(taulell, fil1) == true) {
	                eliminarFila(taulell, fil1);
	                files--;  // disminueix el nombre de files
	                System.out.println("La fila " + fil1 + " s'ha eliminat.");
	                visualitzar(taulell, files);
	            } else if (isBuida(taulell, fil2) == true) {
	                eliminarFila(taulell, fil2);
	                files--;  // disminueix el nombre de files
	                System.out.println("La fila " + fil2 + " s'ha eliminat.");
	                visualitzar(taulell, files);
	            }


	            if (isBuit(taulell, files) == true) {
	                acabar = false;
	                guanyat = true;
	            }
	        } else if (valor == 2) {
	            if (cops > 0) {
	                cops--;
	                afegir(taulell,files);
	                files += sumaCops;
	                visualitzar(taulell, files);
	            } else if (cops <= 0) {
	                System.out.println("Ja has utilitzar el màxim de vegades que pots omplir els espais vuits");
	            }
	        } else if (valor == 3) {
	            acabar = false;
	            System.out.println("Quina Pena! Molta sort el pròxim cop!");
	            System.out.println("Vols fer una nova partida amb el mateix taulell? (s/n)");
	            sn = Keyboard.readChar();
	            if(sn=='s') {
	            	acabar=true;
	            	copy(tcop,taulell);
	            	files=4;
	            	visualitzar(taulell,files);
	            }
	            else {
	            	System.out.println("ADEU!");
	            }
	        }
	        
	        if(isBuit(taulell, files)==true) {
	        	if (guanyat) {
		            System.out.println("MOLT BÉ! Has finalitar el joc correctament!");
		            System.out.println("Vols fer una nova partida? (s/n)");
		            sn = Keyboard.readChar();
		            if(sn=='s') {
		            	acabar=true;
		            	tcop = crearTaulell();
		            	copy(tcop,taulell);
		            	files=4;
		            	visualitzar(taulell,files);
		            }
		            else {
		            	System.out.println("ADEU!");
		            }
	        }
	    }
	    
	        
	    }
	}
	




}

