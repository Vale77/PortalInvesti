package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveCarta;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveLineaInvestProyec.class)
public class InveLineaInvestProyec_ { 

    public static volatile SingularAttribute<InveLineaInvestProyec, Integer> lipCodigo;
    public static volatile SingularAttribute<InveLineaInvestProyec, String> lipNombre;
    public static volatile SingularAttribute<InveLineaInvestProyec, Short> lipAreaAcad;
    public static volatile SingularAttribute<InveLineaInvestProyec, Integer> lipCodAnterior;
    public static volatile CollectionAttribute<InveLineaInvestProyec, InveCarta> inveCartaCollection;

}