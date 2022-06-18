package ivan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "persons")
public class Person {

	@Id
	@Column(name = "id", unique = true, nullable = false)
    int    id;
	@Column(name = "name")
    String name;
    
    public Person() { }
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

}
