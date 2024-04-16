import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, List<String>> phonebook = new HashMap<>();

        System.out.println("Enter file name");
        String inputFile = in.nextLine();

        phonebook = ReadWriteFile.readFile(inputFile);

        Set<String> keySet = phonebook.keySet();

        int maxLen = 0;
        for (String item : keySet){
            int currSize = phonebook.get(item).size();
            if (maxLen< currSize){
                maxLen = currSize;
            }
        }

        int i = 0;
        while (i < keySet.size()){
            for (String item : keySet) {
                if (maxLen == phonebook.get(item).size()){
                    System.out.println(item);
                    for (String number : phonebook.get(item)) {
                        System.out.printf("\t %s%n", number);
                    }
                    i++;
                }
            }
            maxLen--;
        }

    }
}

class ReadWriteFile {
    public static HashMap<String, List<String>> readFile(String fileName) {
        HashMap<String, List<String>> phonebook = new HashMap<>();

        try {
            File currentFile = new File(fileName);
            Scanner fileScanner = new Scanner(currentFile);

            List<String> phoneNumbers = new Vector<>();
            StringBuilder currentStrBuilder = new StringBuilder();


            StringBuilder currentName = new StringBuilder();

            while (fileScanner.hasNext()) {


                currentStrBuilder.append(fileScanner.next());
                currentStrBuilder.append(" ");

                switch (currentStrBuilder.charAt(currentStrBuilder.length() - 2)) {
                    case ':':
                        currentName.append(currentStrBuilder);
                        currentStrBuilder.setLength(0);
                        break;
                    case ',':
                        phoneNumbers.add(currentStrBuilder.toString());
                        currentStrBuilder.setLength(0);
                        break;
                    case ';':
                        phoneNumbers.add(currentStrBuilder.toString());
                        phonebook.put(currentName.toString(), new Vector<>(phoneNumbers));
                        phoneNumbers.clear();
                        currentStrBuilder.setLength(0);
                        currentName.setLength(0);
                        break;
                }
            }

            for (String item : phoneNumbers){
                System.out.println(item);
            }
        } catch (FileNotFoundException err) {
            System.out.println(err);
        }

        return phonebook;
    }

}