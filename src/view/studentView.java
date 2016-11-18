package view;

/**
 * Created by michaelfolkmann on 16/11/2016.
 */
public class studentView {

    public void studentMenu(){

        int studentMenu = 8;

        switch (studentMenu) {
            case 1:
                System.out.println("1: Rate lecture");
                break;
            case 2:
                System.out.println("2: See other reviews");

                break;
            case 3:
                System.out.println("3: Show lectures");

                break;
            case 4:
                System.out.println("4: Show participating");
                break;
            case 5:
                System.out.println("5: Delete own comment");
                break;

            default:

                break;

        }


    }
}
