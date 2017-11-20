package listeners;

import com.krishnamg.model.Person;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by krishnamg on 20/11/17.
 */
public class EntityEventListener {

    @PrePersist
    public void prePersist(Person person) {
        System.out.println("Listening User Pre Persist : " + person.toString());
        person.setCreatedDate(LocalDateTime.now());
        person.setModifiedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Person person) {
        System.out.println("Listening User Pre Persist : " + person.toString());
        person.setModifiedDate(LocalDateTime.now());
    }

}
