package vfinf.zerok.zerok.classes.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class TaskHandler {
    private ArrayList tasks;

    private static int currentTask=0;
    BufferedReader reader;

    public TaskHandler() throws FileNotFoundException {
        tasks= new ArrayList();
        readFile();
    }

    private void readFile() throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader("Task.txt"));
        String line = null;

        do {
            try {
                String[] dati = line.split(";");
                line = this.reader.readLine();
                if (line != null && dati.length == 2) {
                    Task task = new Task(dati[0], Double.parseDouble(dati[1]));
                    tasks.add(task);
                }
            } catch (Exception var3) {
                System.out.println("Errore in lettura");
            }
        } while(line != null);
    }

    public String getCurrentTask() {
        return (String) tasks.get(currentTask);
    }

    private void nextTask(){
        currentTask++;
    }

    public String wasSucesfull(Boolean success){
        switch (currentTask){
            case 0:
                if(success) {
                    nextTask();
                    //qua potremmo anche ritornare i soldi guadagnati oppure si implementa un metodo per l'aumento dei soldi all'utente
                    return "Sei riuscito a mandare in orbita il tuo primo satellite!";
                }else
                    return "Il tuo satellite non è sopravvissuto all'impatto con l'atmosfera, migliora il tuo satelli e riprova";
            case 1:
                if(success) {
                    nextTask();
                    return "Sei riuscito a inviare il tuo primo essere vivente nello spazio!";
                } else
                    return "Mi spiace ma l'animale che hai inviato nello spazio non è riuscito a sopravvivere il viaggio.";
            case 2:
                if(success){
                    nextTask();
                    return "Il tuo uomo è riuscito nell'impresa di andare nello spazio!";
                }else
                    return "Durante il viaggio l'uomo che hai inviato non è riuscito a sopravvivere";
            case 3:
                if(success) {
                    nextTask();
                    return "Il tuo primo lander è atterrato sul suolo lunare, continua a fare ricerche e un giorno anche l'uomo riuscirà a visitare la luna!";
                }else
                    return "Sfortunatamente il tuo lander si è guastato prima di toccare il suolo lunare, miglioralo per poter continuare la ricerca";
            case 4:
                if(success)
                    return "Un piccolo passo per un uomo ma il primo passo per risolvere i misteri celati nello spazio!\nCongratulazioni, hai vinto!";
                else
                    return "Sfortunatamente la missione è fallita, ma non arrenderti, sei quasi alla fine";
        }
        return "Errore";
    }
}