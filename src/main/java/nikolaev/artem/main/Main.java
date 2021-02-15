package nikolaev.artem.main;

import nikolaev.artem.exceptions.BaseDirReadException;
import nikolaev.artem.exceptions.FSIgnoreReadException;
import nikolaev.artem.exceptions.IncorrectBaseDirException;
import nikolaev.artem.parser.FileNameParser;
import nikolaev.artem.parser.SubjectFilesParser;
import nikolaev.artem.sorter.Sorter;
import nikolaev.artem.sorter.SorterImpl;
import nikolaev.artem.suppliers.FilesSupplier;
import nikolaev.artem.suppliers.FilesSupplierImpl;;import java.util.Scanner;

public class Main {
    private static FilesSupplier supplier = new FilesSupplierImpl();
    private static FileNameParser parser = new SubjectFilesParser();
    private static Sorter sorter = new SorterImpl(parser, supplier);

    public static void main(String[] args) {
        //проверка наличия аргументов
        if(args.length != 0) {
            if (args.length == 1 && args[0].equals("-h")){
                System.out.printf("%-25s%s%n", "-b путь_к_диретории", "изменить базовую директорию.");
                System.out.printf("%-25s%s%n", "", "Допускается как абсолютный, так и отностильный путь");
                System.out.printf("%-25s%s%n", "", "по отношению к пути программы сортировщика.");
                System.out.println();
                System.out.printf("%-25s%s%n", "-h", "вывод вспомогательной информации.");


            } else if (args.length == 2 && args[0].equals("-b")) {//изменение базовой директории
                String path = args[1];
                //задание нового пути базовой директории
                try {
                    supplier.setBaseDirPath(path);
                }catch (IncorrectBaseDirException ex){
                    System.out.println(ex.getMessage());
                    return;
                }
            } else {
                System.out.println("Неизвестный аргумент.");
                System.out.println("Используйте флаг \"-h\" чтобы вывести вспомогательную информацию");
            }
        }

        System.out.println("\nВведите \"run\" чтобы начать сортировку\nили \"cancel\" чтобы отменить сортировку");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equals("run")){
            System.out.println("Выполняю сортировку:");
            try {
                sorter.sort();
            } catch (BaseDirReadException | FSIgnoreReadException ex){
                System.out.println(ex.getMessage());
            }
        } else if (userInput.equals("cancel")){
            System.out.println("Сортировка отменена.\nПрограмма завершается.");
        }
    }


}
