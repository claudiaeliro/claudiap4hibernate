package idao;

import dao.ConexionJPA;
import modeloEntity.Articulo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;


public class ArticuloDaoImpl  {


    public boolean registrar(modeloEntity.Articulo articulo) throws Exception {
        try
        {
        ConexionJPA jpaService =ConexionJPA.getInstance();
        EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(new modeloEntity.Articulo(articulo.getId(), articulo.getDescripcion(), articulo.getPvpVenta(), articulo.getGastosEnvio(),
                articulo.getTiempoPreparacion()) );

        transaction.commit();
        System.out.println("Guardado");

        return true;

    } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No guardado");
            return false;
        }

    }


    public modeloEntity.Articulo getArticuloDAOById(String idArticulo) {

        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            modeloEntity.Articulo articulo= entityManager.find(modeloEntity.Articulo.class,idArticulo);

            transaction.commit();

            if (articulo!=null)
                return articulo;
            else return null;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NoEncontrado");
            return null;
        }


    }
}
