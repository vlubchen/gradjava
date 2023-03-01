package ru.vlubchen.gradjava.model;

public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    protected String name;

    protected AbstractNamedEntity() {
    }

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AbstractNamedEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}