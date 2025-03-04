package Org.Entity;

import Org.Entity.Class_Entity;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-02-26T13:12:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Course_Entity.class)
public class Course_Entity_ { 

    public static volatile SingularAttribute<Course_Entity, LocalDate> reg_date;
    public static volatile SingularAttribute<Course_Entity, Integer> access;
    public static volatile ListAttribute<Course_Entity, Class_Entity> classes;
    public static volatile SingularAttribute<Course_Entity, String> name;
    public static volatile SingularAttribute<Course_Entity, String> description;
    public static volatile SingularAttribute<Course_Entity, Long> id;

}