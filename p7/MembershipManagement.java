package p7;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MembershipManagement {

    final private Scanner reader = new Scanner(System.in);


    private int getIntInput() {
        int choise = 0;
        while (choise == 0) {
            try {
                choise = reader.nextInt();
                if (choise == 0) throw new InputMismatchException();
                reader.nextLine();
            } catch (InputMismatchException exception) {
                System.out.println("ERROR: Invalid input. Try again");
                reader.nextLine();
            }
        }
        return choise;
    }


    private void printClubOptions() {
        System.out.println("\n1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");

    }

    public int getChoice() {
        int choice;
        System.out.println("\nWELCOME TO OZONE FITNESS CENTER");
        System.out.println(" ================================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.print("\nPlease select an option (or Enter -1 to quit): ");
        choice = getIntInput();
        return choice;

    }

    public String addMembers(LinkedList<Member> listMembers) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        System.out.print("\n Please enter the member's name: ");
        name = reader.nextLine();
        printClubOptions();
        System.out.print("\n Please enter the member's clubID:  ");
        club = getIntInput();
        while (club< 1 || club > 4) {
            System.out.print("\n Invalid clubID/ Please try again: ");
            club = getIntInput();
        }

        if (listMembers.size() > 0) {
            memberID = listMembers.getLast().getMemberID() + 1;
        } else {
            memberID = 1;
        }
        if (club !=4) {
            cal = (n) -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }


            };

            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            listMembers.add(mbr);
            mem = mbr.toString();
            System.out.println("\n Status: Single Club Member added");
        } else {
            cal = (n) -> {
                if (n == 4) {
                    return 1200;
                } else {
                    return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            listMembers.add(mbr);
            mem = mbr.toString();
            System.out.println("\n Status: Multi Club Member added");

        }
        return mem;
    }

    public void removeMember(LinkedList<Member> listMembers) {
        int memberID;
        System.out.println("\n Enter Member ID to remove: ");
        memberID = getIntInput();
        for (int i = 0; i < listMembers.size(); i++) {
            if (listMembers.get(i).getMemberID() == memberID) {
                listMembers.remove(i);
                System.out.print("\n Member removed");
                return;
            }

        }
        System.out.println("\n Member ID not found");
    }

    public void printMemberInfo(LinkedList<Member> listMembers) {
        int memberID;
        System.out.println("\n Enter member ID to remove: ");
        memberID = getIntInput();
        for (int i = 0; i < listMembers.size(); i++) {
            if (listMembers.get(i).getMemberID() == memberID) {
                String[] memberInfo = listMembers.get(i).toString().split(", ");
                System.out.println("\n Member type: " + memberInfo[0]);
                System.out.println("Member ID: " + memberInfo[1]);
                System.out.println("Member name: " + memberInfo[2]);
                System.out.println("Member fees: " + memberInfo[3]);
                if (memberInfo[0].equals("S")) {
                    System.out.println("ClubID: " + memberInfo[4]);
                } else {
                    System.out.println("Member Ship points: " + memberInfo[4]);
                }
                return;

            }
        }
        System.out.println("\n Member ID not found");
    }
}
