package p7;

import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    public LinkedList<Member> readFile() {
        LinkedList<Member> members = new LinkedList<>();
        Member member;
        String line;
        String[] splitLine;


        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            line = reader.readLine();
            while (line != null) {
                splitLine = line.split(", ");

                if (splitLine[0].equals("S")) {
                    member = new SingleClubMember('S',
                            Integer.parseInt(splitLine[1]),
                            splitLine[2],
                            Double.parseDouble(splitLine[3]),
                            Integer.parseInt(splitLine[4])
                    );

                } else {
                    member = new MultiClubMember('M',
                            Integer.parseInt(splitLine[1]),
                            splitLine[2],
                            Double.parseDouble(splitLine[3]),
                            Integer.parseInt(splitLine[4])
                    );
                }
                members.add(member);
                line = reader.readLine();
            }

        } catch (IOException exeption) {
            exeption.printStackTrace();
        }
        return members;

    }

    public void appendFile(String mem) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("members.csv", true))) {
            bufferedWriter.write(mem + "\n");
        } catch (IOException exeption) {
            exeption.printStackTrace();


        }

    }

    public void overwriteFile(LinkedList<Member> m) {
        String s;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("members.temp", false))) {
            for (int i = 0; i < m.size(); i++) {
                s = m.get(i).toString();
                bufferedWriter.write(s + "\n");
                }
        } catch (IOException exeption) {
            exeption.printStackTrace();
        }
        try{
            File f = new File("members.csv");
            File tf = new File("members.temp");
            f.delete();
            tf.renameTo(f);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}

