package idao;

import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import modelo.*;
import modeloEntity.Clienteestandard;
import modeloEntity.Clientepremium;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ClienteDaoImple  {


    public List<modeloEntity.Clienteestandard> listarSTD() throws Exception {
        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<Clienteestandard> todosClientesEstandard= new ArrayList<>();

            Query nativeQuery= entityManager.createNativeQuery("SELECT * FROM clienteestandard INNER JOIN cliente WHERE clienteestandard.id_eMailestandard=cliente.Id_eMail");

            transaction.commit();

            for(Object[] row:(List<Object[]>)nativeQuery.getResultList()) {

                modeloEntity.Clienteestandard  cli= new modeloEntity.Clienteestandard((String)row[0],
                        new modeloEntity.Cliente((String)row[0],(String)row[4],(String)row[5],(String)row[6]),
                        (float)row[1],(float)row[2]);
                todosClientesEstandard.add(cli);
            }

            if (todosClientesEstandard.size()>0)
                return todosClientesEstandard;
            System.out.println("Bueno");

        }
        catch (Exception e)
        {

        }
        return null;
    }


    public List<modeloEntity.Clientepremium> listarPRM() throws Exception {
        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<Clientepremium> todosClientesPremium= new ArrayList<>();

            Query nativeQuery= entityManager.createNativeQuery("SELECT * FROM clientepremium INNER JOIN cliente WHERE clientepremium.id_eMailPremium=cliente.Id_eMail");

            transaction.commit();

            for(Object[] row:(List<Object[]>)nativeQuery.getResultList()) {

                modeloEntity.Clientepremium  cli= new modeloEntity.Clientepremium((String)row[0],
                        new modeloEntity.Cliente((String)row[0],(String)row[4],(String)row[5],(String)row[6]),
                        (float)row[1],(float)row[2]);
                todosClientesPremium.add(cli);
            }

            if (todosClientesPremium.size()>0)
                return todosClientesPremium;
            System.out.println("Bueno");

        }
        catch (Exception e)
        {

        }
        return null;



    }

    public boolean registrarClienteEstandard(Clienteestandard cliente, modeloEntity.Cliente clientePadre) throws Exception {
        try
        {
            //Registramos Cliente Estandard
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(new modeloEntity.Clienteestandard(cliente.getId(),clientePadre,cliente.getDescuento()
                    ,cliente.getTarifaAnual()));

            transaction.commit();
            System.out.println("Guardado");
            return true;


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }



    public boolean registrarClientePremium(Clientepremium cliente, modeloEntity.Cliente clientePadre) throws Exception {
        try
        {
            //Registramos Cliente Premium
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(new modeloEntity.Clientepremium(cliente.getId(),clientePadre,cliente.getDescuento()
                    ,cliente.getTarifaAnual()));

            transaction.commit();
            System.out.println("Guardado");
            return true;


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public float clienteByEmailDescuento(String email) throws Exception{
        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            modeloEntity.Clientepremium  cliente= entityManager.find(modeloEntity.Clientepremium.class,email);

            transaction.commit();

            if (cliente!=null)
                return cliente.getDescuento();
            else return 0;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NoEncontrado");
            return 0;
        }



    }

    public modeloEntity.Cliente getCliente(String email)
    {
        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            modeloEntity.Cliente cliente= entityManager.find(modeloEntity.Cliente.class,email);

            transaction.commit();

            if (cliente!=null)
                return cliente;
            else return null;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NoEncontrado");
            return null;
        }


    }

    public boolean getClienteEmail(String email) throws Exception {
        try
        {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            modeloEntity.Cliente cliente= entityManager.find(modeloEntity.Cliente.class,email);

            transaction.commit();

            if (cliente!=null)
                return true;
            else return false;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NoEncontrado");
            return false;
        }



    }



}
