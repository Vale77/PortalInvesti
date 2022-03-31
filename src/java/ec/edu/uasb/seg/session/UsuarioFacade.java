/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.seg.session;

import ec.edu.uasb.seg.constants.LoginStatus;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario getUsuarioByCodigo(Long codigo) {
        Usuario user = null;
        Query query = em.createNativeQuery("select DISTINCT CODIGO,CED_PAS_USUARIO,NOMBRES,APELLIDOS,CLAVE,EMAIL,USULDAP,DN  from V_USUARIO where CODIGO = ?", Usuario.class);
        query.setParameter(1, codigo);
        try {
            user = (Usuario) query.getSingleResult();
        } catch (NoResultException ex) {
            user = null;
        }
        return user;
    }

    @Override
    public Usuario getPerson(String userName) {
        boolean isLoginSuccessful = false;
        Usuario user = new Usuario();
        StringBuilder sb = new StringBuilder("select DISTINCT PRIN_PERSONA.PER_CODIGO CODIGO,CED_PAS_USUARIO,NOMBRES,APELLIDOS,CLAVE,EMAIL,USULDAP,DN ");
        sb.append("from V_USUARIO1, PRIN_PERSONA where V_USUARIO1.CED_PAS_USUARIO = PRIN_PERSONA.PER_ID_DOC and USULDAP = ?");
        user.setLoginStatus(LoginStatus.INACTIVE);
        Query query = em.createNativeQuery(sb.toString(), Usuario.class).setParameter(1, userName);
        try {
            user = (Usuario) query.getSingleResult();
            user.setUsuEstadoUsuario("A");
            user.setLoginStatus(LoginStatus.SUCCESSFUL);
            isLoginSuccessful = user.getLoginStatus().equals(LoginStatus.SUCCESSFUL);
            if (isLoginSuccessful) {
                user.setUsuFechaUltimoLogin(new Date());
            }
        } catch (NoResultException ex) {
            user = null;
        }
        return user;
    }

    @Override
    public List<Perfil> getPerfil(Long codigo, String perfiles) {
        StringBuilder sb = new StringBuilder("select DISTINCT COD_PERFIL, ID_PERFIL, DES_PERFIL ");
        sb.append("from V_USUARIO1, PRIN_PERSONA where V_USUARIO1.CED_PAS_USUARIO = PRIN_PERSONA.PER_ID_DOC and PRIN_PERSONA.PER_CODIGO = ? and ID_PERFIL in (" + perfiles + ")");
        Query query = em.createNativeQuery(sb.toString(), Perfil.class);
        query.setParameter(1, codigo);
        return query.getResultList();
    }

}
