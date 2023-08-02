package multi.core.repository;

import multi.core.domain.Product;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositoryProduct {
    private final String path = "D:\\ProjectServletJsp\\servlet-jsp\\multi-core\\src\\main\\resources\\ListProduct";
    private List<Product> list;

    public RepositoryProduct() {
        this.list = new ArrayList<>();
    }

    public void save(Product good) {
        list = deserialization();
        try {
            if (deserialization().size() > 0) {
                Optional<Product> optional = deserialization().stream().max(Comparator.comparing(Product::getId));
                good.setId(optional.get().getId() + 1);
            } else {
                good.setId(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        list.add(good);
        serialization(list);
    }

    public void delete(long id) {
        list = getList();
        list.removeIf(client -> client.getId() == id);
        serialization(list);
    }

    public Product find(long id) {
        list = getList();
        Optional<Product> optional = list.stream().filter(client -> client.getId() == id).findFirst();
        if (optional.isEmpty()) {
            System.out.println("Такого клиента нету");
            return null;
        }
        return optional.get();
    }

    public List<Product> getList() {
        return deserialization();
    }

    private List<Product> deserialization() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            list = (List<Product>) objectInputStream.readObject();
        } catch (Exception e) {

        }
        return list;
    }

    private void serialization(List<Product> clientList) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))) {
            stream.writeObject(clientList);
        } catch (Exception e) {

        }
    }
}
