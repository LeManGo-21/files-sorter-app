package nikolaev.artem.sorter;


import nikolaev.artem.exceptions.BaseDirReadException;
import nikolaev.artem.exceptions.FSIgnoreReadException;

/**
 * Сортирует файлы в базовой диреткории
 */
public interface Sorter {
    /**
     * Выполняет сортировку файлов в базовой директории
     * @throws FSIgnoreReadException если не удается прочитать FSIgnore.TXT
     * @throws BaseDirReadException если не удается прочитаь базовую директорию
     */
    void sort() throws BaseDirReadException, FSIgnoreReadException;
}
