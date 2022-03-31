package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinDocumento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinClaseDocumento.class)
public class PrinClaseDocumento_ { 

    public static volatile SingularAttribute<PrinClaseDocumento, Short> clsCodigo;
    public static volatile SingularAttribute<PrinClaseDocumento, String> clsDocumento;
    public static volatile CollectionAttribute<PrinClaseDocumento, PrinDocumento> prinDocumentoCollection;
    public static volatile SingularAttribute<PrinClaseDocumento, String> clsModulo;

}