package Org.Entity;

import Org.Entity.Class_Entity;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-02-26T13:12:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Students_Entity.class)
public class Students_Entity_ { 

    public static volatile SingularAttribute<Students_Entity, String> reg_number;
    public static volatile SingularAttribute<Students_Entity, String> address;
    public static volatile SingularAttribute<Students_Entity, Integer> access;
    public static volatile SingularAttribute<Students_Entity, Integer> sex;
    public static volatile ListAttribute<Students_Entity, Class_Entity> classes;
    public static volatile SingularAttribute<Students_Entity, String> last_name;
    public static volatile SingularAttribute<Students_Entity, LocalDate> reg_date;
    public static volatile SingularAttribute<Students_Entity, String> password;
    public static volatile SingularAttribute<Students_Entity, Integer> phone;
    public static volatile SingularAttribute<Students_Entity, LocalDate> dob;
    public static volatile SingularAttribute<Students_Entity, Long> id;
    public static volatile SingularAttribute<Students_Entity, String> first_name;
    public static volatile SingularAttribute<Students_Entity, String> email;

}