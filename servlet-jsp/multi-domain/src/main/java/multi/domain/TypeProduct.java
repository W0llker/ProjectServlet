package multi.domain;

public enum TypeProduct {

    COMPUTER("компьютеры"),
    MOBILE_PHONE("мобильный телефон"),
    ACCESSORIES("комплектующие");
    private String name;

    TypeProduct(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
