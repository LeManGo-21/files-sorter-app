package nikolaev.artem.sorter;

import nikolaev.artem.exceptions.BaseDirReadException;
import nikolaev.artem.exceptions.FSIgnoreReadException;
import nikolaev.artem.parser.FileNameParser;
import nikolaev.artem.suppliers.FilesSupplier;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;

public class SorterImpl implements Sorter {

    private FilesSupplier supplier;
    private FileNameParser parser;

    public SorterImpl(FileNameParser parser, FilesSupplier supplier){
        this.supplier = supplier;
        this.parser = parser;
    }

    @Override
    public void sort() throws BaseDirReadException, FSIgnoreReadException {
        Set<Path> baseDirFiles = supplier.getCleanFilesPathsSet();

        for (Path file : baseDirFiles){
            String fileName = file.getFileName().toString();

            if (parser.parse(fileName)){//если имя файла соотвествует шаблону
                //получаем путь к директории в которой должен быть файл
                Path targetDir = Paths.get(supplier.getBaseDirPath(), parser.getTargetDirectoryName());

                //если директории не существет то создать ее
                try {
                    if (!Files.exists(targetDir)) {
                        Files.createDirectory(targetDir);
                    }
                } catch (IOException ex) {
                    System.out.println("Невозможно создать директорию: \"" + targetDir + "\"");
                    continue;
                }

                try{
                    Files.move(file, Paths.get(targetDir.toString(), fileName));
                    System.out.println("Файл: \"" + fileName + "\" перемещен в директорию: \"" + targetDir + "\"");
                } catch (IOException ex){
                    System.out.println("Невозможно переместить файл: \"" + fileName + "\" в директорию: \"" + targetDir + "\"");
                }

            } else {
                System.out.println("Перемещение не возможно! Имя файла: \"" + fileName + "\" не соотвествует шаблону.");
            }
        }
    }


}