package modeloEntity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido", nullable = false)
    private Integer id;

    @Column(name = "Cantidad")
    private Integer cantidad;

    @Column(name = "FechaHora")
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArticuloPedido")
    private Articulo idArticuloPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eMailPedido")
    private Cliente idEmailpedido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Articulo getIdArticuloPedido() {
        return idArticuloPedido;
    }

    public void setIdArticuloPedido(Articulo idArticuloPedido) {
        this.idArticuloPedido = idArticuloPedido;
    }

    public Cliente getIdEmailpedido() {
        return idEmailpedido;
    }

    public void setIdEmailpedido(Cliente idEmailpedido) {
        this.idEmailpedido = idEmailpedido;
    }

    public Pedido(Integer id, Integer cantidad, LocalDateTime fechaHora, Articulo idArticuloPedido, Cliente idEmailpedido) {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
        this.idArticuloPedido = idArticuloPedido;
        this.idEmailpedido = idEmailpedido;
    }

    public Pedido()
    {

    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                ", idArticuloPedido=" + idArticuloPedido +
                ", idEmailpedido=" + idEmailpedido +
                '}';
    }
}