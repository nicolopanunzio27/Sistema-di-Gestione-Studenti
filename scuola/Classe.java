package scuola;

public class Classe {

    public static void main(String[] args) {
    
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String giudizioTemp = "", spazio = "-------------------------------------------------------------";
        final int maxStudenti = 100;
        boolean cont = true;
        int numeroStudenti = 0, giudizioNum = 0;
        Studente[] classe = new Studente[maxStudenti];

        while(cont){
            System.out.println("Inserisci il comando (1: Aggiungi studente, 2: Visualizza studenti, 3: Ricerca Studente, 4: Esci");
            int comando = scanner.nextInt();
            scanner.nextLine(); 
            switch(comando){
                case 1:
                    if(numeroStudenti < maxStudenti){
                        System.out.println("Inserisci il nome dello studente:");
                        String nome = scanner.nextLine();
                        System.out.println("Inserisci il cognome dello studente:");
                        String cognome = scanner.nextLine();
                        System.out.println("Inserisci il voto dello studente:");
                        double voto = scanner.nextDouble();
                        while(voto < 0 || voto > 30){
                            System.out.println("Voto non valido. Riprova.");
                            voto = scanner.nextDouble();
                        }
                        scanner.nextLine(); 
                        String esito = (voto >= 18) ? "Promosso" : "Bocciato";

                        if(voto < 18){giudizioNum = 0;} 
                        else if(voto < 21){giudizioNum = 1;} 
                        else if(voto < 27){giudizioNum = 2;} 
                        else {giudizioNum = 3;}
                        
                        switch(giudizioNum){
                            case 0: giudizioTemp = "Insufficiente";
                            case 1: giudizioTemp = "Sufficiente";
                            case 2: giudizioTemp = "Buono";
                            case 3: giudizioTemp = "Ottimo";
                        }

                        classe[numeroStudenti] = new Studente(nome, cognome, voto, giudizioTemp, esito);
                        numeroStudenti++;
                    } else {System.out.println("Numero massimo di studenti raggiunto.");}
                    break;
                case 2:
                    if(numeroStudenti == 0){
                        System.out.println("Nessuno studente presente.");
                        break;
                    }
                    for (int i = 0; i < numeroStudenti; i++){stampaStudente(classe[i], spazio);}
                    break;
                case 3:
                    if(numeroStudenti == 0){
                        System.out.println("Nessuno studente presente.");
                        break;
                    }
                    System.out.println("Inserisci il nome dello studente da cercare:");
                    String nomeCercato = scanner.nextLine();
                    System.out.println("Inserisci il cognome dello studente da cercare:");
                    String cognomeCercato = scanner.nextLine();
                    boolean trovato = false;
                    for (int i = 0; i < numeroStudenti; i++){
                        Studente s = classe[i];
                        if(s.nome.equalsIgnoreCase(nomeCercato) && s.cognome.equalsIgnoreCase(cognomeCercato)){
                            stampaStudente(s, spazio);
                            trovato = true;
                        }
                    }
                    if(!trovato){System.out.println("Studente non trovato.");}
                    break;
                case 4:
                    if(numeroStudenti == 0){
                        System.out.println("Nessuno studente presente.");
                        System.out.println("Uscita in corso...");
                        return;
                    }
                    if(numeroStudenti > 1){
                        System.out.println("Il voto più alto è: " + maxMin(classe, numeroStudenti, true));
                        System.out.println("Il voto più basso è: " + maxMin(classe, numeroStudenti, false));
                    }
                    double media = mediaVoti(classe, numeroStudenti);
                    System.out.println("La media dei voti è: " + media+"\nUscita in corso...");
                    return;
                default: System.out.println("Comando non valido. Riprova.");
            }
        }
        scanner.close();
    }
 
    static void stampaStudente(Studente s, String spazio)
    {System.out.println(spazio + "\nNome: " + s.nome + " " + s.cognome + " - " + s.voto + " - " + s.esito + "  (" + s.guidizio+")" + "\n"+ spazio);}
    
    static double mediaVoti(Studente[] studenti, int numeroStudenti){
        double somma = 0;
        for (int i = 0; i < numeroStudenti; i++){somma += studenti[i].voto;}
        return somma / numeroStudenti;
    }

    static double maxMin(Studente[] studenti, int numeroStudenti, boolean massimo){
        double valore = studenti[0].voto;
        for (int i = 1; i < numeroStudenti; i++){
            Studente s = studenti[i];
            if(massimo){
                if(s.voto > valore){valore = s.voto;}}
            else if(s.voto < valore){valore = s.voto;}
        }
        return valore;
    }
}