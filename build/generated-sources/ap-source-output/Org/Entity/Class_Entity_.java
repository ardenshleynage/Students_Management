package Org.Entity;

import Org.Entity.Course_Entity;
import Org.Entity.Students_Entity;
import Org.Entity.Teachers_Entity;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-02-26T13:12:36", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Class_Entity.class)
public class Class_Entity_ { 

    public static volatile SingularAttribute<Class_Entity, Float> score;
    public static volatile SingularAttribute<Class_Entity, LocalDate> reg_date;
    public static volatile SingularAttribute<Class_Entity, Teachers_Entity> teacher;
    public static volatile SingularAttribute<Class_Entity, Integer> access;
    public static volatile SingularAttribute<Class_Entity, Students_Entity> student;
    public static volatile SingularAttribute<Class_Entity, Course_Entity> course;
    public static volatile SingularAttribute<Class_Entity, Long> id;

}