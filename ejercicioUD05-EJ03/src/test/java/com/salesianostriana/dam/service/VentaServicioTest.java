package com.salesianostriana.dam.service;

import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.LineaDeVenta;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.Venta;
import com.salesianostriana.dam.repos.ProductoRepositorio;
import com.salesianostriana.dam.repos.VentaRepositorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VentaServicioTest {

    @Mock
    ProductoRepositorio productoRepositorio;

    @Mock
    VentaRepositorio ventaRepositorio;

    @InjectMocks
    VentaServicio ventaServicio;


    @Test
    void nuevaVenta() {

        Producto p = Producto.builder()
                .id(1L)
                .nombre("Producto")
                .precio(12.34)
                .build();

        Cliente c = Cliente.builder()
                .id(1L)
                .nombre("Rompetechos")
                .email("rompetechos@ruedelpercebe13.com")
                .dni("12345678A")
                .build();

        Map<Long, Integer> carrito = Map.of(1L, 3);

        Venta v = Venta.builder()
                .id(1L)
                .cliente(c)
                .lineasDeVenta(List.of(LineaDeVenta.builder()
                                .producto(p)
                                .cantidad(3)
                                .pvp(p.getPrecio())
                        .build()))
                .build();

        when(productoRepositorio.findById(any(Long.class))).thenReturn(Optional.of(p));

        when(ventaRepositorio.save(any(Venta.class))).thenReturn(v);

        Venta result = ventaServicio.nuevaVenta(carrito, c);

        assertEquals(1, result.getLineasDeVenta().size());

        assertEquals(12.34*3, result.getLineasDeVenta().stream().mapToDouble(l -> l.getPvp() * l.getCantidad()).sum());
    }

    @DisplayName("Add producto to venta Test")
    @Test
    void addProductoToVentaTest() {

        Producto p = Producto.builder()
                .id(1L)
                .nombre("Producto")
                .precio(12.34)
                .build();

        Producto pNew = Producto
                .builder()
                .id(1L)
                .nombre("Macbook")
                .precio(1299.00)
                .build();

        Cliente c = Cliente.builder()
                .id(1L)
                .nombre("Rompetechos")
                .email("rompetechos@ruedelpercebe13.com")
                .dni("12345678A")
                .build();

        Map<Long, Integer> carrito = Map.of(1L, 3);

        Venta v = Venta.builder()
                .id(1L)
                .cliente(c)
                .lineasDeVenta(List.of(LineaDeVenta.builder()
                        .producto(p)
                        .cantidad(3)
                        .pvp(p.getPrecio())
                        .build()))
                .build();

        LineaDeVenta l1 = LineaDeVenta.builder()
                .id(1L)
                .producto(p)
                .cantidad(3)
                .pvp(p.getPrecio())
                .build();
        LineaDeVenta l2 = LineaDeVenta.builder()
                .id(2L)
                .producto(pNew)
                .cantidad(1)
                .pvp(pNew.getPrecio())
                .build();

        Venta vNew = Venta
                .builder()
                .id(2L)
                .lineasDeVenta(List.of(l1, l2))
                .build();

        Map<Long, Integer> carritoBew = Map.of(1L, 3, 2L, 1);


        when(ventaRepositorio.findById(any(Long.class))).thenReturn(Optional.of(v));
        when(productoRepositorio.findById(any(Long.class))).thenReturn(Optional.of(pNew));
        when(ventaRepositorio.save(v)).thenReturn(vNew);

        V



        assertNotNull(ventaRepositorio.findById(1L));
        assertEquals(((12.34*3)+1299.00), vNew.getLineasDeVenta().stream().mapToDouble(l -> l.getPvp() * l.getCantidad()).sum());
        assertEquals(4, result.getLineasDeVenta().size());
        assertEquals(vNew.getLineasDeVenta().size(), result.getLineasDeVenta().size());
    }
}