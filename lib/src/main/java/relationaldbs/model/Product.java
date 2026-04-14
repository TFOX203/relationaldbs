package relationaldbs.model;


public class Product {
    
    // 🔹 atributos (lo que ves en tu HTML)
    private long id;
    private String name;
    private String image;
    private String tag;
    
    // 🔹 constructor
    public Product(long id, String name, String image, String tag) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tag = tag;}

    // 🔹 getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
