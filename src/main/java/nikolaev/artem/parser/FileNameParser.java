package nikolaev.artem.parser;

/**
 * Предоставляет методы для разбора имени файла на составные части согласно шаблону
 * и методы получения разобранной информации.
 */
public interface FileNameParser {

    /**
     * разбирает имя файла на составляющие по шаблону
     * @param fileName сторка, представляющая имя файла
     * @return true - в случае успешного разбора,
     *         false - если имя не соотвествует шаблону
     */
    boolean parse(String fileName);

    /**
     * Возвращет имя директории, полученное из названия файла,
     * в которой должен находиться файл
     * @return строку, представляющую имя диретории
     */
    String getTargetDirectoryName();
}
