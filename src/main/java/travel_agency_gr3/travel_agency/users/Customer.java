package travel_agency_gr3.travel_agency.users;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@Entity
@DiscriminatorValue("C")
public class Customer extends User {

    boolean preferEmails;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Order> ordersList = Lists.newArrayList();
}
