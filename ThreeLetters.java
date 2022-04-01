import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ThreeLetters{
    static String[] alphabeta = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    static HashMap<String,ArrayList<String>> wordsMap = new HashMap<>(); 
    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File("threeletterwords.txt");
        Scanner scanner = new Scanner(textFile);
        ArrayList<String> wordsList = new ArrayList<>();
        while(scanner.hasNextLine()){
            String word = scanner.next();
            //wordsList.add(new Node(word));
            wordsList.add(word);
        }
        System.out.println(wordsList.size()+" of words loaded");
        scanner.close();
        wordsMap= initializeMap(wordsList);
        HashMap<String,String> predecessorMap = BFS(wordsMap, "cat", "dog");
        
        scanner = new Scanner(System.in);
        while(true){
        System.out.println("Enter a beginning word");
        String beginningWord = scanner.nextLine();
        System.out.println("Enter a destination word");
        String destinationWord = scanner.nextLine();
        
            
        ArrayList<String> Path =reconstructPath(predecessorMap, beginningWord,destinationWord);
        if(Path!=null){
            for (String string : Path) {
                System.out.println(string);
            }
        }
        else{
            System.out.println("It returns a null");
        }
    }
        //scanner.close();
    }
        // for (Map.Entry<String,ArrayList<String>> entry: wordsMap.entrySet()) {
        //         System.out.println("parent:"+entry.getKey());
        //         System.out.println("value:"+entry.getValue());
        //     }
        // }
        // findNode(beginningWord, wordsList).setDiscovered(true);
        // BFS(wordsList, beginningWord, destinationWord);
        // scanner.close();
    //}

    public static HashMap<String,ArrayList<String>> initializeMap(ArrayList<String> wordsList) {
        HashMap<String,ArrayList<String>> hashMap = new HashMap<>();
        for (String string : wordsList) {
            ArrayList<String> neighborList = new ArrayList<>();
            for (String neighbor : wordsList) {
                if(string.charAt(0)!=neighbor.charAt(0)&&string.charAt(1)==neighbor.charAt(1)&&string.charAt(2)==neighbor.charAt(2)){
                    neighborList.add(neighbor);
                }
                else if(string.charAt(0)==neighbor.charAt(0)&&string.charAt(1)!=neighbor.charAt(1)&&string.charAt(2)==neighbor.charAt(2)){
                    neighborList.add(neighbor);
                }
                else if(string.charAt(0)==neighbor.charAt(0)&&string.charAt(1)==neighbor.charAt(1)&&string.charAt(2)!=neighbor.charAt(2)){
                    neighborList.add(neighbor);
                }
            }
            hashMap.put(string, neighborList); 
        }
        return hashMap;
    }

    // public static ArrayList<Node> BFS(ArrayList<Node>wordsList,String beginningWord,String destinationWord) {
    //     Queue<Node> queue = new PriorityQueue<>();
    //     for(int i=0;i<beginningWord.length();i++){
    //         for (String letter : alphabeta) {
    //             Node beginingNode = findNode(beginningWord, wordsList);
    //             String newWord="";
    //             if(i==0){
    //             newWord=letter+beginningWord.substring(1, 2);
    //             }
    //             else if(i==1){
    //             newWord=beginningWord.substring(0,1)+letter+beginningWord.substring(2);
    //             }
    //             else if(i==2){
    //             newWord=beginningWord.substring(0, 2)+letter;
    //             }
    //             Node neighbor = findNode(newWord, wordsList);
    //             if(neighbor!=null&&!neighbor.discovered){
    //                 neighbor.setDiscovered(true);
    //                 neighbor.setPredecessor(beginingNode);
    //                 queue.add(neighbor);
    //             }
    //         }
    //     }
    //     return null;
    // }

    public static HashMap<String,String> BFS(HashMap<String,ArrayList<String>> wordsList,String beginning,String destination) {
        HashMap<String,String> discovered = new HashMap<>();
        HashMap<String,String> predecessor = new HashMap<>();
        discovered.put(beginning, "discovered");
        ArrayList<String> wordsArray = new ArrayList<>();
        wordsArray.add(beginning);
            while(!wordsArray.isEmpty()){
                String removed = wordsArray.remove(0);
                //System.out.println(removed);
                for (String string : wordsList.get(removed)) {
                    if(!discovered.containsKey(string)){
                        
                        discovered.put(string, "discovered");
                       // System.out.println(string);
                        predecessor.put(string, removed); 
                        if(string.equals(destination)){
                            return predecessor;
                        }
                        wordsArray.add(string);
                    }
                }
                
            }
            return predecessor;
    }

    // public static Node findNode(String string,ArrayList<Node>wordsList) {
    //     for (Node word : wordsList) {
    //         if(word.name.equals(string)){return word;};
    //     }
    //     return null;
    // }
    static ArrayList<String> reconstructPath(HashMap<String,String> predecessor, String beginningWord,String goal){
        String curString = goal;
        ArrayList<String> path = new ArrayList<>();
        while(predecessor.get(curString)!=null){
            path.add(curString);
            curString = predecessor.get(curString);
            if(curString.equals(beginningWord)){
                path.add(curString);
                return path;
            }
        }
        return null;

    }
}
