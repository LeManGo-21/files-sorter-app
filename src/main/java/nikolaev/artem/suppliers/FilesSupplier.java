package nikolaev.artem.suppliers;

import nikolaev.artem.exceptions.BaseDirReadException;
import nikolaev.artem.exceptions.FSIgnoreReadException;
import nikolaev.artem.exceptions.IncorrectBaseDirException;

import java.nio.file.Path;
import java.util.Set;

/**
 * Класс поставляющий файлы из базовой директории и файла FSIgnore.TXT
 * Путь к базовой директории по умолчанию ".",
 * но можно задать другой путь к директории.
 * Путь к файлу FSIgnore.TXT всегда "{baseDirPath}\FSIgnore.TXT",
 * то есть файл FSIgnore.TXT всегда находится в базовой дирекории.
 */
public interface FilesSupplier {
    /**
     * Сканирует только базовую дирекотрию, не переходя в поддиректории и возвращет множество,
     * содержащее экземпляры класса Path только файлов (не директорий и не символических ссылок)
     * @return множество, содержащее экземпляры класса Path файлов из базовой директории
     * @throws BaseDirReadException если не удается прочитаь базовую директорию
     */
    Set<Path> getFilesPathsFromBaseDir() throws BaseDirReadException;

    /**
     * Сканирует файл FSIgnore.TXT и возвращет множество, содержащее экземпляры класса Path файлов,
     * названия которых перчислены в данном файле.
     * @return множество, содержащее экземпляры класса Path
     * @throws FSIgnoreReadException если не удается прочитать данный файл
     */
    Set<Path> getFilesPathsFromFSIgnore() throws FSIgnoreReadException;

    /**
     * Из множества содержащего экземпляры класса Path файлов базовой директории
     * удаляет Path тех файлов, кторые есть во множестве Path файлов перечисленных в FSIgnore.TXT
     * @return множество, содержащее экземпляры класса Path
     * @throws FSIgnoreReadException если не удается прочитать FSIgnore.TXT
     * @throws BaseDirReadException если не удается прочитаь базовую директорию
     */
    Set<Path> getCleanFilesPathsSet() throws FSIgnoreReadException, BaseDirReadException ;

    /**
     * Устанавливает новый путь к базовой директории
     * @param baseDirPath строка являющаяся путем к базовой директории
     * @throws IncorrectBaseDirException если путь задан некоректно или директори по такому пути не существует
     */
    void setBaseDirPath(String baseDirPath) throws IncorrectBaseDirException;

    /**
     * Возвращает абсолютный путь к базовой директории
     * @return строку представляющую абсолютный путь к базовой директории
     */
    String getBaseDirPath();
}
