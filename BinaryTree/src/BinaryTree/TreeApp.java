package BinaryTree;
import java.util.Scanner;

public class TreeApp {
    public static void main(String[] args) {
        Tree theTree =new Tree();


        theTree.insert(50);
        theTree.insert(25);
        theTree.insert(75);
        theTree.insert(12);
        theTree.insert(37);
        theTree.insert(43);
        theTree.insert(30);
        theTree.insert(33);
        theTree.insert(87);
        theTree.insert(93);
        theTree.insert(97);
         theTree.minimum().displayNode();

         while (true){
             System.out.println("İslem seciniz:");
             System.out.println("Goster(g), Ekle(e), Bul(b), Sil(s), Dolas(d), Kapat(k)");
             int choice=getChar();
             switch (choice){
                 case 'g':
                     theTree.displayTree();
                     break;
                 case 'e':
                     System.out.println("Eklenecek deger:");
                     long value =getLong();
                     theTree.insert(value);
                     break;
                 case 'b':
                     System.out.println("Aranacak deger:");
                     value=getLong();
                     Node found=theTree.find(value);
                     if (found!=null){
                         System.out.println("Bulundu:");
                         found.displayNode();
                         System.out.println("");
                     }
                     else {
                         System.out.println("Bulunamadı:");
                     }
                     System.out.println(value);
                     break;
                 case 's':
                     System.out.println("Silinecek Deger:");
                     value=getLong();
                     boolean didDelete= theTree.delete(value);
                     if (didDelete){
                         System.out.println("Silindi" + value);
                     }
                     else {
                         System.out.println("Silinemedi!");
                     }
                     break;
                 case 'd':
                     System.out.println("Dolasma Turu Seciniz Pre(1(-In(2)-Post(3):");
                     value=getLong();
                     theTree.traverse((int)value);
                     break;
                 case 'k':
                     Runtime.getRuntime().exit(0);
                     break;
                 default:
                     System.out.println("Hatali giris!..");
             }
         }

    }
    public static char getChar(){
        String s=getString();
        return s.charAt(0);
    }
    public static String getString(){
        Scanner input =new Scanner(System.in);
        String s=input.next();
        return s;
    }
    public static long getLong(){
        String s=getString();
        return Long.parseLong(s);
    }


}
