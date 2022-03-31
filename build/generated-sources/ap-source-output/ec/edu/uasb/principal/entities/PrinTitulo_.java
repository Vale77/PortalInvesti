package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinEntidad;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinTitulo.class)
public class PrinTitulo_ { 

    public static volatile SingularAttribute<PrinTitulo, String> nroRegistro;
    public static volatile SingularAttribute<PrinTitulo, String> titulo;
    public static volatile SingularAttribute<PrinTitulo, String> esPrincipal;
    public static volatile SingularAttribute<PrinTitulo, Short> titCodigo;
    public static volatile SingularAttribute<PrinTitulo, PrinPersona> prinPersona;
    public static volatile SingularAttribute<PrinTitulo, PrinEntidad> prinEntidad;
    public static volatile SingularAttribute<PrinTitulo, Date> fechaRegistro;
    public static volatile SingularAttribute<PrinTitulo, PrinPais> prinPais;
    public static volatile SingularAttribute<PrinTitulo, String> titNivelAcad;

}