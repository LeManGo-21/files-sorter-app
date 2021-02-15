package nikolaev.artem.suppliers;

import nikolaev.artem.exceptions.BaseDirReadException;
import nikolaev.artem.exceptions.FSIgnoreReadException;
import nikolaev.artem.exceptions.IncorrectBaseDirException;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesSupplierImpl implements FilesSupplier {
    private Path baseDir;
    private Path FSIgnorePath;

    public FilesSupplierImpl() {
        setBaseDirPath(".");
    }

    @Override
    public Set<Path> getFilesPathsFromBaseDir() throws BaseDirReadException{
        Set<Path> baseDirFiles;

        try(Stream<Path> baseDirStream = Files.list(baseDir)) {
            //фильтр пропускает только пути являющиеся файлами
            baseDirFiles = baseDirStream.filter(f -> Files.isRegularFile(f, LinkOption.NOFOLLOW_LINKS))
                    .collect(Collectors.toSet());
        } catch (IOException ex){
            throw new BaseDirReadException("Ошибка во время чтения базовой директории", ex);
        }

        return baseDirFiles;
    }

    @Override
    public Set<Path> getFilesPathsFromFSIgnore() throws FSIgnoreReadException {
        Set<Path> ignoreFiles;

        try(Stream<String> FSIgnoreStream = Files.lines(FSIgnorePath)) {
            //происходит преобразование строковых имен файлов в полные пути к этим файлам
            ignoreFiles = FSIgnoreStream.map(string -> Paths.get(baseDir.toString(), string)).collect(Collectors.toSet());
        } catch (IOException ex){
            throw new FSIgnoreReadException("Ошибка во время чтения файла FSIgnore.TXT", ex);
        }

        return ignoreFiles;
    }

    @Override
    public Set<Path> getCleanFilesPathsSet() throws FSIgnoreReadException, BaseDirReadException {
        Set<Path> baseDirFilesToSort = getFilesPathsFromBaseDir();
        if (Files.exists(FSIgnorePath)) {
            baseDirFilesToSort.removeAll(getFilesPathsFromFSIgnore());
        }
        return baseDirFilesToSort;
    }

    @Override
    public void setBaseDirPath(String baseDirPath) throws IncorrectBaseDirException{
        try {
            Path newBaseDir = Paths.get(baseDirPath);
            if (!Files.exists(newBaseDir)) {
                throw new IncorrectBaseDirException("Директории по указаному пути не сущетсвует.");
            }
            baseDir = newBaseDir;
            FSIgnorePath = Paths.get(baseDir.toString(),"FSIgnore.TXT");
        } catch (InvalidPathException invalidPathEx){
            throw new IncorrectBaseDirException(invalidPathEx.getMessage(), invalidPathEx);
        }
    }

    @Override
    public String getBaseDirPath() {
        return baseDir.toAbsolutePath().toString();
    }
}
