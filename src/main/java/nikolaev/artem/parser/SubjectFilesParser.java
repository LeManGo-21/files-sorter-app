package nikolaev.artem.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubjectFilesParser implements FileNameParser{
    //Соотвествует шаблону: "Фамилия И.О._Название предмета_Название документа.расширение"
    private final Pattern templatePattern = Pattern.compile("^[А-Я]?[а-я]+\\s[А-Я]?\\.[А-Я]?\\._[а-яА-Я\\s\\d]+_[а-яА-Я\\s\\d]+\\.\\w+", Pattern.UNICODE_CHARACTER_CLASS);
    private String author;
    private String subject;
    private String docName;
    private String extension;

    @Override
    public boolean parse(String fileName) {
        Matcher matcher = templatePattern.matcher(fileName);
        if (matcher.matches()){
            String[] nameData = fileName.split("_");
            author = nameData[0];
            subject = nameData[1];
            String[] docNameWithExtension = nameData[2].split("\\.");
            docName = docNameWithExtension[0];
            extension = docNameWithExtension[1];

            return true;
        } else {
            return false;
        }

    }

    @Override
    public String getTargetDirectoryName() {
        return subject;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public String getDocName() {
        return docName;
    }

    public String getExtension() {
        return extension;
    }
}
