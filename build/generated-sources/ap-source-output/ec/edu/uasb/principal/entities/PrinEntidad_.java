package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinTipoEntidad;
import ec.edu.uasb.principal.entities.PrinTitulo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinEntidad.class)
public class PrinEntidad_ { 

    public static volatile SingularAttribute<PrinEntidad, String> entAbreviatura;
    public static volatile SingularAttribute<PrinEntidad, String> pentAutor;
    public static volatile SingularAttribute<PrinEntidad, Date> entFechaCrea;
    public static volatile SingularAttribute<PrinEntidad, String> entUsuario;
    public static volatile CollectionAttribute<PrinEntidad, PrinTitulo> prinTituloCollection;
    public static volatile SingularAttribute<PrinEntidad, Short> entCodigo;
    public static volatile SingularAttribute<PrinEntidad, String> entUsuarioAct;
    public static volatile SingularAttribute<PrinEntidad, String> entNombre;
    public static volatile SingularAttribute<PrinEntidad, Date> entFechaAct;
    public static volatile SingularAttribute<PrinEntidad, String> pentEditor;
    public static volatile SingularAttribute<PrinEntidad, PrinTipoEntidad> prinTipoEntidad;

}