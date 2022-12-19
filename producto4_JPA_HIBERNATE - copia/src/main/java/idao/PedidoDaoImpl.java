package idao;


import dao.ConexionJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import modeloEntity.Articulo;
import modeloEntity.Cliente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PedidoDaoImpl  {



    public boolean registrar(modeloEntity.Pedido pedido) throws Exception {

        LocalDateTime localDateTime;
        Timestamp timestamp;

        try {
            ConexionJPA jpaService =ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory= jpaService.getEntityManagerFactory();
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Articulo articulo= new Articulo(pedido.getIdArticuloPedido().getId(), pedido.getIdArticuloPedido().getDescripcion(),
                    pedido.getIdArticuloPedido().getPvpVenta(),pedido.getIdArticuloPedido().getGastosEnvio(),
                    pedido.getIdArticuloPedido().getTiempoPreparacion());

            //pedido.getIdArticuloPedido().;
            if (articulo!=null){
                pedido.setIdArticuloPedido(articulo);
            }

            Cliente cliente=new Cliente(pedido.getIdEmailpedido().getId(),pedido.getIdEmailpedido().getNombre(),
                    pedido.getIdEmailpedido().getDomicilio(),pedido.getIdEmailpedido().getNif());
            if (cliente!=null){
                pedido.setIdEmailpedido(cliente);
            }
            entityManager.persist(pedido);

            //entityManager.persist(new modeloEntity.Pedido(pedido.getId(), pedido.getCantidad(),pedido.getFechaHora(),
            //        pedido.getIdArticuloPedido(),pedido.getIdEmailpedido()));

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




    public int getNumPedido() {
        int rowCnt =0 ;
        try {
            ConexionJPA jpaService = ConexionJPA.getInstance();
            EntityManagerFactory entityManagerFactory = jpaService.getEntityManagerFactory();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Query nativeQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM pedido");

            transaction.commit();


            rowCnt=(Integer) nativeQuery.getSingleResult();
            return rowCnt;
        }
    catch (Exception e) {
        e.printStackTrace();
        System.out.println("NoEncontrado");
        return rowCnt;
    }






       /* Connection con=null;
        try {
            con=Conexion.conectar();
            CallableStatement sp= con.prepareCall("CALL getNumPedido(?)");
            sp.registerOutParameter("numPedido", Types.INTEGER);
            sp.execute();
            numPedido = sp.getInt("numPedido");
            sp.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numPedido;*/
    }
/*
    @Override
    public void borrarPedido(int id_Pedido) {
        Connection con=null;
        boolean existe=false;
        try {
            con=Conexion.conectar();
            CallableStatement sp=con.prepareCall("CALL eliminarPedido(?)");
            sp.setInt("id_Pedido",id_Pedido);
            sp.execute();
            sp.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {
        List<Pedido> lista = new ArrayList<>();
        Connection con = null;
        try{
            con= Conexion.conectar();
            CallableStatement sp= con.prepareCall("{CALL listarPedidos}");
            ResultSet rs = sp.executeQuery();
            while(rs.next()){
                Cliente cliente;
                Articulo articulo = new Articulo(rs.getString("idArticuloPedido"), rs.getString("Descripcion"),rs.getFloat("PvpVenta"), rs.getFloat("GastosEnvio"), rs.getInt("TiempoPreparacion"));
                if(rs.getFloat("TarifaAnual")==0) {
                    cliente = new ClienteEstandard(rs.getString("id_eMail"), rs.getString("Nombre"), rs.getString("Domicilio"), rs.getString("Nif"));
                } else {
                    cliente = new ClientePremium(rs.getString("id_eMail"), rs.getString("Nombre"), rs.getString("Domicilio"), rs.getString("Nif"));
                }
                Pedido pedido = new Pedido(rs.getInt("idPedido"), articulo, rs.getInt("Cantidad"), cliente);
                pedido.setFechaYhora(rs.getTimestamp("FechaHora").toLocalDateTime());
                lista.add(pedido);

            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return lista;
    }

   */
}
