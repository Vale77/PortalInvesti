package ec.edu.uasb.principal.entities;

import ec.edu.uasb.principal.entities.PrinClaseDocumento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinDocumento.class)
public class PrinDocumento_ { 

    public static volatile SingularAttribute<PrinDocumento, byte[]> docArchivo;
    public static volatile SingularAttribute<PrinDocumento, String> docEntidad;
    public static volatile SingularAttribute<PrinDocumento, String> docNombre;
    public static volatile SingularAttribute<PrinDocumento, String> docExtension;
    public static volatile SingularAttribute<PrinDocumento, String> docUsuarioAct;
    public static volatile SingularAttribute<PrinDocumento, String> docModOrigen;
    public static volatile SingularAttribute<PrinDocumento, String> docDirGeneral;
    public static volatile SingularAttribute<PrinDocumento, String> docDirDestino;
    public static volatile SingularAttribute<PrinDocumento, Date> docFechaCrea;
    public static volatile SingularAttribute<PrinDocumento, Long> docEntidadCodigo;
    public static volatile SingularAttribute<PrinDocumento, Date> docFechaAct;
    public static volatile SingularAttribute<PrinDocumento, String> docUsuario;
    public static volatile SingularAttribute<PrinDocumento, Long> docCodigo;
    public static volatile SingularAttribute<PrinDocumento, PrinClaseDocumento> prinClaseDocumento;

}