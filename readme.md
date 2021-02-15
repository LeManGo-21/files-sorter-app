# Files Sorter Application

Данное консольное приложение позволяет сортировать учебные фалйы по соотвествующим предметам директориям.  

Все файлы, которые подлежат сортировке должны распологаться в одной директории(**Базовой директории**).  
По умолчанию эта таже директория в которой находится .jar архив программы сортировщика.  
Можно изменить расположение базовой директории запустив программу с параметром **"-b"** и передать путь к базвой директории.  

Каждый файл должен соответствовать шаблону именования файла **"Фамилия И.О._Название предмета_Название документа.расширение"**.  
Сортировщик сам создаст в базовой директории директорию с именем соотвествующим названию предмета и переместит туда соотвествующий файл.  
Если файл не соотвествует шаблону, то сортировщик не будет его перемещать и сообщит об этом в консоль.

Если в базовой директории расположены файлы, которые должны быть проигнорированы сортировщиком, то имена этих файлов можно перечислить(каждое имя на отдельной строке) в файле **FSIgnore.TXT**, который надо будет предварительно создать в **базовой директории.**

Запустите программу с параметром **"-h"** чтобы увидеть вспомогательную информацию.