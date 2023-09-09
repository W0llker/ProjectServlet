package multi.basic.repository.file;

import multi.domain.Client;
import multi.domain.Product;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileWork<T> {
    protected  String path;
    protected  List<T> list;

    protected List<T> deserialization() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            list = (List<T>) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить");
        }
        return list;
    }

    protected  void serialization(List<T> list) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))) {
            stream.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось сохранить");
        }
    }
}
